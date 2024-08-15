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
 * Artalk 评论系统的数据库模型操作接口.
 *
 * @author javaquan
 * @since 1.0.0
 */
@RequiredArgsConstructor
@RestController
@RequestMapping("/core/artalk/comment/pages/")
public class ArtalkCommentPagesController {

    private final ArtalkCommentPagesRepository artalkCommentPagesRepository;

    /**
     * 分页查询列表.
     * @param query 查询参数
     * @return 分页查询结果
     */
    @GetMapping("page")
    public Result<PageResult<ArtalkCommentPagesPO>> page(ArtalkCommentPagesQuery query) {
        ArtalkCommentPagesPO po = ArtalkCommentPagesAssembler.INSTANCE.toQueryPO(query);
        return Result.success(artalkCommentPagesRepository.page(po, query));
    }

    /**
     * 根据ID查询.
     * @param id 自增id
     * @return 查询结果
     */
    @GetMapping("details")
    public Result<ArtalkCommentPagesPO> details(@RequestParam Long id) {
        return Result.success(artalkCommentPagesRepository.getById(id));
    }

    /**
     * 根据主键更新.
     * @param cmd 更新指令参数
     * @return 操作是否成功
     */
    @PutMapping("update")
    public Result<Boolean> update(@RequestBody ArtalkCommentPagesUpdateCommand cmd) {
        ArtalkCommentPagesPO po = ArtalkCommentPagesAssembler.INSTANCE.toUpdatePO(cmd);
        return Result.success(artalkCommentPagesRepository.updateById(po));
    }

    /**
     * 新增.
     * @param cmd 新增指令参数
     * @return 操作是否成功
     */
    @PostMapping("save")
    public Result<Boolean> save(@RequestBody ArtalkCommentPagesAddCommand cmd) {
        ArtalkCommentPagesPO po = ArtalkCommentPagesAssembler.INSTANCE.toAddPO(cmd);
        return Result.success(artalkCommentPagesRepository.save(po));
    }

    /**
     * 批量新增.
     * @param cmds 新增参数
     * @return 新增结果
     */
    @PostMapping("saveBatch")
    public Result<Boolean> saveBatch(@RequestBody List<ArtalkCommentPagesAddCommand> cmds) {
        List<ArtalkCommentPagesPO> pos = ArtalkCommentPagesAssembler.INSTANCE.toAddPOS(cmds);
        return Result.success(artalkCommentPagesRepository.saveBatch(pos));
    }

    /**
     * 删除.
     * @param ids 主键
     * @return 操作是否成功
     */
    @DeleteMapping("deleteByIds")
    public Result<Boolean> deleteByIds(@RequestBody List<Long> ids) {
        return Result.success(artalkCommentPagesRepository.removeByIds(ids));
    }

    /**
     * 查询页面统计数据.
     * @param keys 页面唯一键
     * @return 统计数据
     */
    @GetMapping("statistics")
    public Result<List<ArtalkCommentPagesPO>> statistics(@RequestParam List<String> keys) {
        return Result.success(artalkCommentPagesRepository.statistics(keys));
    }

}
