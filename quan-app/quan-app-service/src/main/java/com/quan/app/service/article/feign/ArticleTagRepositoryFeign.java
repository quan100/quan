package com.quan.app.service.article.feign;

import com.quan.app.common.module.article.ArticleTagAddCommand;
import com.quan.app.common.module.article.ArticleTagDTO;
import com.quan.app.common.module.article.ArticleTagQuery;
import com.quan.app.common.module.article.ArticleTagUpdateCommand;
import com.quan.common.base.message.PageResult;
import com.quan.common.base.message.Result;
import com.quan.app.service.article.feign.fallback.ArticleTagRepositoryFallback;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.cloud.openfeign.SpringQueryMap;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 文章标签
 *
 * @author JavaQuan
 * @version 1.0.0
 * @date 2023-04-14 16:43:32
 */
@FeignClient(value = "${quan.app.feign.core.name}", url = "${quan.app.feign.core.url:}", fallbackFactory = ArticleTagRepositoryFallback.class)
public interface ArticleTagRepositoryFeign {

    /**
     * 查询列表
     *
     * @param query
     * @return
     */
    @GetMapping("/core/article/tag/page")
    Result<PageResult<ArticleTagDTO>> page(@SpringQueryMap ArticleTagQuery query);

    /**
     * 根据ID查询
     *
     * @param id
     * @return
     */
    @GetMapping("/core/article/tag/details")
    Result<ArticleTagDTO> details(@RequestParam(value = "id") Long id);

    /**
     * 根据主键更新
     *
     * @param cmd
     * @return
     */
    @PutMapping("/core/article/tag/update")
    Result<Boolean> update(@RequestBody ArticleTagUpdateCommand cmd);

    /**
     * 新增
     *
     * @param cmd
     * @return
     */
    @PostMapping("/core/article/tag/save")
    Result<Boolean> save(@RequestBody ArticleTagAddCommand cmd);

    /**
     * 批量新增
     *
     * @param cmds
     * @return
     */
    @PostMapping("/core/article/tag/saveBatch")
    Result saveBatch(@RequestBody List<ArticleTagAddCommand> cmds);

    /**
     * 删除
     *
     * @param ids
     * @return
     */
    @DeleteMapping("/core/article/tag/deleteByIds")
    Result<Boolean> deleteByIds(@RequestBody List<Long> ids);

}
