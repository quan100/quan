package cn.javaquan.app.mobile.bff.friendly.convert;

import cn.javaquan.app.common.module.friendly.FriendlyLinkAddCommand;
import cn.javaquan.app.common.module.friendly.FriendlyLinkApplyCommand;
import cn.javaquan.app.common.module.friendly.FriendlyLinkDTO;
import cn.javaquan.app.common.module.friendly.FriendlyLinkVO;
import cn.javaquan.app.common.util.LocalDateUtils;
import cn.javaquan.tools.id.ID;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.mapstruct.factory.Mappers;

import java.util.List;

/**
 * 友链相关参数转换器.
 *
 * @author wangquan
 * @since 1.0.0
 */
@Mapper(imports = { ID.class, LocalDateUtils.class })
public interface OpenFriendlyLinkAssembler {

    /**
     * 返回给定映射器类型的实例.
     */
    OpenFriendlyLinkAssembler INSTANCE = Mappers.getMapper(OpenFriendlyLinkAssembler.class);

    /**
     * 转换为VO.
     * @param dto dto
     * @return vo
     */
    @Mapping(target = "email", expression = "java(toEmail(dto))")
    FriendlyLinkVO toFriendlyLinkVo(FriendlyLinkDTO dto);

    /**
     * 转换为VO.
     * @param dtos dtos
     * @return vo
     */
    List<FriendlyLinkVO> toFriendlyLinkVoList(List<FriendlyLinkDTO> dtos);

    /**
     * 邮箱地址转换.
     * @param dto 友链数据
     * @return 邮箱地址
     */
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

    /**
     * 友链申请参数转换为新增指令参数.
     * @param cmd 友链申请参数
     * @return 新增指令参数
     */
    @Mapping(target = "status", constant = "1")
    @Mapping(target = "emailPublic", constant = "false")
    FriendlyLinkAddCommand toFriendlyLinkAddCommand(FriendlyLinkApplyCommand cmd);

}
