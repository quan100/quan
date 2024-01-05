package cn.javaquan.tools.crypto;

import java.io.Serializable;

/**
 * 密码生成参数
 *
 * @author javaquan
 * @since 2.2.0
 */
public class CryptoParam implements Serializable {
    private static final long serialVersionUID = -1977352420642886176L;

    /**
     * 账号
     */
    private String account;

    /**
     * 密码
     */
    private String password;

    /**
     * 盐
     */
    private String salt;

    /**
     * 密码
     */
    private String secret;

    /**
     * 登录ip
     */
    private String ip;

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getSalt() {
        return salt;
    }

    public void setSalt(String salt) {
        this.salt = salt;
    }

    public String getSecret() {
        return secret;
    }

    public void setSecret(String secret) {
        this.secret = secret;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    @Override
    public String toString() {
        return "CryptoParam{" +
                "account='" + account + '\'' +
                ", password='" + password + '\'' +
                ", salt='" + salt + '\'' +
                ", secret='" + secret + '\'' +
                ", ip='" + ip + '\'' +
                '}';
    }
}
