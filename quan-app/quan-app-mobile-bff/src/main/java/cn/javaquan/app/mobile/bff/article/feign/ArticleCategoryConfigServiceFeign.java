package cn.javaquan.app.mobile.bff.article.feign;

import cn.javaquan.app.mobile.bff.article.feign.fallback.ArticleCategoryConfigServiceFallback;
import cn.javaquan.app.common.module.article.ArticleCategoryConfigAddCommand;
import cn.javaquan.app.common.module.article.ArticleCategoryConfigDTO;
import cn.javaquan.app.common.module.article.ArticleCategoryConfigQuery;
import cn.javaquan.app.common.module.article.ArticleCategoryConfigUpdateCommand;
import cn.javaquan.common.base.message.PageResult;
import cn.javaquan.common.base.message.Result;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.cloud.openfeign.SpringQueryMap;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

/**
 * 文章分类配置.
 *
 * @author javaquan
 * @since 1.0.0
 */
@FeignClient(value = "${quan.app.feign.service.name}", url = "${quan.app.feign.service.url:}",
        fallbackFactory = ArticleCategoryConfigServiceFallback.class)
public interface ArticleCategoryConfigServiceFeign {

    /**
     * 查询列表.
     * @param query 查询参数
     * @return 查询结果
     */
    @GetMapping("/service/article/category/config/page")
    Result<PageResult<ArticleCategoryConfigDTO>> page(@SpringQueryMap ArticleCategoryConfigQuery query);

    /**
     * 根据ID查询.
     * @param id 主键
     * @return 查询结果
     */
    @GetMapping("/service/article/category/config/details")
    Result<ArticleCategoryConfigDTO> details(@RequestParam Long id);

    /**
     * 根据主键更新.
     * @param cmd 更新指令参数
     * @return 操作是否成功
     */
    @PutMapping("/service/article/category/config/update")
    Result<Boolean> update(@RequestBody ArticleCategoryConfigUpdateCommand cmd);

    /**
     * 新增.
     * @param cmd 新增指令参数
     * @return 操作是否成功
     */
    @PostMapping("/service/article/category/config/save")
    Result<Boolean> save(@RequestBody ArticleCategoryConfigAddCommand cmd);

    /**
     * 批量新增.
     * @param cmds 新增参数
     * @return 新增结果
     */
    @PostMapping("/service/article/category/config/saveBatch")
    Result<Boolean> saveBatch(@RequestBody List<ArticleCategoryConfigAddCommand> cmds);

    /**
     * 删除.
     * @param ids 主键
     * @return 操作是否成功
     */
    @DeleteMapping("/service/article/category/config/deleteByIds")
    Result<Boolean> deleteByIds(@RequestBody List<Long> ids);

}
