package cn.javaquan.cloud.gateway.auth.service;

import java.util.List;

/**
 * 权限获取服务配置.
 *
 * @author wangquan
 * @since 1.0.0
 */
public interface IAuthSource {

    /**
     * 获取资源权限.
     * <p>
     * 格式：资源（请求的url）=权限 多个权限用逗号隔开 示例：/user/token=user,ip,anon
     * @return 权限配置
     */
    List<String> getAuth();

}
