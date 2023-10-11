package com.quan.app.mobile.bff.tools.convert;

import com.quan.app.common.module.tools.OpenToolsQuery;
import com.quan.app.common.module.tools.OpenToolsVO;
import com.quan.app.common.module.tools.ToolsDTO;
import com.quan.app.common.module.tools.ToolsQuery;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

/**
 * @author wangquan
 */
@Mapper
public interface OpenToolsAssembler {

    OpenToolsAssembler INSTANCE = Mappers.getMapper(OpenToolsAssembler.class);

    ToolsQuery toToolsQuery(OpenToolsQuery query);

    OpenToolsVO toOpenToolsVO(ToolsDTO dto);

    List<OpenToolsVO> toOpenToolsVOList(List<ToolsDTO> dtos);
}
