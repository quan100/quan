package cn.javaquan.app.core.article.controller;

import cn.javaquan.app.core.article.convert.ArticleContentAssembler;
import cn.javaquan.app.core.article.repository.ArticleContentRepository;
import cn.javaquan.app.common.module.article.ArticleContentAddCommand;
import cn.javaquan.app.common.module.article.ArticleContentUpdateCommand;
import cn.javaquan.common.base.message.Result;
import cn.javaquan.app.core.article.entity.ArticleContentPO;
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
@RequestMapping("/core/article/content/")
public class ArticleContentController {

    private final ArticleContentRepository articleContentRepository;

    /**
     * 根据ID查询
     *
     * @param articleId
     * @return
     */
    @GetMapping("details")
    public Result details(@RequestParam String articleId) {
        return Result.success(articleContentRepository.getOne(articleId));
    }

    /**
     * 根据主键更新
     *
     * @param cmd
     * @return
     */
    @PutMapping("update")
    public Result update(@RequestBody ArticleContentUpdateCommand cmd) {
        ArticleContentPO po = ArticleContentAssembler.INSTANCE.toUpdatePO(cmd);
        return Result.success(articleContentRepository.updateByArticleId(po));
    }

    /**
     * 新增
     *
     * @param cmd
     * @return
     */
    @PostMapping("save")
    public Result save(@RequestBody ArticleContentAddCommand cmd) {
        ArticleContentPO po = ArticleContentAssembler.INSTANCE.toAddPO(cmd);
        return Result.success(articleContentRepository.save(po));
    }

    /**
     * 删除
     *
     * @param articleIds
     * @return
     */
    @DeleteMapping("deleteByIds")
    public Result deleteByIds(@RequestBody List<String> articleIds) {
        return Result.success(articleContentRepository.removeByByArticleIds(articleIds));
    }
}
