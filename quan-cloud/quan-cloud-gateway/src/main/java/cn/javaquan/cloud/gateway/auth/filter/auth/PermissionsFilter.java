package cn.javaquan.cloud.gateway.auth.filter.auth;

import cn.javaquan.cloud.gateway.auth.constant.AccessorTypeEnum;
import cn.javaquan.cloud.gateway.auth.constant.HttpStatusErrorEnum;
import cn.javaquan.common.base.message.Result;
import cn.javaquan.security.common.dto.AccessorInfo;
import org.springframework.util.CollectionUtils;

import java.util.List;
import java.util.Map;

/**
 * 系统权限过滤器.
 * <p>
 * 该权限过滤器将校验用户是否拥有对应的 url 权限.
 * <p>
 * 通过角色配置，当用户拥有该角色时，则拥有该角色对应 url 的权限.
 *
 * @author wangquan
 * @since 2.3.1
 */
public class PermissionsFilter extends UserFilter {

    @Override
    public Result doFilter() {
        return super.doFilter();
    }

    @Override
    public Result doFilter(List<String> roles) {
        Result<AccessorInfo> result = doFilter();
        if (result.isSuccess()) {
            // 校验角色是否拥有当前访问权限
            if (!validRole(result.getData(), roles)) {
                return Result.fail(HttpStatusErrorEnum.FORBIDDEN);
            }
        }
        return result;
    }

    @Override
    public int getFilterType() {
        return AccessorTypeEnum.AUTHENTICATED.getType();
    }

    /**
     * 校验角色.
     * @param accessorInfo 认证用户角色信息
     * @param permRoles 权限角色（当角色授权时配置）
     * @return true：校验通过，false：校验失败
     */
    public boolean validRole(AccessorInfo accessorInfo, List<String> permRoles) {
        // 所有角色必须配置授权，否则禁止访问需登录的接口
        if (CollectionUtils.isEmpty(permRoles)) {
            return false;
        }
        List<Map<String, Object>> userRoles = (List<Map<String, Object>>) accessorInfo.getRoles();

        if (CollectionUtils.isEmpty(userRoles)) {
            return false;
        }

        // 校验当前链接访问的角色是否包含在用户角色中
        for (String permRole : permRoles) {
            if (userRoles.stream().anyMatch(userRole -> permRole.equals(userRole.get("code")))) {
                return true;
            }
        }
        return false;
    }

}
