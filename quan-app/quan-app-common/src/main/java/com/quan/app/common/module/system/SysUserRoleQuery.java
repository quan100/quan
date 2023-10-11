package com.quan.app.common.module.system;

import com.quan.common.base.message.BasePage;
import lombok.Data;

import java.io.Serializable;

/**
 * 用户角色配置
 *
 * @author JavaQuan
 * @since 2023-04-04 10:53:59
 */
@Data
public class SysUserRoleQuery extends BasePage implements Serializable {

    private static final long serialVersionUID = 742833405930788595L;

    /**
     * 主键
     */
    private Long id;

    /**
     * 角色ID
     */
    private Long roleId;

    /**
     * 用户ID
     */
    private String userId;

}
