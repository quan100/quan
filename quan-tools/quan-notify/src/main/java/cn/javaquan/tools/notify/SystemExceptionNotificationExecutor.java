package cn.javaquan.tools.notify;

import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.util.CollectionUtils;

import java.util.Map;

/**
 * 系统异常信息通知接口执行器.
 *
 * @author javaquan
 * @since 2.2.0
 */
@Slf4j
public class SystemExceptionNotificationExecutor implements InitializingBean {

    @Setter
    private Map<String, ISystemExceptionNotification> notificationInterfaceContainer;

    @Setter
    private boolean printStackTrace;

    private static SystemExceptionNotificationExecutor INSTANCE;

    /**
     * 默认构造函数.
     */
    public SystemExceptionNotificationExecutor() {
        INSTANCE = this;
    }

    /**
     * 推送错误信息.
     * @param e 异常信息
     */
    public static void send(Throwable e) {
        if (INSTANCE != null) {
            INSTANCE.sendErrorMessage(e);
        }
    }

    /**
     * 推送错误信息.
     * @param e 异常信息
     */
    public void sendErrorMessage(Throwable e) {
        if (!CollectionUtils.isEmpty(this.notificationInterfaceContainer)) {
            this.notificationInterfaceContainer.values()
                .forEach(notificationInterface -> notificationInterface.send(e));
        }
        if (this.printStackTrace) {
            log.error(e.getMessage(), e);
        }
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        if (CollectionUtils.isEmpty(this.notificationInterfaceContainer)) {
            log.warn("Notification interface container is null");
        }
    }

}
