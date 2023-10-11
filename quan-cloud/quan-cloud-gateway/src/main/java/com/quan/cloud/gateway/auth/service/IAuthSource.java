package com.quan.cloud.gateway.auth.service;

import java.util.List;

/**
 * 权限获取服务配置
 *
 * @author wangquan
 */
public interface IAuthSource {

    /**
     * 获取资源权限
     * 格式：资源（请求的url）=权限 多个权限用逗号隔开
     * 示例：/user/token=user,ip,anon
     *
     * @return
     */
    List<String> getAuth();
}
