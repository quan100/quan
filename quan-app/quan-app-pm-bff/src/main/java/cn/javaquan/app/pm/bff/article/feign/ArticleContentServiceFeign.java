package cn.javaquan.app.pm.bff.article.feign;

import cn.javaquan.app.pm.bff.article.feign.fallback.ArticleContentServiceFallback;
import cn.javaquan.app.common.module.article.ArticleContentAddCommand;
import cn.javaquan.app.common.module.article.ArticleContentDTO;
import cn.javaquan.app.common.module.article.ArticleContentUpdateCommand;
import cn.javaquan.common.base.message.Result;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

/**
 * 文章内容.
 *
 * @author javaquan
 * @since 1.0.0
 */
@FeignClient(value = "${quan.app.feign.service.name}", url = "${quan.app.feign.service.url:}",
        fallbackFactory = ArticleContentServiceFallback.class)
public interface ArticleContentServiceFeign {

    /**
     * 根据ID查询.
     * @param articleId 文章id
     * @return 查询结果
     */
    @GetMapping("/service/article/content/details")
    Result<ArticleContentDTO> details(@RequestParam String articleId);

    /**
     * 根据主键更新.
     * @param cmd 更新指令参数
     * @return 操作是否成功
     */
    @PutMapping("/service/article/content/update")
    Result<Boolean> update(@RequestBody ArticleContentUpdateCommand cmd);

    /**
     * 新增.
     * @param cmd 新增指令参数
     * @return 操作是否成功
     */
    @PostMapping("/service/article/content/save")
    Result<Boolean> save(@RequestBody ArticleContentAddCommand cmd);

    /**
     * 删除.
     * @param articleIds 文章id
     * @return 操作是否成功
     */
    @DeleteMapping("/service/article/content/deleteByIds")
    Result<Boolean> deleteByIds(@RequestBody List<String> articleIds);

}
