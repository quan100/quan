package cn.javaquan.app.core.system.convert;

import cn.javaquan.app.common.module.system.SysPermissionAddCommand;
import cn.javaquan.app.common.module.system.SysPermissionQuery;
import cn.javaquan.app.common.module.system.SysPermissionUpdateCommand;
import cn.javaquan.app.common.module.system.SysPermissionTreeDTO;
import cn.javaquan.app.common.util.LocalDateUtils;
import cn.javaquan.app.core.system.entity.SysPermissionPO;
import cn.javaquan.tools.id.ID;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.List;

/**
 * 系统资源权限配置参数转换.
 *
 * @author javaquan
 * @since 1.0.0
 */
@Mapper(imports = { ID.class, LocalDateUtils.class })
public interface SysPermissionAssembler {

    /**
     * 返回给定映射器类型的实例.
     */
    SysPermissionAssembler INSTANCE = Mappers.getMapper(SysPermissionAssembler.class);

    /**
     * 转换为查询参数.
     * @param query 查询参数
     * @return 查询参数
     */
    SysPermissionPO toQueryPO(SysPermissionQuery query);

    /**
     * 转换为更新参数.
     * <p>
     * 更新自动配置更新时间。 更新时不处理删除状态，删除状态交由删除功能处理。
     * @param cmd 更新指令参数
     * @return 更新参数
     */
    @Mapping(target = "updateTime", expression = "java(LocalDateUtils.now())")
    @Mapping(target = "delFlag", ignore = true)
    SysPermissionPO toUpdatePO(SysPermissionUpdateCommand cmd);

    /**
     * 转换为新增参数.
     * <p>
     * 新增时删除状态默认为正常。
     * @param cmd 新增指令参数
     * @return 新增参数
     */
    @Mapping(target = "updateTime", expression = "java(LocalDateUtils.now())")
    @Mapping(target = "createTime", expression = "java(LocalDateUtils.now())")
    @Mapping(target = "delFlag", constant = "false")
    SysPermissionPO toAddPO(SysPermissionAddCommand cmd);

    /**
     * 转换为新增参数.
     * @param cmds 新增参数
     * @return 新增参数
     */
    List<SysPermissionPO> toAddPOS(List<SysPermissionAddCommand> cmds);

    /**
     * PO 转换为 DTO.
     * <p>
     * 忽略无权限展示的字段
     * @param sysPermissionPo po
     * @return dto
     */
    @Mapping(target = "redirect", ignore = true)
    @Mapping(target = "hideChildrenInMenu", ignore = true)
    @Mapping(target = "path", ignore = true)
    @Mapping(target = "component", ignore = true)
    @Mapping(target = "description", ignore = true)
    @Mapping(target = "permission", ignore = true)
    @Mapping(target = "target", ignore = true)
    @Mapping(target = "children", ignore = true)
    @Mapping(target = "hideInMenu", ignore = true)
    @Mapping(target = "hash", ignore = true)
    @Mapping(target = "flatMenu", ignore = true)
    @Mapping(target = "exact", ignore = true)
    SysPermissionTreeDTO toSysPermissionTreeDto(SysPermissionPO sysPermissionPo);

    /**
     * PO 转换为 DTO.
     * @param sysPermissionPoList po
     * @return dto
     */
    List<SysPermissionTreeDTO> toSysPermissionTreeDtoList(List<SysPermissionPO> sysPermissionPoList);

}
