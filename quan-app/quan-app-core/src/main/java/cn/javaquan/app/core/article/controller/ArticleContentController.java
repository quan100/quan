package cn.javaquan.app.core.article.controller;

import cn.javaquan.app.core.article.convert.ArticleContentAssembler;
import cn.javaquan.app.core.article.repository.ArticleContentRepository;
import cn.javaquan.app.common.module.article.ArticleContentAddCommand;
import cn.javaquan.app.common.module.article.ArticleContentUpdateCommand;
import cn.javaquan.common.base.message.Result;
import cn.javaquan.app.core.article.entity.ArticleContentPO;
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
@RequestMapping("/core/article/content/")
public class ArticleContentController {

    private final ArticleContentRepository articleContentRepository;

    /**
     * 根据ID查询.
     * @param articleId 文章ID
     * @return 查询结果
     */
    @GetMapping("details")
    public Result<ArticleContentPO> details(@RequestParam String articleId) {
        return Result.success(articleContentRepository.getOne(articleId));
    }

    /**
     * 根据主键更新.
     * @param cmd 更新指令参数
     * @return 更新操作是否成功
     */
    @PutMapping("update")
    public Result<Boolean> update(@RequestBody ArticleContentUpdateCommand cmd) {
        ArticleContentPO po = ArticleContentAssembler.INSTANCE.toUpdatePO(cmd);
        return Result.success(articleContentRepository.updateByArticleId(po));
    }

    /**
     * 新增.
     * @param cmd 新增指令参数
     * @return 新增操作是否成功
     */
    @PostMapping("save")
    public Result<Boolean> save(@RequestBody ArticleContentAddCommand cmd) {
        ArticleContentPO po = ArticleContentAssembler.INSTANCE.toAddPO(cmd);
        return Result.success(articleContentRepository.save(po));
    }

    /**
     * 删除.
     * @param articleIds 文章ID列表
     * @return 删除结果
     */
    @DeleteMapping("deleteByIds")
    public Result<Boolean> deleteByIds(@RequestBody List<String> articleIds) {
        return Result.success(articleContentRepository.removeByByArticleIds(articleIds));
    }

}
