package com.quan.tools.jasypt;

import org.jasypt.encryption.pbe.StandardPBEStringEncryptor;

/**
 * 配置加密测试类
 *
 * @author javaquan
 * @since 1.0.0
 */
public class StandardPBEStringEncryptorUtil {
    public static void main(String[] args) {
        StandardPBEStringEncryptor standardPBEStringEncryptor = new StandardPBEStringEncryptor();
        standardPBEStringEncryptor.setAlgorithm("PBEWithMD5AndDES");
        standardPBEStringEncryptor.setPassword("0ygWULnPXh8=");
        String encryptStr = standardPBEStringEncryptor.encrypt("root");
        System.out.println(String.format("密文：ENC(%s)", encryptStr));
    }

}
