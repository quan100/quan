package cn.javaquan.app.common.module.system;

import lombok.Data;

import java.io.Serializable;

/**
 * 角色配置.
 *
 * @author javaquan
 * @since 1.0.0
 */
@Data
public class SysRoleDTO implements Serializable {

    private static final long serialVersionUID = -137770378920503836L;

    /**
     * 主键.
     */
    private Long id;

    /**
     * 角色名称.
     */
    private String name;

    /**
     * 角色编码.
     */
    private String code;

    /**
     * 0：启用，1：禁用.
     */
    private Integer status;

    /**
     * 删除状态，false：未删除，true：已删除.
     */
    private Boolean delFlag;

    /**
     * 应用编码，标识权限所属的应用.
     */
    private String appType;

}
