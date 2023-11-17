package cn.javaquan.tools.chat.autoconfigure;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.util.Assert;

/**
 * Configuration properties for im support.
 *
 * @author javaquan
 * @since 1.0.0
 */
@ConfigurationProperties(prefix = "quan.im")
public class ChatProperties {

    /**
     * 默认数据包最大长度
     * 64kb
     */
    private final static int MAX_FRAME_SIZE = 65536;

    /**
     * 默认的消息体最大长度
     * 64kb
     */
    private final static int MAX_CONTENT_LENGTH = 65536;

    /**
     * 空闲检查时间，单位：秒
     */
    private final static long READER_IDLE_TIME = 600L;

    /**
     * 开启IM服务的端口
     */
    private Integer port;

    /**
     * SSL配置
     */
    private Ssl ssl;

    /**
     * websocket 路径
     */
    private String websocketPath;

    /**
     * 数据包最大长度
     * 单位：字节
     */
    private Integer maxFrameSize;

    /**
     * 消息体最大长度
     * 单位：字节
     */
    private Integer maxContentLength;

    /**
     * 允许连接空闲的最大时间
     * <p>
     * 当空闲超过最大时间后，强制下线
     */
    private Long readerIdleTime;

    public Integer getPort() {
        return port;
    }

    public void setPort(Integer port) {
        this.port = port;
    }

    public int determineDefaultPort() {
        Assert.notNull(this.port, "[Assertion failed chat server port] - this numeric argument must have value; it must not be null");
        return this.port;
    }

    public Ssl getSsl() {
        return ssl;
    }

    public void setSsl(Ssl ssl) {
        this.ssl = ssl;
    }

    public String getWebsocketPath() {
        return websocketPath;
    }

    public void setWebsocketPath(String websocketPath) {
        this.websocketPath = websocketPath;
    }

    public String determineDefaultWebsocketPath() {
        Assert.hasText(this.websocketPath, "[Assertion failed chat server websocketPath] - it must not be null or empty");
        return this.websocketPath;
    }

    public Integer getMaxFrameSize() {
        return maxFrameSize;
    }

    public void setMaxFrameSize(Integer maxFrameSize) {
        this.maxFrameSize = maxFrameSize;
    }

    public Integer determineDefaultMaxFrameSize() {
        if (null == maxFrameSize) {
            this.setMaxFrameSize(MAX_FRAME_SIZE);
        }
        return this.maxFrameSize;
    }

    public Integer getMaxContentLength() {
        return maxContentLength;
    }

    public void setMaxContentLength(Integer maxContentLength) {
        this.maxContentLength = maxContentLength;
    }

    public Integer determineDefaultMaxContentLength() {
        if (null == maxContentLength) {
            this.setMaxContentLength(MAX_CONTENT_LENGTH);
        }
        return this.maxContentLength;
    }

    public Long getReaderIdleTime() {
        return readerIdleTime;
    }

    public void setReaderIdleTime(Long readerIdleTime) {
        this.readerIdleTime = readerIdleTime;
    }

    public Long determineDefaultReaderIdleTime() {
        if (null == readerIdleTime) {
            this.setReaderIdleTime(READER_IDLE_TIME);
        }
        return this.readerIdleTime;
    }

    /**
     * ssl properties.
     */
    public static class Ssl {

        private boolean enabled = false;
        private String protocol = "TLS";

        /**
         * an X.509 certificate chain file in PEM format
         */
        private String keyCertChainFilePath;

        /**
         * a PKCS#8 private key file in PEM format
         */
        private String keyFilePath;

        public boolean isEnabled() {
            return enabled;
        }

        public void setEnabled(boolean enabled) {
            this.enabled = enabled;
        }

        public String getProtocol() {
            return protocol;
        }

        public void setProtocol(String protocol) {
            this.protocol = protocol;
        }

        public String getKeyCertChainFilePath() {
            return keyCertChainFilePath;
        }

        public void setKeyCertChainFilePath(String keyCertChainFilePath) {
            this.keyCertChainFilePath = keyCertChainFilePath;
        }

        public String determineDefaultKeyCertChainFilePath() {
            Assert.hasText(this.keyCertChainFilePath, "[Assertion failed chat server keyCertChainFilePath] - it must not be null or empty");
            return this.keyCertChainFilePath;
        }

        public String getKeyFilePath() {
            return keyFilePath;
        }

        public void setKeyFilePath(String keyFilePath) {
            this.keyFilePath = keyFilePath;
        }

        public String determineDefaultKeyFilePath() {
            Assert.hasText(this.keyFilePath, "[Assertion failed chat server keyFilePath] - it must not be null or empty");
            return this.keyFilePath;
        }

    }

    public void afterPropertiesSet() {
        determineDefaultPort();
        determineDefaultWebsocketPath();
        determineDefaultMaxFrameSize();
        determineDefaultMaxContentLength();
        determineDefaultReaderIdleTime();
    }
}
