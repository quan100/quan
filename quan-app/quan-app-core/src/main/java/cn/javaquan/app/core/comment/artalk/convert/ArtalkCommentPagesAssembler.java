package cn.javaquan.app.core.comment.artalk.convert;

import cn.javaquan.app.common.module.artalk.ArtalkCommentPagesAddCommand;
import cn.javaquan.app.common.module.artalk.ArtalkCommentPagesQuery;
import cn.javaquan.app.common.module.artalk.ArtalkCommentPagesUpdateCommand;
import cn.javaquan.app.core.comment.artalk.entity.ArtalkCommentPagesPO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

/**
 * 参数转换.
 *
 * @author javaquan
 * @since 1.0.0
 */
@Mapper
public interface ArtalkCommentPagesAssembler {

    /**
     * 返回给定映射器类型的实例.
     */
    ArtalkCommentPagesAssembler INSTANCE = Mappers.getMapper(ArtalkCommentPagesAssembler.class);

    /**
     * 转换为查询参数.
     * @param query 查询参数
     * @return 查询参数
     */
    ArtalkCommentPagesPO toQueryPO(ArtalkCommentPagesQuery query);

    /**
     * 转换为更新参数.
     * @param cmd 更新指令参数
     * @return 更新参数
     */
    ArtalkCommentPagesPO toUpdatePO(ArtalkCommentPagesUpdateCommand cmd);

    /**
     * 转换为新增参数.
     * @param cmd 新增指令参数
     * @return 新增参数
     */
    ArtalkCommentPagesPO toAddPO(ArtalkCommentPagesAddCommand cmd);

    /**
     * 转换为新增参数.
     * @param cmds 新增参数
     * @return 新增参数
     */
    List<ArtalkCommentPagesPO> toAddPOS(List<ArtalkCommentPagesAddCommand> cmds);

}
