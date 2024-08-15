package cn.javaquan.cloud.gateway.auth.util;

import com.alibaba.fastjson.JSONObject;
import cn.javaquan.common.base.message.Result;
import org.springframework.core.io.buffer.DataBuffer;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.server.reactive.ServerHttpResponse;
import reactor.core.publisher.Mono;

/**
 * HTTP响应工具类.
 *
 * @author javaquan
 * @since 1.0.0
 */
public final class ResponseUtil {

    /**
     * 私有构造方法.
     */
    private ResponseUtil() {
    }

    /**
     * 输出信息.
     * @param response the response
     * @param result 响应参数
     * @return mono
     */
    public static Mono<Void> writeResponse(ServerHttpResponse response, Result result) {
        response.getHeaders().add(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE);
        DataBuffer dataBuffer = response.bufferFactory().wrap(JSONObject.toJSONBytes(result));
        return response.writeWith(Mono.just(dataBuffer));
    }

}
