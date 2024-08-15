package cn.javaquan.app.core.system.convert;

import cn.javaquan.app.common.module.system.SysRoleAddCommand;
import cn.javaquan.app.common.module.system.SysRoleQuery;
import cn.javaquan.app.common.module.system.SysRoleUpdateCommand;
import cn.javaquan.app.common.util.LocalDateUtils;
import cn.javaquan.app.core.system.entity.SysRolePO;
import cn.javaquan.tools.id.ID;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.List;

/**
 * 角色配置参数转换.
 *
 * @author javaquan
 * @since 1.0.0
 */
@Mapper(imports = { ID.class, LocalDateUtils.class })
public interface SysRoleAssembler {

    /**
     * 返回给定映射器类型的实例.
     */
    SysRoleAssembler INSTANCE = Mappers.getMapper(SysRoleAssembler.class);

    /**
     * 转换为查询参数.
     * @param query 查询参数
     * @return 查询参数
     */
    SysRolePO toQueryPO(SysRoleQuery query);

    /**
     * 转换为更新参数.
     * <p>
     * 更新时不处理删除状态，删除状态交由删除功能处理。
     * @param cmd 更新指令参数
     * @return 更新参数
     */
    @Mapping(target = "delFlag", ignore = true)
    SysRolePO toUpdatePO(SysRoleUpdateCommand cmd);

    /**
     * 转换为新增参数.
     * <p>
     * 新增时删除状态默认为正常。
     * @param cmd 新增指令参数
     * @return 新增参数
     */
    @Mapping(target = "delFlag", constant = "false")
    SysRolePO toAddPO(SysRoleAddCommand cmd);

    /**
     * 转换为新增参数.
     * @param cmds 新增参数
     * @return 新增参数
     */
    List<SysRolePO> toAddPOS(List<SysRoleAddCommand> cmds);

}
