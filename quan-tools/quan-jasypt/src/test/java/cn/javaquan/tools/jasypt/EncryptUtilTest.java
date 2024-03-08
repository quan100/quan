package cn.javaquan.tools.jasypt;

import org.jasypt.encryption.pbe.StandardPBEStringEncryptor;


public class EncryptUtilTest {

    public static void main(String[] args) {
        decrypt();
    }

    public static void decrypt() {
        String password = "0ygWULnPXh8=";
        String algorithm = "PBEWithMD5AndDES";
        EncryptUtil encryptUtil = new EncryptUtil(password, algorithm);

        String encryptStr = encryptUtil.encrypt("root");
        System.out.println(String.format("密文：ENC(%s)", encryptStr));

        String decryptStr = "ENC(1FWlxMqbk5g8Xalcem+wtA==)";
        decryptStr = encryptUtil.decrypt(decryptStr);
        System.out.println(decryptStr);
    }
}
