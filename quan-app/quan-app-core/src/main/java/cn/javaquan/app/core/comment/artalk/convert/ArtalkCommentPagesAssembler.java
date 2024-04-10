package cn.javaquan.app.core.comment.artalk.convert;

import cn.javaquan.app.common.module.artalk.ArtalkCommentPagesAddCommand;
import cn.javaquan.app.common.module.artalk.ArtalkCommentPagesQuery;
import cn.javaquan.app.common.module.artalk.ArtalkCommentPagesUpdateCommand;
import cn.javaquan.app.core.comment.artalk.entity.ArtalkCommentPagesPO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

/**
 * 参数转换
 *
 * @author JavaQuan
 * @version 1.0.0
 */
@Mapper
public interface ArtalkCommentPagesAssembler {

    ArtalkCommentPagesAssembler INSTANCE = Mappers.getMapper(ArtalkCommentPagesAssembler.class);

    /**
     * 转换为查询参数
     *
     * @param query
     * @return
     */
    ArtalkCommentPagesPO toQueryPO(ArtalkCommentPagesQuery query);

    /**
     * 转换为更新参数
     *
     * @param cmd
     * @return
     */
    ArtalkCommentPagesPO toUpdatePO(ArtalkCommentPagesUpdateCommand cmd);

    /**
     * 转换为新增参数
     *
     * @param cmd
     * @return
     */
    ArtalkCommentPagesPO toAddPO(ArtalkCommentPagesAddCommand cmd);

    /**
     * 转换为新增参数
     *
     * @param cmds
     * @return
     */
    List<ArtalkCommentPagesPO> toAddPOS(List<ArtalkCommentPagesAddCommand> cmds);
}
