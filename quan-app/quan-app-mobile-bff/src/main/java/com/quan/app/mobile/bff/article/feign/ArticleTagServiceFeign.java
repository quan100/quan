package com.quan.app.mobile.bff.article.feign;

import com.quan.app.common.module.article.ArticleTagAddCommand;
import com.quan.app.common.module.article.ArticleTagDTO;
import com.quan.app.common.module.article.ArticleTagQuery;
import com.quan.app.common.module.article.ArticleTagUpdateCommand;
import com.quan.common.base.message.PageResult;
import com.quan.common.base.message.Result;
import com.quan.app.mobile.bff.article.feign.fallback.ArticleTagServiceFallback;
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
@FeignClient(value = "${quan.app.feign.service.name}", url = "${quan.app.feign.service.url:}", fallbackFactory = ArticleTagServiceFallback.class)
public interface ArticleTagServiceFeign {

    /**
     * 查询列表
     *
     * @param query
     * @return
     */
    @GetMapping("/service/article/tag/page")
    Result<PageResult<ArticleTagDTO>> page(@SpringQueryMap ArticleTagQuery query);

    /**
     * 根据ID查询
     *
     * @param id
     * @return
     */
    @GetMapping("/service/article/tag/details")
    Result<ArticleTagDTO> details(@RequestParam(value = "id") Long id);

    /**
     * 根据主键更新
     *
     * @param cmd
     * @return
     */
    @PutMapping("/service/article/tag/update")
    Result<Boolean> update(@RequestBody ArticleTagUpdateCommand cmd);

    /**
     * 新增
     *
     * @param cmd
     * @return
     */
    @PostMapping("/service/article/tag/save")
    Result<Boolean> save(@RequestBody ArticleTagAddCommand cmd);

    /**
     * 批量新增
     *
     * @param cmds
     * @return
     */
    @PostMapping("/service/article/tag/saveBatch")
    Result<Boolean> saveBatch(@RequestBody List<ArticleTagAddCommand> cmds);

    /**
     * 删除
     *
     * @param ids
     * @return
     */
    @DeleteMapping("/service/article/tag/deleteByIds")
    Result<Boolean> deleteByIds(@RequestBody List<Long> ids);

}
