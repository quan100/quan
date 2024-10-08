package cn.javaquan.app.common.module.system;

import lombok.Data;

import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.List;

/**
 * 角色权限授权事件参数.
 *
 * @author javaquan
 * @since 1.0.0
 */
@Data
public class AuthorizeRolePermissionEvent implements Serializable {

    private static final long serialVersionUID = 1370684371328942015L;

    /**
     * 角色ID.
     */
    @NotNull(message = "角色ID不可为空")
    private Long roleId;

    /**
     * 权限ID列表.
     */
    private List<Long> permissionIdList;

}
