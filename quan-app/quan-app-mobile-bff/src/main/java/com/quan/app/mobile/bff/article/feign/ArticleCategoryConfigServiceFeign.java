package com.quan.app.mobile.bff.article.feign;

import com.quan.app.common.module.article.ArticleCategoryConfigAddCommand;
import com.quan.app.common.module.article.ArticleCategoryConfigDTO;
import com.quan.app.common.module.article.ArticleCategoryConfigQuery;
import com.quan.app.common.module.article.ArticleCategoryConfigUpdateCommand;
import com.quan.common.base.message.PageResult;
import com.quan.common.base.message.Result;
import com.quan.app.mobile.bff.article.feign.fallback.ArticleCategoryConfigServiceFallback;
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
@FeignClient(value = "${quan.app.feign.service.name}", url = "${quan.app.feign.service.url:}", fallbackFactory = ArticleCategoryConfigServiceFallback.class)
public interface ArticleCategoryConfigServiceFeign {

    /**
     * 查询列表
     *
     * @param query
     * @return
     */
    @GetMapping("/service/article/category/config/page")
    Result<PageResult<ArticleCategoryConfigDTO>> page(@SpringQueryMap ArticleCategoryConfigQuery query);

    /**
     * 根据ID查询
     *
     * @param id
     * @return
     */
    @GetMapping("/service/article/category/config/details")
    Result<ArticleCategoryConfigDTO> details(@RequestParam(value = "id") Long id);

    /**
     * 根据主键更新
     *
     * @param cmd
     * @return
     */
    @PutMapping("/service/article/category/config/update")
    Result<Boolean> update(@RequestBody ArticleCategoryConfigUpdateCommand cmd);

    /**
     * 新增
     *
     * @param cmd
     * @return
     */
    @PostMapping("/service/article/category/config/save")
    Result<Boolean> save(@RequestBody ArticleCategoryConfigAddCommand cmd);

    /**
     * 批量新增
     *
     * @param cmds
     * @return
     */
    @PostMapping("/service/article/category/config/saveBatch")
    Result<Boolean> saveBatch(@RequestBody List<ArticleCategoryConfigAddCommand> cmds);

    /**
     * 删除
     *
     * @param ids
     * @return
     */
    @DeleteMapping("/service/article/category/config/deleteByIds")
    Result<Boolean> deleteByIds(@RequestBody List<Long> ids);

}
