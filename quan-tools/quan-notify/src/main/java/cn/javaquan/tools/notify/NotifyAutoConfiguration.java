package cn.javaquan.tools.notify;

import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.boot.autoconfigure.condition.AnyNestedCondition;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Conditional;
import org.springframework.context.annotation.Configuration;
import org.springframework.lang.Nullable;

import java.util.Map;

/**
 * Notify 信息通知工具配置
 *
 * @author javaquan
 * @since 2.2.0
 */
@AutoConfiguration
@EnableConfigurationProperties(NotifyProperties.class)
public class NotifyAutoConfiguration {

    @Configuration(proxyBeanMethods = false)
    @Conditional(NotifyCondition.class)
    protected static class NotifyConfiguration {

        @Bean
        @ConditionalOnMissingBean
        public SystemExceptionNotificationExecutor systemExceptionNotificationExecutor(NotifyProperties properties, @Nullable Map<String, ISystemExceptionNotification> notificationInterfaceContainer) {
            SystemExceptionNotificationExecutor noticeExecutor = new SystemExceptionNotificationExecutor();
            noticeExecutor.setNotificationInterfaceContainer(notificationInterfaceContainer);
            noticeExecutor.setPrintStackTrace(properties.isPrintStackTrace());
            return noticeExecutor;
        }
    }

    static class NotifyCondition extends AnyNestedCondition {

        NotifyCondition() {
            super(ConfigurationPhase.PARSE_CONFIGURATION);
        }

        @ConditionalOnProperty(prefix = "quan.tools.notify", name = "enabled", havingValue = "true")
        static class EnabledProperty {

        }

    }

}
