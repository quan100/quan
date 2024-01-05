package cn.javaquan.app.common.module.system.convert;

import cn.javaquan.app.common.module.system.*;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

/**
 * {@code system} 模块中的简单参数转换
 *
 * @author javaquan
 */
@Mapper
public interface SystemAssembler {

    SystemAssembler INSTANCE = Mappers.getMapper(SystemAssembler.class);

    SysRolePermissionEvent toSysRolePermissionEvent(Long roleId);

    SysRolePermissionQuery toSysRolePermissionQuery(Long roleId, List<Long> roleIds);

    default SysRoleQuery toSysRoleQuery(List<Long> roleIds) {
        SysRoleQuery query = new SysRoleQuery();
        query.setRoleIds(roleIds);
        return query;
    }

    SysRoleQuery toSysRoleQuery(String code, String appType, Integer status);

    SysUserAccountQuery toSysUserAccountQuery(String account, String userId);

    SysUserInfoDTO toSysUserInfoDTO(String userId, String nickName);

    SysUserTripartiteAccountQuery toSysUserTripartiteAccountQuery(String thirdType, String thirdId);

}
