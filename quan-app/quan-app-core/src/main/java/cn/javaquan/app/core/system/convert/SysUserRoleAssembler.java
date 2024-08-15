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
 * 用户角色配置参数转换.
 *
 * @author javaquan
 * @since 1.0.0
 */
@Mapper(imports = { ID.class, LocalDateUtils.class })
public interface SysUserRoleAssembler {

    /**
     * 返回给定映射器类型的实例.
     */
    SysUserRoleAssembler INSTANCE = Mappers.getMapper(SysUserRoleAssembler.class);

    /**
     * 转换为查询参数.
     * @param query 查询参数
     * @return 查询参数
     */
    SysUserRolePO toQueryPO(SysUserRoleQuery query);

    /**
     * 转换为更新参数.
     * @param cmd 更新指令参数
     * @return 更新参数
     */
    SysUserRolePO toUpdatePO(SysUserRoleUpdateCommand cmd);

    /**
     * 转换为新增参数.
     * @param cmd 新增指令参数
     * @return 新增参数
     */
    SysUserRolePO toAddPO(SysUserRoleAddCommand cmd);

    /**
     * 转换为新增参数.
     * @param cmds 新增参数
     * @return 新增参数
     */
    List<SysUserRolePO> toAddPOS(List<SysUserRoleAddCommand> cmds);

    /**
     * 转换为新增参数.
     * @param event 绑定参数
     * @return 新增参数
     */
    @Mapping(target = "userId", source = "defaultBoundRole")
    SysUserRolePO toAddPO(TripartiteBoundEvent event);

    /**
     * 转换为新增参数.
     * @param roleId 角色ID
     * @param userId 用户ID
     * @return 新增参数
     */
    SysUserRolePO toAddPO(Long roleId, String userId);

}
