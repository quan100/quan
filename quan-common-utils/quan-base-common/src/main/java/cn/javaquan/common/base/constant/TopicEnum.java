package cn.javaquan.common.base.constant;

/**
 * 消息队列主题.
 *
 * @author wangquan
 * @since 1.0.0
 */
public final class TopicEnum {

    /**
     * 私有构造方法.
     */
    private TopicEnum() {
    }

    /**
     * 角色权限刷新主题.
     * <p>
     * 一般用于网关权限刷新
     */
    public static final String ROLE_AUTHORIZATION = "ROLE_AUTHORIZATION";

    /**
     * 钉钉群机器人消息发送主题.
     */
    public static final String DING_MSG_TOPIC = "DING_MSG_TOPIC";

}
