package cn.javaquan.tools.crypto;

import cn.javaquan.tools.crypto.credential.RetryLimitHashedCredentialsMatcher;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.crypto.RandomNumberGenerator;
import org.apache.shiro.crypto.SecureRandomNumberGenerator;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.util.ByteSource;

/**
 * 密码生成工具类
 * <p>
 * 借助shiro
 *
 * @author javaquan
 * @since 2.2.0
 */
public class PasswordService {

    private RandomNumberGenerator randomNumberGenerator = new SecureRandomNumberGenerator();

    private final RetryLimitHashedCredentialsMatcher retryLimitHashedCredentialsMatcher;
    private final CryptoProperties properties;

    public PasswordService(RetryLimitHashedCredentialsMatcher retryLimitHashedCredentialsMatcher, CryptoProperties properties) {
        this.retryLimitHashedCredentialsMatcher = retryLimitHashedCredentialsMatcher;
        this.properties = properties;
    }

    /**
     * 生成凭证
     *
     * @param account
     * @param password
     * @return
     */
    public CryptoParam encryptPassword(String account, String password) {
        CryptoParam cryptoParam = new CryptoParam();
        cryptoParam.setPassword(password);
        cryptoParam.setAccount(account);

        encryptPassword(cryptoParam);
        return cryptoParam;
    }

    /**
     * 生成凭证
     *
     * @param cryptoParam
     */
    public void encryptPassword(CryptoParam cryptoParam) {
        String salt = randomNumberGenerator.nextBytes().toHex();
        cryptoParam.setSalt(salt);
        String secret = new SimpleHash(properties.determineDefaultAlgorithmName(), cryptoParam.getPassword(), getByteSource(cryptoParam), properties.determineDefaultHashIterations()).toHex();
        cryptoParam.setSecret(secret);
        cryptoParam.setPassword(null);
    }

    /**
     * 密码校验
     *
     * @param cryptoParam
     * @return
     */
    @Deprecated
    public boolean validPassword(CryptoParam cryptoParam) {
        // 创建认证信息和令牌
        UsernamePasswordToken token = new UsernamePasswordToken(cryptoParam.getAccount(), cryptoParam.getPassword());
        SimpleAuthenticationInfo info = new SimpleAuthenticationInfo(cryptoParam.getAccount(), cryptoParam.getSecret(), getByteSource(cryptoParam), properties.determineDefaultRealmName());
        // 创建匹配器
        HashedCredentialsMatcher hashedCredentialsMatcher = new HashedCredentialsMatcher();
        // 设置算法
        hashedCredentialsMatcher.setHashAlgorithmName(properties.determineDefaultAlgorithmName());
        // 设置迭代次数
        hashedCredentialsMatcher.setHashIterations(properties.determineDefaultHashIterations());
        try {
            return hashedCredentialsMatcher.doCredentialsMatch(token, info);
        } catch (Exception e) {
            return false;
        }
    }

    /**
     * 凭证校验
     *
     * @param cryptoParam
     * @return
     */
    public boolean verify(CryptoParam cryptoParam) {
        return retryLimitHashedCredentialsMatcher.verify(cryptoParam);
    }

    private ByteSource getByteSource(CryptoParam cryptoParam) {
        return ByteSource.Util.bytes(cryptoParam.getAccount() + cryptoParam.getSalt());
    }
}
