package cn.javaquan.tools.notify;

/**
 * 该自定义异常主要定义为向外部服务发出消息通知，通知内容为抛出的异常信息
 * <p>
 * 当抛出该异常时，会调用 {@link SystemExceptionNotificationExecutor} 消息通知执行器
 *
 * @author javaquan
 */
public class SystemNotifyException extends RuntimeException {

    private static final long serialVersionUID = -8887652625723536544L;

    public SystemNotifyException(String message) {
        super(message);
        SystemExceptionNotificationExecutor.send(this);
    }

    public SystemNotifyException(Throwable cause) {
        super(cause);
        SystemExceptionNotificationExecutor.send(this);
    }

    public SystemNotifyException(String message, Throwable cause) {
        super(message, cause);
        SystemExceptionNotificationExecutor.send(this);
    }

}
