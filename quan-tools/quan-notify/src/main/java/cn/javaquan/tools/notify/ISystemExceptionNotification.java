package cn.javaquan.tools.notify;

/**
 * 系统异常信息通知接口.
 *
 * @author javaquan
 * @since 2.2.0
 */
public interface ISystemExceptionNotification {

    /**
     * 推送消息.
     * @param e 异常信息
     */
    void send(Throwable e);

}
