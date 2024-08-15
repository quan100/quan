package cn.javaquan.cloud.gateway.auth.filter.auth;

import cn.javaquan.cloud.gateway.auth.constant.AccessorTypeEnum;
import cn.javaquan.common.base.message.Result;
import cn.javaquan.security.common.dto.AccessorInfo;
import org.springframework.http.HttpStatus;
import org.springframework.util.CollectionUtils;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * 按钮权限过滤器.
 * <p>
 * 该权限将校验用户是否拥有按钮权限
 *
 * @author wangquan
 * @since 1.0.0
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
                return Result.fail(HttpStatus.FORBIDDEN.value(), "无权限！");
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
        List<Map<String, Object>> roles = (List<Map<String, Object>>) accessorInfo.getRoles();

        if (CollectionUtils.isEmpty(roles)) {
            return false;
        }
        List<Object> roleCode = roles.stream().map(role -> role.get("code")).collect(Collectors.toList());

        // 校验角色满足权限必须的校验
        for (String role : permRoles) {
            if (roleCode.contains(role)) {
                return true;
            }
        }
        return false;
    }

}
