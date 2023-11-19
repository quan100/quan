package cn.javaquan.tools.chat.context;

import cn.javaquan.tools.chat.core.ChannelPool;
import cn.javaquan.tools.chat.core.support.AuthorizationProcessor;
import cn.javaquan.tools.chat.util.SpringUtils;
import io.netty.channel.Channel;
import io.netty.channel.ChannelHandler.Sharable;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.channel.group.ChannelGroup;
import io.netty.handler.codec.http.FullHttpRequest;
import io.netty.handler.timeout.IdleState;
import io.netty.handler.timeout.IdleStateEvent;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.HashMap;
import java.util.Map;

/**
 * 客户端用户状态处理
 *
 * @author javaquan
 */
@Sharable
public class ClientInboundHandler extends ChannelInboundHandlerAdapter {

    private static final Log logger = LogFactory.getLog(ClientInboundHandler.class);

    private final ChannelGroup group;
    private final String websocketPath;

    public ClientInboundHandler(ChannelGroup group, String websocketPath) {
        this.group = group;
        this.websocketPath = websocketPath;
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        if (msg instanceof FullHttpRequest) {
            FullHttpRequest request = (FullHttpRequest) msg;
            String uri = request.uri();
            Map<String, String> queryParams = paramsParser(uri);
            online(ctx.channel(), queryParams);
            request.setUri(websocketPath);
        }
        super.channelRead(ctx, msg);
    }

    @Override
    public void userEventTriggered(ChannelHandlerContext ctx, Object evt) throws Exception {
        if (evt instanceof IdleStateEvent) {
            IdleStateEvent event = (IdleStateEvent) evt;
            if (event.state() == IdleState.READER_IDLE) {
                logger.info(String.format("用户[%s]闲置时间超过最大值，将关闭连接！", ChannelPool.getSessionState(ctx.channel())));
                ctx.channel().close();
            }
        } else {
            super.userEventTriggered(ctx, evt);
        }
    }

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        group.add(ctx.channel());
    }

    @Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception {
        Channel channel = ctx.channel();
        group.remove(channel);
        offline(channel);
    }

    /**
     * 异常时调用
     *
     * @param ctx
     * @param cause
     */
    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) {
        logger.error("服务器错误", cause);
        offline(ctx.channel());
        // 发生异常之后关闭连接（关闭channel）
        ctx.channel().close();
    }

    /**
     * url参数解析
     *
     * @param uriParams
     * @return
     * @throws URISyntaxException
     */
    private Map<String, String> paramsParser(String uriParams) throws URISyntaxException {
        URI uri = new URI(uriParams);
        Map<String, String> paramsMap = new HashMap<>();

        String queryParam = uri.getQuery();
        String[] queryParams = queryParam.split("&");

        for (String param : queryParams) {
            String[] urlParam = param.split("=");
            paramsMap.put(urlParam[0], urlParam[1]);
        }

        return paramsMap;
    }

    /**
     * 用户上线
     *
     * @param channel
     * @param urlParams url参数
     */
    private void online(Channel channel, Map<String, String> urlParams) {
        String userId = urlParams.get("userId");
        String authorization = urlParams.get("authorization");
        AuthorizationProcessor authorizationProcessor = SpringUtils.getBean(AuthorizationProcessor.class);

        if (!authorizationProcessor.checkAuth(authorization)) {
            channel.close();
            logger.info(String.format("用户[%s]凭证校验失败，连接被服务器拒绝", userId));
            return;
        }

        logger.info(String.format("用户[%s]上线", userId));

        channel.attr(ChannelPool.SESSION_STATE).set(userId);
        ChannelPool.addChannel(userId, channel);

        /// TODO 若用户上线，则通知好友已上线。kafka发送上线事件
    }

    /**
     * 用户离线
     *
     * @param channel
     */
    private void offline(Channel channel) {
        ChannelPool.removeChannel(channel);

        logger.info(String.format("用户[%s]下线", ChannelPool.getSessionState(channel)));

        /// TODO 若用户下线，则通知好友已下线。kafka发送下线事件
    }

}
