package cn.javaquan.tools.mail;

import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.boot.autoconfigure.condition.AnyNestedCondition;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Conditional;
import org.springframework.context.annotation.Configuration;
import org.springframework.mail.javamail.JavaMailSenderImpl;

/**
 * 邮件工具配置.
 *
 * @author javaquan
 * @since 2.2.0
 */
@AutoConfiguration
@EnableConfigurationProperties(MailUtilProperties.class)
public class MailUtilAutoConfiguration {

    /**
     * 邮件工具配置.
     */
    @Configuration(proxyBeanMethods = false)
    @Conditional(MailUtilCondition.class)
    protected static class MailUtilConfiguration {

        /**
         * 邮件工具实例.
         * @param javaMailSender 邮件发送实现的实例
         * @param properties 邮件工具配置
         * @return 邮件工具实例
         */
        @SuppressWarnings("SpringJavaInjectionPointsAutowiringInspection")
        @Bean
        MailUtil mailUtil(JavaMailSenderImpl javaMailSender, MailUtilProperties properties) {
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
