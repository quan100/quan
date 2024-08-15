package cn.javaquan.tools.chat.core.support;

/**
 * 授权凭证处理器.
 *
 * @author wangquan
 * @since 1.0.0
 */
public interface AuthorizationProcessor {

    /**
     * 检查权限.
     * @param authorization 登录凭证
     * @return 权限验证结果
     */
    boolean checkAuth(String authorization);

}
