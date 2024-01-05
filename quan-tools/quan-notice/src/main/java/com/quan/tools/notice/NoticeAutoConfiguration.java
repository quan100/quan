package com.quan.tools.notice;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.lang.Nullable;

/**
 * JmsUtil工具配置
 *
 * @author javaquan
 * @since 2.2.0
 */
@AutoConfiguration
public class NoticeAutoConfiguration {

    @Bean
    @ConditionalOnMissingBean
    public NoticeExecutor noticeExecutor(@Value("${quan.tools.notice.printStackTrace:false}") Boolean printStackTrace, @Nullable INoticeExecutor executor) {
        NoticeExecutor noticeExecutor = new NoticeExecutor();
        noticeExecutor.setNoticeExecutor(executor);
        noticeExecutor.setPrintStackTrace(printStackTrace);
        return noticeExecutor;
    }

}
