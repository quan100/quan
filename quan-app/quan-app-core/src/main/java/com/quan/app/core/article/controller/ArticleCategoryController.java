package com.quan.app.core.article.controller;

import com.quan.common.base.message.PageResult;
import com.quan.app.common.module.article.ArticleCategoryAddCommand;
import com.quan.app.common.module.article.ArticleCategoryQuery;
import com.quan.app.common.module.article.ArticleCategoryUpdateCommand;
import com.quan.common.base.message.Result;
import com.quan.app.core.article.convert.ArticleCategoryAssembler;
import com.quan.app.core.article.entity.ArticleCategoryPO;
import com.quan.app.core.article.repository.ArticleCategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;


/**
 * 文章分类
 *
 * @author JavaQuan
 * @version 1.0.0
 * @date 2023-04-14 16:43:32
 */
@RequiredArgsConstructor
@RestController
@RequestMapping("/core/article/category/")
public class ArticleCategoryController {

    private final ArticleCategoryRepository articleCategoryRepository;

    /**
     * 查询列表
     *
     * @param query
     * @return
     */
    @GetMapping("page")
    public Result<PageResult> page(ArticleCategoryQuery query) {
        ArticleCategoryPO po = ArticleCategoryAssembler.INSTANCE.toQueryPO(query);
        return Result.success(articleCategoryRepository.page(po, query));
    }

    /**
     * 根据ID查询
     *
     * @param id
     * @return
     */
    @GetMapping("details")
    public Result details(@RequestParam Long id) {
        return Result.success(articleCategoryRepository.getById(id));
    }

    /**
     * 根据主键更新
     *
     * @param cmd
     * @return
     */
    @PutMapping("update")
    public Result update(@RequestBody ArticleCategoryUpdateCommand cmd) {
        ArticleCategoryPO po = ArticleCategoryAssembler.INSTANCE.toUpdatePO(cmd);
        return Result.success(articleCategoryRepository.updateById(po));
    }

    /**
     * 新增
     *
     * @param cmd
     * @return
     */
    @PostMapping("save")
    public Result save(@RequestBody ArticleCategoryAddCommand cmd) {
        ArticleCategoryPO po = ArticleCategoryAssembler.INSTANCE.toAddPO(cmd);
        return Result.success(articleCategoryRepository.save(po));
    }

    /**
     * 批量新增
     *
     * @param cmds
     * @return
     */
    @PostMapping("saveBatch")
    public Result saveBatch(@RequestBody List<ArticleCategoryAddCommand> cmds) {
        List<ArticleCategoryPO> pos = ArticleCategoryAssembler.INSTANCE.toAddPOS(cmds);
        return Result.success(articleCategoryRepository.saveBatch(pos));
    }

    /**
     * 删除
     *
     * @param ids
     * @return
     */
    @DeleteMapping("deleteByIds")
    public Result deleteByIds(@RequestBody List<Long> ids) {
        return Result.success(articleCategoryRepository.deleteByIds(ids));
    }

}
