package cn.javaquan.tools.notify;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.lang.Nullable;

import java.util.Map;

/**
 * Notice 信息通知工具配置
 *
 * @author javaquan
 * @since 2.2.0
 */
@RequiredArgsConstructor
@AutoConfiguration
public class NotifyAutoConfiguration {

    @Bean
    @ConditionalOnMissingBean
    public SystemExceptionNotificationExecutor noticeExecutor(@Value("${quan.tools.notify.printStackTrace:false}") Boolean printStackTrace, @Nullable Map<String, ISystemExceptionNotification> notificationInterfaceContainer) {
        SystemExceptionNotificationExecutor noticeExecutor = new SystemExceptionNotificationExecutor();
        noticeExecutor.setNotificationInterfaceContainer(notificationInterfaceContainer);
        noticeExecutor.setPrintStackTrace(printStackTrace);
        return noticeExecutor;
    }

}
