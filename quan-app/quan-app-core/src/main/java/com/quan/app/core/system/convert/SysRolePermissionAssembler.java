package com.quan.app.core.system.convert;

import com.quan.app.common.module.system.SysRolePermissionAddCommand;
import com.quan.app.common.module.system.SysRolePermissionQuery;
import com.quan.app.common.module.system.SysRolePermissionUpdateCommand;
import com.quan.app.common.util.LocalDateUtils;
import com.quan.app.core.system.entity.SysRolePermissionPO;
import com.quan.tools.id.ID;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

/**
 * 角色权限配置参数转换
 *
 * @author JavaQuan
 * @version 1.0.0
 * @date 2023-04-14 18:19:03
 */
@Mapper(imports = {ID.class, LocalDateUtils.class})
public interface SysRolePermissionAssembler {

    SysRolePermissionAssembler INSTANCE = Mappers.getMapper(SysRolePermissionAssembler.class);

    /**
     * 转换为查询参数
     *
     * @param query
     * @return
     */
    SysRolePermissionPO toQueryPO(SysRolePermissionQuery query);

    /**
     * 转换为更新参数
     * <p>
     * 更新自动配置更新时间。
     * 更新时不处理删除状态，删除状态交由删除功能处理。
     *
     * @param cmd
     * @return
     */
    SysRolePermissionPO toUpdatePO(SysRolePermissionUpdateCommand cmd);

    /**
     * 转换为新增参数
     * <p>
     * 新增时删除状态默认为正常。
     *
     * @param cmd
     * @return
     */
    SysRolePermissionPO toAddPO(SysRolePermissionAddCommand cmd);

    /**
     * 转换为新增参数
     * 新增时删除状态默认为正常。
     *
     * @param cmds
     * @return
     */
    List<SysRolePermissionPO> toAddPOS(List<SysRolePermissionAddCommand> cmds);
}
