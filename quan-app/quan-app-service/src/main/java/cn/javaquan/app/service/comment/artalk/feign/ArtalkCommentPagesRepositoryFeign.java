package cn.javaquan.app.service.comment.artalk.feign;

import cn.javaquan.app.common.module.artalk.ArtalkCommentPagesAddCommand;
import cn.javaquan.app.common.module.artalk.ArtalkCommentPagesDTO;
import cn.javaquan.app.common.module.artalk.ArtalkCommentPagesQuery;
import cn.javaquan.app.common.module.artalk.ArtalkCommentPagesUpdateCommand;
import cn.javaquan.app.service.comment.artalk.feign.fallback.ArtalkCommentPagesRepositoryFallback;
import cn.javaquan.common.base.message.PageResult;
import cn.javaquan.common.base.message.Result;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.cloud.openfeign.SpringQueryMap;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author JavaQuan
 * @version 1.0.0
 */
@FeignClient(value = "${quan.app.feign.core.name}", url = "${quan.app.feign.core.url:}", fallbackFactory = ArtalkCommentPagesRepositoryFallback.class)
public interface ArtalkCommentPagesRepositoryFeign {

    /**
     * 查询列表
     *
     * @param query
     * @return
     */
    @GetMapping("/core/artalk/comment/pages/page")
    Result<PageResult<ArtalkCommentPagesDTO>> page(@SpringQueryMap ArtalkCommentPagesQuery query);

    /**
     * 根据ID查询
     *
     * @param id
     * @return
     */
    @GetMapping("/core/artalk/comment/pages/details")
    Result<ArtalkCommentPagesDTO> details(@RequestParam Long id);

    /**
     * 根据主键更新
     *
     * @param cmd
     * @return
     */
    @PutMapping("/core/artalk/comment/pages/update")
    Result<Boolean> update(@RequestBody ArtalkCommentPagesUpdateCommand cmd);

    /**
     * 新增
     *
     * @param cmd
     * @return
     */
    @PostMapping("/core/artalk/comment/pages/save")
    Result<Boolean> save(@RequestBody ArtalkCommentPagesAddCommand cmd);

    /**
     * 批量新增
     *
     * @param cmds
     * @return
     */
    @PostMapping("/core/artalk/comment/pages/saveBatch")
    Result saveBatch(@RequestBody List<ArtalkCommentPagesAddCommand> cmds);

    /**
     * 删除
     *
     * @param ids
     * @return
     */
    @DeleteMapping("/core/artalk/comment/pages/deleteByIds")
    Result<Boolean> deleteByIds(@RequestBody List<Long> ids);

    /**
     * 查询页面统计数据
     *
     * @param keys
     * @return
     */
    @GetMapping("/core/artalk/comment/pages/statistics")
    Result<List<ArtalkCommentPagesDTO>> statistics(@RequestParam List<String> keys);
}
