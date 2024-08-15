package cn.javaquan.app.mobile.bff.tools.convert;

import cn.javaquan.app.common.module.tools.OpenToolsQuery;
import cn.javaquan.app.common.module.tools.OpenToolsVO;
import cn.javaquan.app.common.module.tools.ToolsDTO;
import cn.javaquan.app.common.module.tools.ToolsQuery;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

/**
 * 在线工具参数转换器.
 *
 * @author wangquan
 * @since 1.0.0
 */
@Mapper
public interface OpenToolsAssembler {

    /**
     * 返回给定映射器类型的实例.
     */
    OpenToolsAssembler INSTANCE = Mappers.getMapper(OpenToolsAssembler.class);

    /**
     * 转换为业务查询条件.
     * @param query 开放的查询条件
     * @return 业务查询条件
     */
    ToolsQuery toToolsQuery(OpenToolsQuery query);

    /**
     * 转换为展示的数据模型.
     * @param dto 数据模型
     * @return vo
     */
    OpenToolsVO toOpenToolsVO(ToolsDTO dto);

    /**
     * 转换为展示的数据模型.
     * @param dtos 数据模型
     * @return vo
     */
    List<OpenToolsVO> toOpenToolsVOList(List<ToolsDTO> dtos);

}
