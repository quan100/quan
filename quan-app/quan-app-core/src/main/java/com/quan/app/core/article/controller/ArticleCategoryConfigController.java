package com.quan.app.core.article.controller;

import com.quan.app.common.module.article.ArticleCategoryConfigAddCommand;
import com.quan.app.common.module.article.ArticleCategoryConfigQuery;
import com.quan.app.common.module.article.ArticleCategoryConfigUpdateCommand;
import com.quan.common.base.message.PageResult;
import com.quan.common.base.message.Result;
import com.quan.app.core.article.convert.ArticleCategoryConfigAssembler;
import com.quan.app.core.article.entity.ArticleCategoryConfigPO;
import com.quan.app.core.article.repository.ArticleCategoryConfigRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;


/**
 * 文章分类配置
 *
 * @author JavaQuan
 * @version 1.0.0
 * @date 2023-04-14 16:43:32
 */
@RequiredArgsConstructor
@RestController
@RequestMapping("/core/article/category/config/")
public class ArticleCategoryConfigController {

    private final ArticleCategoryConfigRepository articleCategoryConfigRepository;

    /**
     * 查询列表
     *
     * @param query
     * @return
     */
    @GetMapping("page")
    public Result<PageResult> page(ArticleCategoryConfigQuery query) {
        ArticleCategoryConfigPO po = ArticleCategoryConfigAssembler.INSTANCE.toQueryPO(query);
        return Result.success(articleCategoryConfigRepository.page(po, query));
    }

    /**
     * 根据ID查询
     *
     * @param id
     * @return
     */
    @GetMapping("details")
    public Result details(@RequestParam Long id) {
        return Result.success(articleCategoryConfigRepository.getById(id));
    }

    /**
     * 根据主键更新
     *
     * @param cmd
     * @return
     */
    @PutMapping("update")
    public Result update(@RequestBody ArticleCategoryConfigUpdateCommand cmd) {
        ArticleCategoryConfigPO po = ArticleCategoryConfigAssembler.INSTANCE.toUpdatePO(cmd);
        return Result.success(articleCategoryConfigRepository.updateById(po));
    }

    /**
     * 新增
     *
     * @param cmd
     * @return
     */
    @PostMapping("save")
    public Result save(@RequestBody ArticleCategoryConfigAddCommand cmd) {
        ArticleCategoryConfigPO po = ArticleCategoryConfigAssembler.INSTANCE.toAddPO(cmd);
        return Result.success(articleCategoryConfigRepository.save(po));
    }

    /**
     * 批量新增
     *
     * @param cmds
     * @return
     */
    @PostMapping("saveBatch")
    public Result saveBatch(@RequestBody List<ArticleCategoryConfigAddCommand> cmds) {
        List<ArticleCategoryConfigPO> pos = ArticleCategoryConfigAssembler.INSTANCE.toAddPOS(cmds);
        return Result.success(articleCategoryConfigRepository.saveBatch(pos));
    }

    /**
     * 删除
     *
     * @param ids
     * @return
     */
    @DeleteMapping("deleteByIds")
    public Result deleteByIds(@RequestBody List<Long> ids) {
        return Result.success(articleCategoryConfigRepository.removeByIds(ids));
    }

}
