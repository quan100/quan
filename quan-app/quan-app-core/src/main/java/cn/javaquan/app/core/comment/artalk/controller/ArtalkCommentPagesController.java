package cn.javaquan.app.core.comment.artalk.controller;

import cn.javaquan.app.common.module.artalk.ArtalkCommentPagesAddCommand;
import cn.javaquan.app.common.module.artalk.ArtalkCommentPagesQuery;
import cn.javaquan.app.common.module.artalk.ArtalkCommentPagesUpdateCommand;
import cn.javaquan.app.core.comment.artalk.convert.ArtalkCommentPagesAssembler;
import cn.javaquan.app.core.comment.artalk.entity.ArtalkCommentPagesPO;
import cn.javaquan.app.core.comment.artalk.repository.ArtalkCommentPagesRepository;
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
@RequestMapping("/core/artalk/comment/pages/")
public class ArtalkCommentPagesController {

    private final ArtalkCommentPagesRepository artalkCommentPagesRepository;

    /**
     * 查询列表
     *
     * @param query
     * @return
     */
    @GetMapping("page")
    public Result<PageResult> page(ArtalkCommentPagesQuery query) {
        ArtalkCommentPagesPO po = ArtalkCommentPagesAssembler.INSTANCE.toQueryPO(query);
        return Result.success(artalkCommentPagesRepository.page(po, query));
    }

    /**
     * 根据ID查询
     *
     * @param id
     * @return
     */
    @GetMapping("details")
    public Result details(@RequestParam Long id) {
        return Result.success(artalkCommentPagesRepository.getById(id));
    }

    /**
     * 根据主键更新
     *
     * @param cmd
     * @return
     */
    @PutMapping("update")
    public Result update(@RequestBody ArtalkCommentPagesUpdateCommand cmd) {
        ArtalkCommentPagesPO po = ArtalkCommentPagesAssembler.INSTANCE.toUpdatePO(cmd);
        return Result.success(artalkCommentPagesRepository.updateById(po));
    }

    /**
     * 新增
     *
     * @param cmd
     * @return
     */
    @PostMapping("save")
    public Result save(@RequestBody ArtalkCommentPagesAddCommand cmd) {
        ArtalkCommentPagesPO po = ArtalkCommentPagesAssembler.INSTANCE.toAddPO(cmd);
        return Result.success(artalkCommentPagesRepository.save(po));
    }

    /**
     * 批量新增
     *
     * @param cmds
     * @return
     */
    @PostMapping("saveBatch")
    public Result saveBatch(@RequestBody List<ArtalkCommentPagesAddCommand> cmds) {
        List<ArtalkCommentPagesPO> pos = ArtalkCommentPagesAssembler.INSTANCE.toAddPOS(cmds);
        return Result.success(artalkCommentPagesRepository.saveBatch(pos));
    }

    /**
     * 删除
     *
     * @param ids
     * @return
     */
    @DeleteMapping("deleteByIds")
    public Result deleteByIds(@RequestBody List<Long> ids) {
        return Result.success(artalkCommentPagesRepository.removeByIds(ids));
    }

    /**
     * 查询页面统计数据
     *
     * @param keys
     * @return
     */
    @GetMapping("statistics")
    public Result statistics(@RequestParam List<String> keys) {
        return Result.success(artalkCommentPagesRepository.statistics(keys));
    }

}
