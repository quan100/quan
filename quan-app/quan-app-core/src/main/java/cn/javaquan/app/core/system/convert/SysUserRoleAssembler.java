package cn.javaquan.app.core.system.convert;

import cn.javaquan.app.common.module.auth.TripartiteBoundEvent;
import cn.javaquan.app.common.module.system.SysUserRoleAddCommand;
import cn.javaquan.app.common.module.system.SysUserRoleQuery;
import cn.javaquan.app.common.module.system.SysUserRoleUpdateCommand;
import cn.javaquan.app.common.util.LocalDateUtils;
import cn.javaquan.app.core.system.entity.SysUserRolePO;
import cn.javaquan.tools.id.ID;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.List;

/**
 * 用户角色配置参数转换
 *
 * @author JavaQuan
 * @version 1.0.0
 * @date 2023-04-14 18:19:03
 */
@Mapper(imports = {ID.class, LocalDateUtils.class})
public interface SysUserRoleAssembler {

    SysUserRoleAssembler INSTANCE = Mappers.getMapper(SysUserRoleAssembler.class);

    /**
     * 转换为查询参数
     *
     * @param query
     * @return
     */
    SysUserRolePO toQueryPO(SysUserRoleQuery query);

    /**
     * 转换为更新参数
     *
     * @param cmd
     * @return
     */
    SysUserRolePO toUpdatePO(SysUserRoleUpdateCommand cmd);

    /**
     * 转换为新增参数
     *
     * @param cmd
     * @return
     */
    SysUserRolePO toAddPO(SysUserRoleAddCommand cmd);

    /**
     * 转换为新增参数
     *
     * @param cmds
     * @return
     */
    List<SysUserRolePO> toAddPOS(List<SysUserRoleAddCommand> cmds);

    @Mapping(target = "userId", source = "defaultBoundRole")
    SysUserRolePO toAddPO(TripartiteBoundEvent event);

    /**
     * 转换为新增参数
     *
     * @param roleId
     * @param userId
     * @return
     */
    SysUserRolePO toAddPO(Long roleId, String userId);
}
