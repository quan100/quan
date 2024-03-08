package cn.javaquan.tools.jasypt;

import org.jasypt.encryption.pbe.StandardPBEStringEncryptor;

/**
 * 配置加密工具类
 *
 * @author javaquan
 * @since 2.2.0
 */
public class EncryptUtil {

    private final static String DEFAULT_PREFIX = "ENC";

    static StandardPBEStringEncryptor standardPBEStringEncryptor;

    /**
     * @param password  加密密码
     * @param algorithm 加密算法
     */
    public EncryptUtil(String password, String algorithm) {
        standardPBEStringEncryptor = new StandardPBEStringEncryptor();
        standardPBEStringEncryptor.setAlgorithm(algorithm);
        // 设置加密密码
        standardPBEStringEncryptor.setPassword(password);
    }

    /**
     * 加密
     *
     * @param encryptStr
     * @return
     */
    public static String encrypt(String encryptStr) {
        return standardPBEStringEncryptor.encrypt(encryptStr);
    }

    /**
     * 解密
     *
     * @param decryptStr
     * @return
     */
    public static String decrypt(String decryptStr) {
        return decrypt(decryptStr, DEFAULT_PREFIX);
    }

    /**
     * 解密
     *
     * @param decryptStr
     * @return
     */
    public static String decrypt(String decryptStr, String prefix) {
        if (decryptStr.startsWith(prefix)) {
            return standardPBEStringEncryptor.decrypt(decryptStr);
        }
        return decryptStr;
    }
}
