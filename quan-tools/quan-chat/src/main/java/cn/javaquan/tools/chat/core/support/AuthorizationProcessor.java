package cn.javaquan.tools.chat.core.support;


/**
 * 授权凭证处理器
 *
 * @author wangquan
 */
public interface AuthorizationProcessor {

    /**
     * 检查权限
     *
     * @param authorization 登录凭证
     * @return
     */
    boolean checkAuth(String authorization);

}
