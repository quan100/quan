package cn.javaquan.app.common.module.system;

import cn.javaquan.common.base.message.BasePage;
import lombok.Data;

import java.io.Serializable;

/**
 * 用户角色配置.
 *
 * @author javaquan
 * @since 1.0.0
 */
@Data
public class SysUserRoleQuery extends BasePage implements Serializable {

    private static final long serialVersionUID = 742833405930788595L;

    /**
     * 主键.
     */
    private Long id;

    /**
     * 角色ID.
     */
    private Long roleId;

    /**
     * 用户ID.
     */
    private String userId;

}
