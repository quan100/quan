package cn.javaquan.tools.chat.autoconfigure;

import cn.javaquan.tools.chat.ChatServerApplication;
import cn.javaquan.tools.chat.core.ChannelPool;
import cn.javaquan.tools.chat.server.ChatServer;
import cn.javaquan.tools.chat.server.SecureChatServer;
import io.netty.channel.Channel;
import io.netty.handler.ssl.SslContext;
import io.netty.handler.ssl.SslContextBuilder;
import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.boot.autoconfigure.condition.AnyNestedCondition;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Conditional;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

import java.io.File;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * im聊天sdk配置
 *
 * @author javaquan
 * @since 1.0.0
 */
@AutoConfiguration
@EnableConfigurationProperties(ChatProperties.class)
public class ChatAutoConfiguration {

    @Import(ChatServerApplication.class)
    @Configuration(proxyBeanMethods = false)
    @Conditional(ChatCondition.class)
    protected static class ChatConfiguration {

        @ConditionalOnProperty(prefix = "quan.im.ssl", name = "enabled", havingValue = "false", matchIfMissing = true)
        @ConditionalOnMissingBean
        @Bean
        ChatServer chatServer() {
            return new ChatServer();
        }

        @ConditionalOnMissingBean
        @Bean
        ChannelPool channelPool() {
            Map<String, Channel> channelContainer = new ConcurrentHashMap<>();
            return new ChannelPool(channelContainer);
        }

    }

    static class ChatCondition extends AnyNestedCondition {

        ChatCondition() {
            super(ConfigurationPhase.PARSE_CONFIGURATION);
        }

        @ConditionalOnProperty(prefix = "quan.im", name = "port")
        static class PortProperty {

        }

        @ConditionalOnProperty(prefix = "quan.im.ssl", name = "enabled", havingValue = "true")
        @ConditionalOnMissingBean
        @Bean
        SslContext sslContext(ChatProperties properties) throws Exception {
            ChatProperties.Ssl ssl = properties.getSsl();
            File keyCertChainFile = new File(ssl.determineDefaultKeyCertChainFilePath());
            File keyFile = new File(ssl.determineDefaultKeyFilePath());
            return SslContextBuilder.forServer(keyCertChainFile, keyFile).build();
        }

        @ConditionalOnProperty(prefix = "quan.im.ssl", name = "enabled", havingValue = "true")
        @ConditionalOnMissingBean
        @Bean
        ChatServer secureChatServer(SslContext context) {
            return new SecureChatServer(context);
        }
    }

}
