package cn.javaquan.tools.chat;

import cn.javaquan.tools.chat.autoconfigure.ChatProperties;
import cn.javaquan.tools.chat.server.ChatServer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;

/**
 * chat服务启动.
 *
 * @author javaquan
 * @since 1.0.0
 */
public class ChatServerApplication implements ApplicationRunner {

    @Autowired
    private ChatServer chatServer;

    @Autowired
    private ChatProperties properties;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        this.properties.afterPropertiesSet();
        this.chatServer.start(this.properties);
    }

}
