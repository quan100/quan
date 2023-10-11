package com.quan.app.service.article.controller;

import com.quan.app.common.module.article.ArticleTagAddCommand;
import com.quan.app.common.module.article.ArticleTagDTO;
import com.quan.app.common.module.article.ArticleTagQuery;
import com.quan.app.common.module.article.ArticleTagUpdateCommand;
import com.quan.common.base.message.PageResult;
import com.quan.common.base.message.Result;
import com.quan.app.service.article.service.ArticleTagService;
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
@RequestMapping("/service/article/tag/")
public class ArticleTagController {

    private final ArticleTagService articleTagService;

    /**
     * 查询列表
     *
     * @param query
     * @return
     */
    @GetMapping("page")
    public Result<PageResult<ArticleTagDTO>> page(ArticleTagQuery query) {
        return articleTagService.page(query);
    }

    /**
     * 根据ID查询
     *
     * @param id
     * @return
     */
    @GetMapping("details")
    public Result<ArticleTagDTO> details(@RequestParam Long id) {
        return articleTagService.details(id);
    }

    /**
     * 根据主键更新
     *
     * @param cmd
     * @return
     */
    @PutMapping("update")
    public Result<Boolean> update(@RequestBody ArticleTagUpdateCommand cmd) {
        return articleTagService.update(cmd);
    }

    /**
     * 新增
     *
     * @param cmd
     * @return
     */
    @PostMapping("save")
    public Result<Boolean> save(@RequestBody ArticleTagAddCommand cmd) {
        return articleTagService.save(cmd);
    }

    /**
     * 批量新增
     *
     * @param cmds
     * @return
     */
    @PostMapping("saveBatch")
    public Result<Boolean> saveBatch(@RequestBody List<ArticleTagAddCommand> cmds) {
        return articleTagService.saveBatch(cmds);
    }

    /**
     * 删除
     *
     * @param ids
     * @return
     */
    @DeleteMapping("deleteByIds")
    public Result<Boolean> deleteByIds(@RequestBody List<Long> ids) {
        return articleTagService.deleteByIds(ids);
    }

}
