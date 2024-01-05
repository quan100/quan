package cn.javaquan.tools.notice;

/**
 * 自定义异常
 * <p>
 * 当抛出该异常时，会调用 {@link NoticeExecutor} 消息通知执行器
 *
 * @author javaquan
 */
public class SystemNoticeException extends RuntimeException {

    private static final long serialVersionUID = -8887652625723536544L;

    public SystemNoticeException(String message) {
        super(message);
    }

    public SystemNoticeException(Throwable cause) {
        super(cause);
    }

    public SystemNoticeException(String message, Throwable cause) {
        super(message, cause);
    }

}
