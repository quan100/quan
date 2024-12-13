package cn.javaquan.tools.crypto;

/**
 * 生成账号密码. 用于测试环境的密码生成.
 *
 * @author javaquan
 * @since 2.3.1
 */
public class PasswordServiceTests {

    /**
     * 生成账号密码.
     * @param args
     */
    public static void main(String[] args) {
        PasswordService passwordService = new PasswordService(null, new CryptoProperties());
        // 传入账号、明文登录密码
        CryptoParam cryptoParam = passwordService.encryptPassword("admin", "123456");
        System.out.println(cryptoParam.toString());
    }

}
