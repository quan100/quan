package cn.javaquan.app.common.constant;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 角色类型定义类.
 *
 * @author quan.wang
 * @since 1.0.0
 */
@Getter
@AllArgsConstructor
public enum RoleTypeEnum {

    /**
     * 开放角色.
     * <p>
     * 该角色拥有的权限对匿名用户开放 该角色是系统唯一定制化的角色，若期望匿名用户能够获取访问权限，请配置该角色
     */
    OPEN("open");

    /**
     * 角色编码.
     */
    final String code;

}
