package cn.javaquan.app.mobile.bff.comment.convert;

import cn.javaquan.app.common.module.artalk.ArtalkCommentPagesDTO;
import cn.javaquan.app.common.module.artalk.OpenArtalkCommentPagesVO;
import cn.javaquan.app.common.util.LocalDateUtils;
import cn.javaquan.tools.id.ID;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

/**
 * @author wangquan
 */
@Mapper(imports = {
        ID.class,
        LocalDateUtils.class
})
public interface ArtalkCommentPagesAssembler {

    ArtalkCommentPagesAssembler INSTANCE = Mappers.getMapper(ArtalkCommentPagesAssembler.class);

    OpenArtalkCommentPagesVO toOpenArtalkCommentPagesVO(ArtalkCommentPagesDTO dto);

    List<OpenArtalkCommentPagesVO> toOpenArtalkCommentPagesVOList(List<ArtalkCommentPagesDTO> dtoList);
}
