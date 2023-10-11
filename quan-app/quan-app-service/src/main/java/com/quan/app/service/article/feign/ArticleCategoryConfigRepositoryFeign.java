package com.quan.app.service.article.feign;

import com.quan.app.common.module.article.ArticleCategoryConfigAddCommand;
import com.quan.app.common.module.article.ArticleCategoryConfigDTO;
import com.quan.app.common.module.article.ArticleCategoryConfigQuery;
import com.quan.app.common.module.article.ArticleCategoryConfigUpdateCommand;
import com.quan.common.base.message.PageResult;
import com.quan.common.base.message.Result;
import com.quan.app.service.article.feign.fallback.ArticleCategoryConfigRepositoryFallback;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.cloud.openfeign.SpringQueryMap;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 文章分类配置
 *
 * @author JavaQuan
 * @version 1.0.0
 * @date 2023-04-14 16:43:32
 */
@FeignClient(value = "${quan.app.feign.core.name}", url = "${quan.app.feign.core.url:}", fallbackFactory = ArticleCategoryConfigRepositoryFallback.class)
public interface ArticleCategoryConfigRepositoryFeign {

    /**
     * 查询列表
     *
     * @param query
     * @return
     */
    @GetMapping("/core/article/category/config/page")
    Result<PageResult<ArticleCategoryConfigDTO>> page(@SpringQueryMap ArticleCategoryConfigQuery query);

    /**
     * 根据ID查询
     *
     * @param id
     * @return
     */
    @GetMapping("/core/article/category/config/details")
    Result<ArticleCategoryConfigDTO> details(@RequestParam(value = "id") Long id);

    /**
     * 根据主键更新
     *
     * @param cmd
     * @return
     */
    @PutMapping("/core/article/category/config/update")
    Result<Boolean> update(@RequestBody ArticleCategoryConfigUpdateCommand cmd);

    /**
     * 新增
     *
     * @param cmd
     * @return
     */
    @PostMapping("/core/article/category/config/save")
    Result<Boolean> save(@RequestBody ArticleCategoryConfigAddCommand cmd);

    /**
     * 批量新增
     *
     * @param cmds
     * @return
     */
    @PostMapping("/core/article/category/config/saveBatch")
    Result saveBatch(@RequestBody List<ArticleCategoryConfigAddCommand> cmds);

    /**
     * 删除
     *
     * @param ids
     * @return
     */
    @DeleteMapping("/core/article/category/config/deleteByIds")
    Result<Boolean> deleteByIds(@RequestBody List<Long> ids);

}
