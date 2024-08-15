package cn.javaquan.tools.jasypt;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Jasypt 工具配置.
 *
 * @author javaquan
 * @since 2.2.0
 */
@AutoConfiguration
@ConditionalOnClass({ EncryptUtil.class })
public class JasyptAutoConfiguration {

    /**
     * Jasypt 工具配置.
     */
    @Configuration(proxyBeanMethods = false)
    protected static class JasyptConfiguration {

        /**
         * EncryptUtil 实例.
         * @param password 加密使用的私有密码
         * @param algorithm 加密算法
         * @return 加密工具类实例
         */
        @Bean
        public EncryptUtil encryptUtil(@Value("${jasypt.encryptor.password:}") String password,
                @Value("${jasypt.encryptor.algorithm:PBEWithMD5AndDES}") String algorithm) {
            return new EncryptUtil(password, algorithm);
        }

    }

}
