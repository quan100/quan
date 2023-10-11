package com.quan.tools.feign.autoconfigure;

import com.quan.tools.feign.INoticeExecutor;
import com.quan.tools.feign.NoticeExecutor;
import com.quan.tools.feign.QuanFeignInvocationHandler;
import feign.Feign;
import feign.Retryer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.lang.Nullable;

@ConditionalOnProperty(name = "quan.tools.feign.enabled", havingValue = "true")
@Configuration
public class QuanFeignConfiguration {

    @Bean
    @ConditionalOnMissingBean
    public Feign.Builder quanFeignInvocationHandlerBuilder(NoticeExecutor noticeExecutor, @Nullable Retryer retryer) {
        if (null == retryer) {
            retryer = Retryer.NEVER_RETRY;
        }
        Feign.Builder builder = Feign.builder().retryer(retryer);
        builder.invocationHandlerFactory((target, dispatch) -> new QuanFeignInvocationHandler(target, dispatch, noticeExecutor));
        return builder;
    }

    @Bean
    @ConditionalOnMissingBean
    public NoticeExecutor noticeExecutor(@Value("quan.tools.feign.printStackTrace:false") Boolean printStackTrace, @Nullable INoticeExecutor executor) {
        NoticeExecutor noticeExecutor = new NoticeExecutor();
        noticeExecutor.setNoticeExecutor(executor);
        noticeExecutor.setPrintStackTrace(printStackTrace);
        return noticeExecutor;
    }

}
