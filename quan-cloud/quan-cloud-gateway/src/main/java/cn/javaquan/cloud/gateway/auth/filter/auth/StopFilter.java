package cn.javaquan.cloud.gateway.auth.filter.auth;

import cn.javaquan.cloud.gateway.auth.constant.AccessorTypeEnum;
import cn.javaquan.common.base.message.Result;
import cn.javaquan.cloud.gateway.auth.filter.AbstractAuthFilter;
import org.springframework.http.HttpStatus;

/**
 * 无权过滤器.
 * <p>
 * 使用该过滤器，将直接阻止任何入站访问
 *
 * @author wangquan
 * @since 1.0.0
 */
public class StopFilter extends AbstractAuthFilter {

    @Override
    public Result doFilter() {
        // 没有权限
        return Result.fail(HttpStatus.UNAUTHORIZED.value(), "无访问权限！");
    }

    @Override
    public int getFilterType() {
        // 任何用户
        return AccessorTypeEnum.ALL.getType();
    }

}
