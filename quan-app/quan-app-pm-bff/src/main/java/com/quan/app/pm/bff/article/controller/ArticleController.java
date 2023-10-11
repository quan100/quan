package com.quan.app.pm.bff.article.controller;

import com.quan.app.common.module.article.ArticleAddCommand;
import com.quan.app.common.module.article.ArticleDTO;
import com.quan.app.common.module.article.ArticleQuery;
import com.quan.app.common.module.article.ArticleUpdateCommand;
import com.quan.common.base.message.PageResult;
import com.quan.common.base.message.Result;
import com.quan.app.pm.bff.article.feign.ArticleServiceFeign;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;


/**
 * 文章
 *
 * @author JavaQuan
 * @version 1.0.0
 * @date 2023-04-14 16:43:32
 */
@RequiredArgsConstructor
@RestController
@RequestMapping("/article/")
public class ArticleController {

    private final ArticleServiceFeign articleServiceFeign;

    /**
     * 查询列表
     *
     * @param query
     * @return
     */
    @GetMapping("page")
    public Result<PageResult<ArticleDTO>> page(ArticleQuery query) {
        return articleServiceFeign.page(query);
    }

    /**
     * 根据ID查询
     *
     * @param id
     * @return
     */
    @GetMapping("details")
    public Result<ArticleDTO> details(@RequestParam Long id) {
        return articleServiceFeign.details(id);
    }

    /**
     * 根据主键更新
     *
     * @param cmd
     * @return
     */
    @PutMapping("update")
    public Result<Boolean> update(@RequestBody ArticleUpdateCommand cmd) {
        return articleServiceFeign.update(cmd);
    }

    /**
     * 新增
     *
     * @param cmd
     * @return
     */
    @PostMapping("save")
    public Result<Boolean> save(@RequestBody ArticleAddCommand cmd) {
        return articleServiceFeign.save(cmd);
    }

    /**
     * 新增
     *
     * @param cmds
     * @return
     */
    @PostMapping("saveBatch")
    public Result<Boolean> saveBatch(@RequestBody List<ArticleAddCommand> cmds) {
        return articleServiceFeign.saveBatch(cmds);
    }

    /**
     * 删除
     *
     * @param ids
     * @return
     */
    @DeleteMapping("deleteByIds")
    public Result<Boolean> deleteByIds(@RequestBody List<Long> ids) {
        return articleServiceFeign.deleteByIds(ids);
    }

}
