package com.quan.app.service.article.controller;

import com.quan.app.common.module.article.ArticleContentAddCommand;
import com.quan.app.common.module.article.ArticleContentDTO;
import com.quan.app.common.module.article.ArticleContentUpdateCommand;
import com.quan.common.base.message.Result;
import com.quan.app.service.article.service.ArticleContentService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;


/**
 * 文章内容
 *
 * @author JavaQuan
 * @version 1.0.0
 * @date 2023-04-14 16:43:32
 */
@RequiredArgsConstructor
@RestController
@RequestMapping("/service/article/content/")
public class ArticleContentController {

    private final ArticleContentService articleContentService;

    /**
     * 根据ID查询
     *
     * @param articleId
     * @return
     */
    @GetMapping("details")
    public Result<ArticleContentDTO> details(@RequestParam String articleId) {
        return articleContentService.details(articleId);
    }

    /**
     * 根据主键更新
     *
     * @param cmd
     * @return
     */
    @PutMapping("update")
    public Result<Boolean> update(@RequestBody ArticleContentUpdateCommand cmd) {
        return articleContentService.update(cmd);
    }

    /**
     * 新增
     *
     * @param cmd
     * @return
     */
    @PostMapping("save")
    public Result<Boolean> save(@RequestBody ArticleContentAddCommand cmd) {
        return articleContentService.save(cmd);
    }

    /**
     * 删除
     *
     * @param articleIds
     * @return
     */
    @DeleteMapping("deleteByIds")
    public Result<Boolean> deleteByIds(@RequestBody List<String> articleIds) {
        return articleContentService.deleteByIds(articleIds);
    }

}
