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
     * 默认数据包最大长度 64kb.
     */
    private static final int MAX_FRAME_SIZE = 65536;

    /**
     * 默认的消息体最大长度 64kb.
     */
    private static final int MAX_CONTENT_LENGTH = 65536;

    /**
     * 空闲检查时间，单位：秒.
     */
    private static final long READER_IDLE_TIME = 600L;

    /**
     * 开启IM服务的端口.
     */
    private Integer port;

    /**
     * SSL配置.
     */
    private Ssl ssl;

    /**
     * websocket 路径.
     */
    private String websocketPath;

    /**
     * 数据包最大长度 单位：字节.
     */
    private Integer maxFrameSize;

    /**
     * 消息体最大长度 单位：字节.
     */
    private Integer maxContentLength;

    /**
     * 允许连接空闲的最大时间.
     * <p>
     * 当空闲超过最大时间后，强制下线
     */
    private Long readerIdleTime;

    /**
     * 获取端口.
     * @return 端口
     */
    public Integer getPort() {
        return this.port;
    }

    /**
     * 设置端口.
     * @param port 端口
     */
    public void setPort(Integer port) {
        this.port = port;
    }

    /**
     * 确定默认的端口.
     * @return 端口
     */
    public int determineDefaultPort() {
        Assert.notNull(this.port,
                "[Assertion failed chat server port] - this numeric argument must have value; it must not be null");
        return this.port;
    }

    /**
     * 获取ssl配置.
     * @return ssl配置
     */
    public Ssl getSsl() {
        return this.ssl;
    }

    /**
     * 设置ssl配置.
     * @param ssl ssl配置
     */
    public void setSsl(Ssl ssl) {
        this.ssl = ssl;
    }

    /**
     * 获取websocket路径.
     * @return websocket路径
     */
    public String getWebsocketPath() {
        return this.websocketPath;
    }

    /**
     * 设置websocket路径.
     * @param websocketPath websocket路径
     */
    public void setWebsocketPath(String websocketPath) {
        this.websocketPath = websocketPath;
    }

    /**
     * 确定默认的websocket路径.
     * @return websocket路径
     */
    public String determineDefaultWebsocketPath() {
        Assert.hasText(this.websocketPath,
                "[Assertion failed chat server websocketPath] - it must not be null or empty");
        return this.websocketPath;
    }

    /**
     * 获取数据包最大长度.
     * @return 数据包最大长度
     */
    public Integer getMaxFrameSize() {
        return this.maxFrameSize;
    }

    /**
     * 设置数据包最大长度.
     * @param maxFrameSize 数据包最大长度
     */
    public void setMaxFrameSize(Integer maxFrameSize) {
        this.maxFrameSize = maxFrameSize;
    }

    /**
     * 确定默认的数据包最大长度.
     * @return 数据包最大长度
     */
    public Integer determineDefaultMaxFrameSize() {
        if (null == this.maxFrameSize) {
            this.setMaxFrameSize(MAX_FRAME_SIZE);
        }
        return this.maxFrameSize;
    }

    /**
     * 获取消息体最大长度.
     * @return 消息体最大长度
     */
    public Integer getMaxContentLength() {
        return this.maxContentLength;
    }

    /**
     * 设置消息体最大长度.
     * @param maxContentLength 消息体最大长度
     */
    public void setMaxContentLength(Integer maxContentLength) {
        this.maxContentLength = maxContentLength;
    }

    /**
     * 确定默认的消息体最大长度.
     * @return 消息体最大长度
     */
    public Integer determineDefaultMaxContentLength() {
        if (null == this.maxContentLength) {
            this.setMaxContentLength(MAX_CONTENT_LENGTH);
        }
        return this.maxContentLength;
    }

    /**
     * 获取允许连接空闲的最大时间.
     * @return 允许连接空闲的最大时间
     */
    public Long getReaderIdleTime() {
        return this.readerIdleTime;
    }

    /**
     * 设置允许连接空闲的最大时间.
     * @param readerIdleTime 允许连接空闲的最大时间
     */
    public void setReaderIdleTime(Long readerIdleTime) {
        this.readerIdleTime = readerIdleTime;
    }

    /**
     * 确定默认的允许连接空闲的最大时间.
     * @return 允许连接空闲的最大时间
     */
    public Long determineDefaultReaderIdleTime() {
        if (null == this.readerIdleTime) {
            this.setReaderIdleTime(READER_IDLE_TIME);
        }
        return this.readerIdleTime;
    }

    /**
     * 初始化配置之后执行.
     */
    public void afterPropertiesSet() {
        determineDefaultPort();
        determineDefaultWebsocketPath();
        determineDefaultMaxFrameSize();
        determineDefaultMaxContentLength();
        determineDefaultReaderIdleTime();
    }

    /**
     * ssl properties.
     */
    public static class Ssl {

        /**
         * 是否启用 ssl.
         */
        private boolean enabled = false;

        /**
         * SSL协议.
         */
        private String protocol = "TLS";

        /**
         * an X.509 certificate chain file in PEM format.
         */
        private String keyCertChainFilePath;

        /**
         * a PKCS#8 private key file in PEM format.
         */
        private String keyFilePath;

        /**
         * 是否启用 ssl.
         * @return 是否启用 ssl
         */
        public boolean isEnabled() {
            return this.enabled;
        }

        /**
         * 设置是否启用 ssl.
         * @param enabled 是否启用ssl
         */
        public void setEnabled(boolean enabled) {
            this.enabled = enabled;
        }

        /**
         * 获取 SSL 协议.
         * @return ssl协议
         */
        public String getProtocol() {
            return this.protocol;
        }

        /**
         * 设置 SSL协议.
         * @param protocol ssl协议
         */
        public void setProtocol(String protocol) {
            this.protocol = protocol;
        }

        /**
         * get an X.509 certificate chain file in PEM format.
         * @return an X.509 certificate chain file in PEM format.
         */
        public String getKeyCertChainFilePath() {
            return this.keyCertChainFilePath;
        }

        /**
         * set an X.509 certificate chain file in PEM format.
         * @param keyCertChainFilePath an X.509 certificate chain file in PEM format.
         */
        public void setKeyCertChainFilePath(String keyCertChainFilePath) {
            this.keyCertChainFilePath = keyCertChainFilePath;
        }

        /**
         * determine an X.509 certificate chain file in PEM format.
         * @return an X.509 certificate chain file in PEM format.
         */
        public String determineDefaultKeyCertChainFilePath() {
            Assert.hasText(this.keyCertChainFilePath,
                    "[Assertion failed chat server keyCertChainFilePath] - it must not be null or empty");
            return this.keyCertChainFilePath;
        }

        /**
         * get a PKCS#8 private key file in PEM format.
         * @return a PKCS#8 private key file in PEM format.
         */
        public String getKeyFilePath() {
            return this.keyFilePath;
        }

        /**
         * set a PKCS#8 private key file in PEM format.
         * @param keyFilePath a PKCS#8 private key file in PEM format.
         */
        public void setKeyFilePath(String keyFilePath) {
            this.keyFilePath = keyFilePath;
        }

        /**
         * determine a PKCS#8 private key file in PEM format.
         * @return a PKCS#8 private key file in PEM format.
         */
        public String determineDefaultKeyFilePath() {
            Assert.hasText(this.keyFilePath,
                    "[Assertion failed chat server keyFilePath] - it must not be null or empty");
            return this.keyFilePath;
        }

    }

}
