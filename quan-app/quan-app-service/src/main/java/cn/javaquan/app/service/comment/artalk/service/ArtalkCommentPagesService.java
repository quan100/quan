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
 * @author JavaQuan
 * @version 1.0.0
 */
@RequiredArgsConstructor
@Component
public class ArtalkCommentPagesService {

    private final ArtalkCommentPagesRepositoryFeign artalkCommentPagesRepositoryFeign;

    /**
     * 查询列表
     *
     * @param query
     * @return
     */
    public Result<PageResult<ArtalkCommentPagesDTO>> page(ArtalkCommentPagesQuery query) {
        return artalkCommentPagesRepositoryFeign.page(query);
    }

    /**
     * 根据ID查询
     *
     * @param id
     * @return
     */
    public Result<ArtalkCommentPagesDTO> details(Long id) {
        return artalkCommentPagesRepositoryFeign.details(id);
    }

    /**
     * 根据主键更新
     *
     * @param cmd
     * @return
     */
    public Result<Boolean> update(ArtalkCommentPagesUpdateCommand cmd) {
        return artalkCommentPagesRepositoryFeign.update(cmd);
    }

    /**
     * 新增
     *
     * @param cmd
     * @return
     */
    public Result<Boolean> save(ArtalkCommentPagesAddCommand cmd) {
        return artalkCommentPagesRepositoryFeign.save(cmd);
    }

    /**
     * 批量新增
     *
     * @param cmds
     * @return
     */
    public Result<Boolean> saveBatch(List<ArtalkCommentPagesAddCommand> cmds) {
        return artalkCommentPagesRepositoryFeign.saveBatch(cmds);
    }

    /**
     * 删除
     *
     * @param ids
     * @return
     */
    public Result<Boolean> deleteByIds(List<Long> ids) {
        return artalkCommentPagesRepositoryFeign.deleteByIds(ids);
    }

    /**
     * 查询页面统计数据
     *
     * @param keys
     * @return
     */
    public Result<List<ArtalkCommentPagesDTO>> statistics(List<String> keys) {
        return artalkCommentPagesRepositoryFeign.statistics(keys);
    }
}