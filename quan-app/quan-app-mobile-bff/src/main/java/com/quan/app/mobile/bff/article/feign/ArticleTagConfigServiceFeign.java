package com.quan.app.mobile.bff.article.feign;

import com.quan.app.common.module.article.ArticleTagConfigAddCommand;
import com.quan.app.common.module.article.ArticleTagConfigDTO;
import com.quan.app.common.module.article.ArticleTagConfigQuery;
import com.quan.app.common.module.article.ArticleTagConfigUpdateCommand;
import com.quan.common.base.message.PageResult;
import com.quan.common.base.message.Result;
import com.quan.app.mobile.bff.article.feign.fallback.ArticleTagConfigServiceFallback;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.cloud.openfeign.SpringQueryMap;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 文章标签配置
 *
 * @author JavaQuan
 * @version 1.0.0
 * @date 2023-04-14 16:43:32
 */
@FeignClient(value = "${quan.app.feign.service.name}", url = "${quan.app.feign.service.url:}", fallbackFactory = ArticleTagConfigServiceFallback.class)
public interface ArticleTagConfigServiceFeign {

    /**
     * 查询列表
     *
     * @param query
     * @return
     */
    @GetMapping("/service/article/tag/config/page")
    Result<PageResult<ArticleTagConfigDTO>> page(@SpringQueryMap ArticleTagConfigQuery query);

    /**
     * 根据ID查询
     *
     * @param id
     * @return
     */
    @GetMapping("/service/article/tag/config/details")
    Result<ArticleTagConfigDTO> details(@RequestParam(value = "id") Long id);

    /**
     * 根据主键更新
     *
     * @param cmd
     * @return
     */
    @PutMapping("/service/article/tag/config/update")
    Result<Boolean> update(@RequestBody ArticleTagConfigUpdateCommand cmd);

    /**
     * 新增
     *
     * @param cmd
     * @return
     */
    @PostMapping("/service/article/tag/config/save")
    Result<Boolean> save(@RequestBody ArticleTagConfigAddCommand cmd);

    /**
     * 批量新增
     *
     * @param cmds
     * @return
     */
    @PostMapping("/service/article/tag/config/saveBatch")
    Result<Boolean> saveBatch(@RequestBody List<ArticleTagConfigAddCommand> cmds);

    /**
     * 删除
     *
     * @param ids
     * @return
     */
    @DeleteMapping("/service/article/tag/config/deleteByIds")
    Result<Boolean> deleteByIds(@RequestBody List<Long> ids);

}
