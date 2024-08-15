package cn.javaquan.app.common.module.system.convert;

import cn.javaquan.app.common.module.system.SysRolePermissionEvent;
import cn.javaquan.app.common.module.system.SysRolePermissionQuery;
import cn.javaquan.app.common.module.system.SysRoleQuery;
import cn.javaquan.app.common.module.system.SysUserAccountQuery;
import cn.javaquan.app.common.module.system.SysUserInfoDTO;
import cn.javaquan.app.common.module.system.SysUserTripartiteAccountQuery;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

/**
 * {@code system} 模块中的简单参数转换.
 *
 * @author javaquan
 * @since 1.0.0
 */
@Mapper
public interface SystemAssembler {

    /**
     * 返回给定映射器类型的实例.
     */
    SystemAssembler INSTANCE = Mappers.getMapper(SystemAssembler.class);

    /**
     * 转换为角色权限操作的事件参数.
     * @param roleId 角色ID
     * @return 操作参数
     */
    SysRolePermissionEvent toSysRolePermissionEvent(Long roleId);

    /**
     * 转换为角色权限查询参数.
     * @param roleId 角色id
     * @param roleIds 角色id列表
     * @return 查询参数
     */
    SysRolePermissionQuery toSysRolePermissionQuery(Long roleId, List<Long> roleIds);

    /**
     * 转换为查询参数.
     * @param roleIds 角色ID
     * @return 查询参数
     */
    default SysRoleQuery toSysRoleQuery(List<Long> roleIds) {
        SysRoleQuery query = new SysRoleQuery();
        query.setRoleIds(roleIds);
        return query;
    }

    /**
     * 转换为角色查询参数.
     * @param code 角色编码
     * @param appType 应用类型
     * @param status 角色状态
     * @return 查询参数
     */
    SysRoleQuery toSysRoleQuery(String code, String appType, Integer status);

    /**
     * 转换为用户账号查询参数.
     * @param account 账号
     * @param userId 用户id
     * @return 查询参数
     */
    SysUserAccountQuery toSysUserAccountQuery(String account, String userId);

    /**
     * 转换为用户信息.
     * @param userId 用户id
     * @param nickName 用户昵称
     * @return 用户信息
     */
    SysUserInfoDTO toSysUserInfoDTO(String userId, String nickName);

    /**
     * 转换为用户第三方账号查询参数.
     * @param thirdType 第三方账号类型
     * @param thirdId 第三方账号id
     * @return 查询参数
     */
    SysUserTripartiteAccountQuery toSysUserTripartiteAccountQuery(String thirdType, String thirdId);

}
