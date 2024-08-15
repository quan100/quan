package cn.javaquan.app.service.comment.artalk.service;

import cn.javaquan.app.common.module.artalk.ArtalkCommentPagesAddCommand;
import cn.javaquan.app.common.module.artalk.ArtalkCommentPagesDTO;
import cn.javaquan.app.common.module.artalk.ArtalkCommentPagesQuery;
import cn.javaquan.app.common.module.artalk.ArtalkCommentPagesUpdateCommand;
import cn.javaquan.app.service.comment.artalk.feign.ArtalkCommentPagesRepositoryFeign;
import cn.javaquan.common.base.message.PageResult;
import cn.javaquan.common.base.message.Result;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * artalk 评论数据管理业务实现.
 *
 * @author javaquan
 * @since 1.0.0
 */
@RequiredArgsConstructor
@Component
public class ArtalkCommentPagesService {

    private final ArtalkCommentPagesRepositoryFeign artalkCommentPagesRepositoryFeign;

    /**
     * 查询列表.
     * @param query 查询参数
     * @return 查询结果
     */
    public Result<PageResult<ArtalkCommentPagesDTO>> page(ArtalkCommentPagesQuery query) {
        return artalkCommentPagesRepositoryFeign.page(query);
    }

    /**
     * 根据ID查询.
     * @param id 主键
     * @return 查询结果
     */
    public Result<ArtalkCommentPagesDTO> details(Long id) {
        return artalkCommentPagesRepositoryFeign.details(id);
    }

    /**
     * 根据主键更新.
     * @param cmd 更新指令参数
     * @return 操作是否成功
     */
    public Result<Boolean> update(ArtalkCommentPagesUpdateCommand cmd) {
        return artalkCommentPagesRepositoryFeign.update(cmd);
    }

    /**
     * 新增.
     * @param cmd 新增指令参数
     * @return 操作是否成功
     */
    public Result<Boolean> save(ArtalkCommentPagesAddCommand cmd) {
        return artalkCommentPagesRepositoryFeign.save(cmd);
    }

    /**
     * 批量新增.
     * @param cmds 新增参数
     * @return 新增结果
     */
    public Result<Boolean> saveBatch(List<ArtalkCommentPagesAddCommand> cmds) {
        return artalkCommentPagesRepositoryFeign.saveBatch(cmds);
    }

    /**
     * 删除.
     * @param ids 主键
     * @return 操作是否成功
     */
    public Result<Boolean> deleteByIds(List<Long> ids) {
        return artalkCommentPagesRepositoryFeign.deleteByIds(ids);
    }

    /**
     * 查询页面统计数据.
     * @param keys 页面唯一键
     * @return 统计数据
     */
    public Result<List<ArtalkCommentPagesDTO>> statistics(List<String> keys) {
        return artalkCommentPagesRepositoryFeign.statistics(keys);
    }

}
