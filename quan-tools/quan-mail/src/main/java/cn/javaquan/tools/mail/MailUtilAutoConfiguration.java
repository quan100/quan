package cn.javaquan.tools.mail;

import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.boot.autoconfigure.condition.AnyNestedCondition;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Conditional;
import org.springframework.context.annotation.Configuration;
import org.springframework.lang.Nullable;
import org.springframework.mail.javamail.JavaMailSenderImpl;

/**
 * 邮件工具配置
 *
 * @author javaquan
 * @since 2.2.0
 */
@AutoConfiguration
@EnableConfigurationProperties(MailUtilProperties.class)
public class MailUtilAutoConfiguration {

    @Configuration(proxyBeanMethods = false)
    @Conditional(MailUtilCondition.class)
    protected static class MailUtilConfiguration {

        @Bean
        MailUtil mailUtil(@Nullable JavaMailSenderImpl javaMailSender, MailUtilProperties properties) {
            return new MailUtil(javaMailSender, properties);
        }

    }

    static class MailUtilCondition extends AnyNestedCondition {

        MailUtilCondition() {
            super(ConfigurationPhase.PARSE_CONFIGURATION);
        }

        @ConditionalOnProperty(prefix = "spring.mail", name = "host")
        static class HostProperty {

        }

    }

}
