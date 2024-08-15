package cn.javaquan.tools.notify;

/**
 * 该自定义异常主要定义为向外部服务发出消息通知，通知内容为抛出的异常信息.
 * <p>
 * 当抛出该异常时，会调用 {@link SystemExceptionNotificationExecutor} 消息通知执行器
 *
 * @author javaquan
 * @since 1.0.0
 */
public class SystemNotifyException extends RuntimeException {

    private static final long serialVersionUID = -8887652625723536544L;

    /**
     * 构造系统异常，并推送异常信息.
     * @param message 错误信息
     */
    public SystemNotifyException(String message) {
        super(message);
        SystemExceptionNotificationExecutor.send(this);
    }

    /**
     * 构造系统异常，并推送异常信息.
     * @param e 异常
     */
    public SystemNotifyException(Throwable e) {
        super(e);
        SystemExceptionNotificationExecutor.send(this);
    }

    /**
     * 构造系统异常，并推送异常信息.
     * @param message 错误信息
     * @param e 异常
     */
    public SystemNotifyException(String message, Throwable e) {
        super(message, e);
        SystemExceptionNotificationExecutor.send(this);
    }

}
