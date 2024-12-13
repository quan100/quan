package cn.javaquan.cloud.gateway.auth.filter.auth;

import cn.javaquan.cloud.gateway.auth.constant.HttpStatusErrorEnum;
import cn.javaquan.cloud.gateway.auth.constant.PermEnum;
import cn.javaquan.common.base.message.Result;
import cn.javaquan.security.common.dto.AccessorInfo;
import org.springframework.util.CollectionUtils;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * 管理员权限过滤器.
 * <p>
 * 该权限将校验用户是否拥有管理员角色权限.
 *
 * @author javaquan
 * @since 2.3.1
 */
public class AdminFilter extends PermissionsFilter {

    @Override
    public Result doFilter(List<String> roles) {
        Result<AccessorInfo> result = super.doFilter(roles);
        if (!result.isSuccess()
                || validAdminRole(result.getData(), Collections.singletonList(PermEnum.ADMIN.getType()))) {
            return Result.fail(HttpStatusErrorEnum.FORBIDDEN);
        }
        return result;
    }

    /**
     * 校验角色.
     * @param accessorInfo 认证用户角色信息
     * @param permRoles 权限角色（当角色授权时配置）
     * @return true：校验通过，false：校验失败
     */
    public boolean validAdminRole(AccessorInfo accessorInfo, List<String> permRoles) {
        List<Map<String, Object>> roles = (List<Map<String, Object>>) accessorInfo.getRoles();

        if (CollectionUtils.isEmpty(roles)) {
            return false;
        }
        List<Object> roleCode = roles.stream().map(role -> role.get("code")).collect(Collectors.toList());

        // 必须满足管理员角色
        return roleCode.containsAll(permRoles);
    }

}
