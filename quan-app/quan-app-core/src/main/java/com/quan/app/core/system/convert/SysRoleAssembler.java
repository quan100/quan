package com.quan.app.core.system.convert;

import com.quan.app.common.module.system.SysRoleAddCommand;
import com.quan.app.common.module.system.SysRoleQuery;
import com.quan.app.common.module.system.SysRoleUpdateCommand;
import com.quan.app.common.util.LocalDateUtils;
import com.quan.app.core.system.entity.SysRolePO;
import com.quan.tools.id.ID;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.List;

/**
 * 角色配置参数转换
 *
 * @author JavaQuan
 * @version 1.0.0
 * @date 2023-04-14 18:19:03
 */
@Mapper(imports = {ID.class, LocalDateUtils.class})
public interface SysRoleAssembler {

    SysRoleAssembler INSTANCE = Mappers.getMapper(SysRoleAssembler.class);

    /**
     * 转换为查询参数
     *
     * @param query
     * @return
     */
    SysRolePO toQueryPO(SysRoleQuery query);

    /**
     * 转换为更新参数
     * <p>
     * 更新时不处理删除状态，删除状态交由删除功能处理。
     *
     * @param cmd
     * @return
     */
    @Mapping(target = "delFlag", ignore = true)
    SysRolePO toUpdatePO(SysRoleUpdateCommand cmd);

    /**
     * 转换为新增参数
     * <p>
     * 新增时删除状态默认为正常。
     *
     * @param cmd
     * @return
     */
    @Mapping(target = "delFlag", constant = "false")
    SysRolePO toAddPO(SysRoleAddCommand cmd);

    /**
     * 转换为新增参数
     *
     * @param cmds
     * @return
     */
    List<SysRolePO> toAddPOS(List<SysRoleAddCommand> cmds);
}
