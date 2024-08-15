package cn.javaquan.tools.notify;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.util.StringUtils;

/**
 * Configuration properties for notify support.
 *
 * @author javaquan
 * @since 1.0.0
 */
@Slf4j
@Data
@ConfigurationProperties(prefix = "quan.tools.notify")
public class NotifyProperties implements InitializingBean {

    /**
     * 是否打印堆栈信息.
     */
    private boolean printStackTrace;

    /**
     * 是否开启消息通知推送.
     * <p>
     * 当开启通知时，需实现 {@link ISystemExceptionNotification} 接口，并注册到 Spring 容器中。
     */
    private boolean enabled;

    /**
     * 使用 Webhook 地址，向钉钉群推送消息.
     */
    private String exceptionWebhook;

    /**
     * 通知信息标签配置.
     */
    private String tag;

    private void determineDefaultExceptionWebhook() {
        if (isEnabled()) {
            if (!StringUtils.hasText(this.exceptionWebhook)) {
                log.warn("exceptionWebhook must not be empty");
            }
        }
    }

    @Override
    public void afterPropertiesSet() {
        determineDefaultExceptionWebhook();
    }

}
