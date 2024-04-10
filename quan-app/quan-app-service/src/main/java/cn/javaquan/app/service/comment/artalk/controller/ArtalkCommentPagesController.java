package cn.javaquan.app.service.comment.artalk.controller;

import cn.javaquan.app.common.module.artalk.ArtalkCommentPagesAddCommand;
import cn.javaquan.app.common.module.artalk.ArtalkCommentPagesDTO;
import cn.javaquan.app.common.module.artalk.ArtalkCommentPagesQuery;
import cn.javaquan.app.common.module.artalk.ArtalkCommentPagesUpdateCommand;
import cn.javaquan.app.service.comment.artalk.service.ArtalkCommentPagesService;
import cn.javaquan.common.base.message.PageResult;
import cn.javaquan.common.base.message.Result;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;


/**
 * @author JavaQuan
 * @version 1.0.0
 */
@RequiredArgsConstructor
@RestController
@RequestMapping("/service/artalk/comment/pages/")
public class ArtalkCommentPagesController {

    private final ArtalkCommentPagesService artalkCommentPagesService;

    /**
     * 查询列表
     *
     * @param query
     * @return
     */
    @GetMapping("page")
    public Result<PageResult<ArtalkCommentPagesDTO>> page(ArtalkCommentPagesQuery query) {
        return artalkCommentPagesService.page(query);
    }

    /**
     * 根据ID查询
     *
     * @param id
     * @return
     */
    @GetMapping("details")
    public Result<ArtalkCommentPagesDTO> details(@RequestParam Long id) {
        return artalkCommentPagesService.details(id);
    }

    /**
     * 根据主键更新
     *
     * @param cmd
     * @return
     */
    @PutMapping("update")
    public Result<Boolean> update(@RequestBody ArtalkCommentPagesUpdateCommand cmd) {
        return artalkCommentPagesService.update(cmd);
    }

    /**
     * 新增
     *
     * @param cmd
     * @return
     */
    @PostMapping("save")
    public Result<Boolean> save(@RequestBody ArtalkCommentPagesAddCommand cmd) {
        return artalkCommentPagesService.save(cmd);
    }

    /**
     * 批量新增
     *
     * @param cmds
     * @return
     */
    @PostMapping("saveBatch")
    public Result<Boolean> saveBatch(@RequestBody List<ArtalkCommentPagesAddCommand> cmds) {
        return artalkCommentPagesService.saveBatch(cmds);
    }

    /**
     * 删除
     *
     * @param ids
     * @return
     */
    @DeleteMapping("deleteByIds")
    public Result<Boolean> deleteByIds(@RequestBody List<Long> ids) {
        return artalkCommentPagesService.deleteByIds(ids);
    }

    /**
     * 查询页面统计数据
     *
     * @param keys
     * @return
     */
    @GetMapping("statistics")
    public Result<List<ArtalkCommentPagesDTO>> statistics(@RequestParam List<String> keys) {
        return artalkCommentPagesService.statistics(keys);
    }

}
