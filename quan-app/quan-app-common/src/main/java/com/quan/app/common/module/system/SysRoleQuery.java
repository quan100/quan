package com.quan.app.common.module.system;

import com.quan.common.base.message.BasePage;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * 角色配置
 *
 * @author JavaQuan
 * @since 2023-04-04 10:53:59
 */
@Data
public class SysRoleQuery extends BasePage implements Serializable {

    private static final long serialVersionUID = 742833405930788595L;

    /**
     * 主键
     */
    private Long id;

    /**
     * 角色名称
     */
    private String name;

    /**
     * 角色编码
     */
    private String code;

    /**
     * 0：启用，1：禁用
     */
    private Integer status;

    /**
     * 删除状态，false：未删除，true：已删除
     */
    private Boolean delFlag;

    /**
     * 应用编码，标识权限所属的应用
     */
    private String appType;

    /**
     * 根据角色ID列表查询
     */
    private List<Long> roleIds;

}
