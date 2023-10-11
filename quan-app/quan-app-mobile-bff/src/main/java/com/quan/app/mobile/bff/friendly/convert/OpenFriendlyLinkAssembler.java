package com.quan.app.mobile.bff.friendly.convert;

import com.quan.app.common.module.friendly.FriendlyLinkDTO;
import com.quan.app.common.module.friendly.FriendlyLinkVO;
import com.quan.app.common.util.LocalDateUtils;
import com.quan.tools.id.ID;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.mapstruct.factory.Mappers;

import java.util.List;

/**
 * @author wangquan
 */
@Mapper(imports = {
        ID.class,
        LocalDateUtils.class
})
public interface OpenFriendlyLinkAssembler {

    OpenFriendlyLinkAssembler INSTANCE = Mappers.getMapper(OpenFriendlyLinkAssembler.class);

    @Mapping(target = "email", expression = "java(toEmail(dto))")
    FriendlyLinkVO toFriendlyLinkVo(FriendlyLinkDTO dto);

    List<FriendlyLinkVO> toFriendlyLinkVoList(List<FriendlyLinkDTO> dtos);

    @Named("toEmail")
    default String toEmail(FriendlyLinkDTO dto) {
        /**
         * 是否公开email
         */
        if (dto.getEmailPublic()) {
            return dto.getEmail();
        }
        return null;
    }
}
