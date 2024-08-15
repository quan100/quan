package cn.javaquan.tools.jasypt;

import org.jasypt.encryption.pbe.StandardPBEStringEncryptor;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 配置加密工具类.
 *
 * @author javaquan
 * @since 2.2.0
 */
public class EncryptUtil {

    private static final String DEFAULT_PREFIX = "ENC";

    private static final Pattern DEFAULT_PATTERN = Pattern.compile("(?<=\\()[^)]+");

    static StandardPBEStringEncryptor standardPBEStringEncryptor;

    /**
     * 构造方法.
     * @param password 加密密码.
     * @param algorithm 加密算法
     */
    public EncryptUtil(String password, String algorithm) {
        standardPBEStringEncryptor = new StandardPBEStringEncryptor();
        standardPBEStringEncryptor.setAlgorithm(algorithm);
        // 设置加密密码
        standardPBEStringEncryptor.setPassword(password);
    }

    /**
     * 加密.
     * @param encryptStr 待加密的字符串
     * @return 加密后的字符串
     */
    public static String encrypt(String encryptStr) {
        return standardPBEStringEncryptor.encrypt(encryptStr);
    }

    /**
     * 解密.
     * @param decryptStr 待解密的字符串
     * @return 解密后的字符串
     */
    public static String decrypt(String decryptStr) {
        return decrypt(decryptStr, DEFAULT_PREFIX);
    }

    /**
     * 解密.
     * @param decryptStr 待解密的字符串
     * @param prefix 需要剔除的字符串前缀
     * @return 解密后的字符串
     */
    private static String decrypt(String decryptStr, String prefix) {
        if (decryptStr.startsWith(prefix)) {
            decryptStr = decryptStr.replace(prefix, "");
            Matcher matcher = DEFAULT_PATTERN.matcher(decryptStr);
            if (matcher.find()) {
                decryptStr = matcher.group();
            }
            return standardPBEStringEncryptor.decrypt(decryptStr);
        }
        return decryptStr;
    }

}
