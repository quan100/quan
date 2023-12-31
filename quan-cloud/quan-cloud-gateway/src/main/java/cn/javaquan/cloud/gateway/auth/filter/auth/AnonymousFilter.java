package cn.javaquan.cloud.gateway.auth.filter.auth;


import cn.javaquan.cloud.gateway.auth.constant.AccessorTypeEnum;
import cn.javaquan.common.base.message.Result;
import cn.javaquan.cloud.gateway.auth.filter.AbstractAuthFilter;

/**
 * 匿名权限过滤器
 *
 * @author wangquan
 * @date 2020/3/11 15:58
 */
public class AnonymousFilter extends AbstractAuthFilter {

    @Override
    public Result doFilter() {
        return Result.success();
    }

    @Override
    public int getFilterType() {
        // 匿名用户
        return AccessorTypeEnum.ANON.getType();
    }
}
