package com.quan.app.core.article.controller;

import com.quan.common.base.message.PageResult;
import com.quan.app.common.module.article.ArticleTagConfigAddCommand;
import com.quan.app.common.module.article.ArticleTagConfigQuery;
import com.quan.app.common.module.article.ArticleTagConfigUpdateCommand;
import com.quan.common.base.message.Result;
import com.quan.app.core.article.convert.ArticleTagConfigAssembler;
import com.quan.app.core.article.entity.ArticleTagConfigPO;
import com.quan.app.core.article.repository.ArticleTagConfigRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;


/**
 * 文章标签配置
 *
 * @author JavaQuan
 * @version 1.0.0
 * @date 2023-04-14 16:43:32
 */
@RequiredArgsConstructor
@RestController
@RequestMapping("/core/article/tag/config/")
public class ArticleTagConfigController {

    private final ArticleTagConfigRepository articleTagConfigRepository;

    /**
     * 查询列表
     *
     * @param query
     * @return
     */
    @GetMapping("page")
    public Result<PageResult> page(ArticleTagConfigQuery query) {
        ArticleTagConfigPO po = ArticleTagConfigAssembler.INSTANCE.toQueryPO(query);
        return Result.success(articleTagConfigRepository.page(po, query));
    }

    /**
     * 根据ID查询
     *
     * @param id
     * @return
     */
    @GetMapping("details")
    public Result details(@RequestParam Long id) {
        return Result.success(articleTagConfigRepository.getById(id));
    }

    /**
     * 根据主键更新
     *
     * @param cmd
     * @return
     */
    @PutMapping("update")
    public Result update(@RequestBody ArticleTagConfigUpdateCommand cmd) {
        ArticleTagConfigPO po = ArticleTagConfigAssembler.INSTANCE.toUpdatePO(cmd);
        return Result.success(articleTagConfigRepository.updateById(po));
    }

    /**
     * 新增
     *
     * @param cmd
     * @return
     */
    @PostMapping("save")
    public Result save(@RequestBody ArticleTagConfigAddCommand cmd) {
        ArticleTagConfigPO po = ArticleTagConfigAssembler.INSTANCE.toAddPO(cmd);
        return Result.success(articleTagConfigRepository.save(po));
    }

    /**
     * 批量新增
     *
     * @param cmds
     * @return
     */
    @PostMapping("saveBatch")
    public Result saveBatch(@RequestBody List<ArticleTagConfigAddCommand> cmds) {
        List<ArticleTagConfigPO> pos = ArticleTagConfigAssembler.INSTANCE.toAddPOS(cmds);
        return Result.success(articleTagConfigRepository.saveBatch(pos));
    }

    /**
     * 删除
     *
     * @param ids
     * @return
     */
    @DeleteMapping("deleteByIds")
    public Result deleteByIds(@RequestBody List<Long> ids) {
        return Result.success(articleTagConfigRepository.removeByIds(ids));
    }

}
