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
import org.springframework.web.bind.annotation.*;

import java.util.List;


/**
 * 文章标签
 *
 * @author JavaQuan
 * @version 1.0.0
 * @date 2023-04-14 16:43:32
 */
@RequiredArgsConstructor
@RestController
@RequestMapping("/core/article/tag/")
public class ArticleTagController {

    private final ArticleTagRepository articleTagRepository;

    /**
     * 查询列表
     *
     * @param query
     * @return
     */
    @GetMapping("page")
    public Result<PageResult> page(ArticleTagQuery query) {
        ArticleTagPO po = ArticleTagAssembler.INSTANCE.toQueryPO(query);
        return Result.success(articleTagRepository.page(po, query));
    }

    /**
     * 根据ID查询
     *
     * @param id
     * @return
     */
    @GetMapping("details")
    public Result details(@RequestParam Long id) {
        return Result.success(articleTagRepository.getById(id));
    }

    /**
     * 根据主键更新
     *
     * @param cmd
     * @return
     */
    @PutMapping("update")
    public Result update(@RequestBody ArticleTagUpdateCommand cmd) {
        ArticleTagPO po = ArticleTagAssembler.INSTANCE.toUpdatePO(cmd);
        return Result.success(articleTagRepository.updateById(po));
    }

    /**
     * 新增
     *
     * @param cmd
     * @return
     */
    @PostMapping("save")
    public Result save(@RequestBody ArticleTagAddCommand cmd) {
        ArticleTagPO po = ArticleTagAssembler.INSTANCE.toAddPO(cmd);
        return Result.success(articleTagRepository.save(po));
    }

    /**
     * 批量新增
     *
     * @param cmds
     * @return
     */
    @PostMapping("saveBatch")
    public Result saveBatch(@RequestBody List<ArticleTagAddCommand> cmds) {
        List<ArticleTagPO> pos = ArticleTagAssembler.INSTANCE.toAddPOS(cmds);
        return Result.success(articleTagRepository.saveBatch(pos));
    }

    /**
     * 删除
     *
     * @param ids
     * @return
     */
    @DeleteMapping("deleteByIds")
    public Result deleteByIds(@RequestBody List<Long> ids) {
        return Result.success(articleTagRepository.removeByIds(ids));
    }

}
