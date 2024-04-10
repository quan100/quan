package cn.javaquan.app.mobile.bff.comment.feign;

import cn.javaquan.app.common.module.artalk.ArtalkCommentPagesAddCommand;
import cn.javaquan.app.common.module.artalk.ArtalkCommentPagesDTO;
import cn.javaquan.app.common.module.artalk.ArtalkCommentPagesQuery;
import cn.javaquan.app.common.module.artalk.ArtalkCommentPagesUpdateCommand;
import cn.javaquan.app.mobile.bff.comment.feign.fallback.ArtalkCommentPagesServiceFallback;
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
@FeignClient(value = "${quan.app.feign.service.name}", url = "${quan.app.feign.service.url:}", fallbackFactory = ArtalkCommentPagesServiceFallback.class)
public interface ArtalkCommentPagesServiceFeign {

    /**
     * 查询列表
     *
     * @param query
     * @return
     */
    @GetMapping("/service/artalk/comment/pages/page")
    Result<PageResult<ArtalkCommentPagesDTO>> page(@SpringQueryMap ArtalkCommentPagesQuery query);

    /**
     * 根据ID查询
     *
     * @param id
     * @return
     */
    @GetMapping("/service/artalk/comment/pages/details")
    Result<ArtalkCommentPagesDTO> details(@RequestParam Long id);

    /**
     * 根据主键更新
     *
     * @param cmd
     * @return
     */
    @PutMapping("/service/artalk/comment/pages/update")
    Result<Boolean> update(@RequestBody ArtalkCommentPagesUpdateCommand cmd);

    /**
     * 新增
     *
     * @param cmd
     * @return
     */
    @PostMapping("/service/artalk/comment/pages/save")
    Result<Boolean> save(@RequestBody ArtalkCommentPagesAddCommand cmd);

    /**
     * 批量新增
     *
     * @param cmds
     * @return
     */
    @PostMapping("/service/artalk/comment/pages/saveBatch")
    Result<Boolean> saveBatch(@RequestBody List<ArtalkCommentPagesAddCommand> cmds);

    /**
     * 删除
     *
     * @param ids
     * @return
     */
    @DeleteMapping("/service/artalk/comment/pages/deleteByIds")
    Result<Boolean> deleteByIds(@RequestBody List<Long> ids);

    /**
     * 查询页面统计数据
     *
     * @param keys
     * @return
     */
    @GetMapping("/service/artalk/comment/pages/statistics")
    Result<List<ArtalkCommentPagesDTO>> statistics(@RequestParam List<String> keys);
}
