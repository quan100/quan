package cn.javaquan.app.core.article.controller;

import cn.javaquan.app.core.article.convert.ArticleTagAssembler;
import cn.javaquan.app.core.article.entity.ArticleTagPO;
import cn.javaquan.app.core.article.repository.ArticleTagRepository;
import cn.javaquan.common.base.message.PageResult;
import cn.javaquan.app.common.module.article.ArticleTagAddCommand;
import cn.javaquan.app.common.module.article.ArticleTagQuery;
import cn.javaquan.app.common.module.article.ArticleTagUpdateCommand;
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
 * 文章标签.
 *
 * @author javaquan
 * @since 1.0.0
 */
@RequiredArgsConstructor
@RestController
@RequestMapping("/core/article/tag/")
public class ArticleTagController {

    private final ArticleTagRepository articleTagRepository;

    /**
     * 查询列表.
     * @param query 查询参数
     * @return 查询结果
     */
    @GetMapping("page")
    public Result<PageResult<ArticleTagPO>> page(ArticleTagQuery query) {
        ArticleTagPO po = ArticleTagAssembler.INSTANCE.toQueryPO(query);
        return Result.success(articleTagRepository.page(po, query));
    }

    /**
     * 根据ID查询.
     * @param id 主键
     * @return 查询结果
     */
    @GetMapping("details")
    public Result<ArticleTagPO> details(@RequestParam Long id) {
        return Result.success(articleTagRepository.getById(id));
    }

    /**
     * 根据主键更新.
     * @param cmd 更新指令参数
     * @return 更新操作是否成功
     */
    @PutMapping("update")
    public Result<Boolean> update(@RequestBody ArticleTagUpdateCommand cmd) {
        ArticleTagPO po = ArticleTagAssembler.INSTANCE.toUpdatePO(cmd);
        return Result.success(articleTagRepository.updateById(po));
    }

    /**
     * 新增.
     * @param cmd 新增指令参数
     * @return 新增操作是否成功
     */
    @PostMapping("save")
    public Result<Boolean> save(@RequestBody ArticleTagAddCommand cmd) {
        ArticleTagPO po = ArticleTagAssembler.INSTANCE.toAddPO(cmd);
        return Result.success(articleTagRepository.save(po));
    }

    /**
     * 批量新增.
     * @param cmds 新增参数
     * @return 新增操作是否成功
     */
    @PostMapping("saveBatch")
    public Result<Boolean> saveBatch(@RequestBody List<ArticleTagAddCommand> cmds) {
        List<ArticleTagPO> pos = ArticleTagAssembler.INSTANCE.toAddPOS(cmds);
        return Result.success(articleTagRepository.saveBatch(pos));
    }

    /**
     * 删除.
     * @param ids 主键
     * @return 操作是否成功
     */
    @DeleteMapping("deleteByIds")
    public Result<Boolean> deleteByIds(@RequestBody List<Long> ids) {
        return Result.success(articleTagRepository.removeByIds(ids));
    }

}
