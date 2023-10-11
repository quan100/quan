package com.quan.app.common.constant;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author quan.wang
 * @date 2020/1/13 14:41
 */
@Getter
@AllArgsConstructor
public enum RoleTypeEnum {

    /**
     * 开放角色
     * 该角色拥有的权限对匿名用户开放
     * 该角色是系统唯一定制化的角色，若期望匿名用户能够获取访问权限，请配置该角色
     */
    OPEN("open");

    String code;

}
