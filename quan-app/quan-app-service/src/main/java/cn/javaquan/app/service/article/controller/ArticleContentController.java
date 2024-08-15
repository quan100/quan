package cn.javaquan.app.service.article.controller;

import cn.javaquan.app.common.module.article.ArticleContentAddCommand;
import cn.javaquan.app.common.module.article.ArticleContentDTO;
import cn.javaquan.app.common.module.article.ArticleContentUpdateCommand;
import cn.javaquan.common.base.message.Result;
import cn.javaquan.app.service.article.service.ArticleContentService;
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
 * 文章内容.
 *
 * @author javaquan
 * @since 1.0.0
 */
@RequiredArgsConstructor
@RestController
@RequestMapping("/service/article/content/")
public class ArticleContentController {

    private final ArticleContentService articleContentService;

    /**
     * 根据ID查询.
     * @param articleId 文章id
     * @return 查询结果
     */
    @GetMapping("details")
    public Result<ArticleContentDTO> details(@RequestParam String articleId) {
        return articleContentService.details(articleId);
    }

    /**
     * 根据主键更新.
     * @param cmd 更新指令参数
     * @return 操作是否成功
     */
    @PutMapping("update")
    public Result<Boolean> update(@RequestBody ArticleContentUpdateCommand cmd) {
        return articleContentService.update(cmd);
    }

    /**
     * 新增.
     * @param cmd 新增指令参数
     * @return 操作是否成功
     */
    @PostMapping("save")
    public Result<Boolean> save(@RequestBody ArticleContentAddCommand cmd) {
        return articleContentService.save(cmd);
    }

    /**
     * 删除.
     * @param articleIds 文章id
     * @return 操作是否成功
     */
    @DeleteMapping("deleteByIds")
    public Result<Boolean> deleteByIds(@RequestBody List<String> articleIds) {
        return articleContentService.deleteByIds(articleIds);
    }

}
