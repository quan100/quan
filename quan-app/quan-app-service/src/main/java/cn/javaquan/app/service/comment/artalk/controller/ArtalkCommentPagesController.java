package cn.javaquan.app.service.comment.artalk.controller;

import cn.javaquan.app.common.module.artalk.ArtalkCommentPagesAddCommand;
import cn.javaquan.app.common.module.artalk.ArtalkCommentPagesDTO;
import cn.javaquan.app.common.module.artalk.ArtalkCommentPagesQuery;
import cn.javaquan.app.common.module.artalk.ArtalkCommentPagesUpdateCommand;
import cn.javaquan.app.service.comment.artalk.service.ArtalkCommentPagesService;
import cn.javaquan.common.base.message.PageResult;
import cn.javaquan.common.base.message.Result;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * artalk 评论数据业务接口.
 *
 * @author javaquan
 * @since 1.0.0
 */
@RequiredArgsConstructor
@RestController
@RequestMapping("/service/artalk/comment/pages/")
public class ArtalkCommentPagesController {

    private final ArtalkCommentPagesService artalkCommentPagesService;

    /**
     * 查询列表.
     * @param query 查询参数
     * @return 查询结果
     */
    @GetMapping("page")
    public Result<PageResult<ArtalkCommentPagesDTO>> page(ArtalkCommentPagesQuery query) {
        return artalkCommentPagesService.page(query);
    }

    /**
     * 根据ID查询.
     * @param id 主键
     * @return 查询结果
     */
    @GetMapping("details")
    public Result<ArtalkCommentPagesDTO> details(@RequestParam Long id) {
        return artalkCommentPagesService.details(id);
    }

    /**
     * 根据主键更新.
     * @param cmd 更新指令参数
     * @return 操作是否成功
     */
    @PutMapping("update")
    public Result<Boolean> update(@RequestBody ArtalkCommentPagesUpdateCommand cmd) {
        return artalkCommentPagesService.update(cmd);
    }

    /**
     * 新增.
     * @param cmd 新增指令参数
     * @return 操作是否成功
     */
    @PostMapping("save")
    public Result<Boolean> save(@RequestBody ArtalkCommentPagesAddCommand cmd) {
        return artalkCommentPagesService.save(cmd);
    }

    /**
     * 批量新增.
     * @param cmds 新增参数
     * @return 新增结果
     */
    @PostMapping("saveBatch")
    public Result<Boolean> saveBatch(@RequestBody List<ArtalkCommentPagesAddCommand> cmds) {
        return artalkCommentPagesService.saveBatch(cmds);
    }

    /**
     * 删除.
     * @param ids 主键
     * @return 操作是否成功
     */
    @DeleteMapping("deleteByIds")
    public Result<Boolean> deleteByIds(@RequestBody List<Long> ids) {
        return artalkCommentPagesService.deleteByIds(ids);
    }

    /**
     * 查询页面统计数据.
     * @param keys 页面唯一键
     * @return 统计数据
     */
    @GetMapping("statistics")
    public Result<List<ArtalkCommentPagesDTO>> statistics(@RequestParam List<String> keys) {
        return artalkCommentPagesService.statistics(keys);
    }

}
