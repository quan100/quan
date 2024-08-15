package cn.javaquan.app.mobile.bff.comment.convert;

import cn.javaquan.app.common.module.artalk.ArtalkCommentPagesDTO;
import cn.javaquan.app.common.module.artalk.OpenArtalkCommentPagesVO;
import cn.javaquan.app.common.util.LocalDateUtils;
import cn.javaquan.tools.id.ID;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

/**
 * artalk 评论系统参数转换器.
 *
 * @author wangquan
 * @since 1.0.0
 */
@Mapper(imports = { ID.class, LocalDateUtils.class })
public interface ArtalkCommentPagesAssembler {

    /**
     * 返回给定映射器类型的实例.
     */
    ArtalkCommentPagesAssembler INSTANCE = Mappers.getMapper(ArtalkCommentPagesAssembler.class);

    /**
     * artalk 评论系统参数转换为开放的参数.
     * @param dto artalk 评论系统参数
     * @return 开放的展示参数
     */
    OpenArtalkCommentPagesVO toOpenArtalkCommentPagesVO(ArtalkCommentPagesDTO dto);

    /**
     * artalk 评论系统参数转换为开放的参数.
     * @param dtoList artalk 评论系统参数
     * @return 开放的展示参数
     */
    List<OpenArtalkCommentPagesVO> toOpenArtalkCommentPagesVOList(List<ArtalkCommentPagesDTO> dtoList);

}
