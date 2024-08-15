package cn.javaquan.tools.crypto;

import lombok.Data;

import java.io.Serializable;

/**
 * 密码生成参数.
 *
 * @author javaquan
 * @since 2.2.0
 */
@Data
public class CryptoParam implements Serializable {

    private static final long serialVersionUID = -1977352420642886176L;

    /**
     * 账号.
     */
    private String account;

    /**
     * 密码.
     */
    private String password;

    /**
     * 盐.
     */
    private String salt;

    /**
     * 密码.
     */
    private String secret;

    /**
     * 登录ip.
     */
    private String ip;

}
