package com.quan.app.service.article.feign;

import com.quan.app.common.module.article.ArticleCategoryAddCommand;
import com.quan.app.common.module.article.ArticleCategoryDTO;
import com.quan.app.common.module.article.ArticleCategoryQuery;
import com.quan.app.common.module.article.ArticleCategoryUpdateCommand;
import com.quan.common.base.message.PageResult;
import com.quan.common.base.message.Result;
import com.quan.app.service.article.feign.fallback.ArticleCategoryRepositoryFallback;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.cloud.openfeign.SpringQueryMap;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 文章分类
 *
 * @author JavaQuan
 * @version 1.0.0
 * @date 2023-04-14 16:43:32
 */
@FeignClient(value = "${quan.app.feign.core.name}", url = "${quan.app.feign.core.url:}", fallbackFactory = ArticleCategoryRepositoryFallback.class)
public interface ArticleCategoryRepositoryFeign {

    /**
     * 查询列表
     *
     * @param query
     * @return
     */
    @GetMapping("/core/article/category/page")
    Result<PageResult<ArticleCategoryDTO>> page(@SpringQueryMap ArticleCategoryQuery query);

    /**
     * 根据ID查询
     *
     * @param id
     * @return
     */
    @GetMapping("/core/article/category/details")
    Result<ArticleCategoryDTO> details(@RequestParam(value = "id") Long id);

    /**
     * 根据主键更新
     *
     * @param cmd
     * @return
     */
    @PutMapping("/core/article/category/update")
    Result<Boolean> update(@RequestBody ArticleCategoryUpdateCommand cmd);

    /**
     * 新增
     *
     * @param cmd
     * @return
     */
    @PostMapping("/core/article/category/save")
    Result<Boolean> save(@RequestBody ArticleCategoryAddCommand cmd);

    /**
     * 批量新增
     *
     * @param cmds
     * @return
     */
    @PostMapping("/core/article/category/saveBatch")
    Result saveBatch(@RequestBody List<ArticleCategoryAddCommand> cmds);

    /**
     * 删除
     *
     * @param ids
     * @return
     */
    @DeleteMapping("/core/article/category/deleteByIds")
    Result<Boolean> deleteByIds(@RequestBody List<Long> ids);

}
