package cn.javaquan.app.service.article.feign;

import cn.javaquan.app.service.article.feign.fallback.ArticleCategoryRepositoryFallback;
import cn.javaquan.app.common.module.article.ArticleCategoryAddCommand;
import cn.javaquan.app.common.module.article.ArticleCategoryDTO;
import cn.javaquan.app.common.module.article.ArticleCategoryQuery;
import cn.javaquan.app.common.module.article.ArticleCategoryUpdateCommand;
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
 * 文章分类.
 *
 * @author javaquan
 * @since 1.0.0
 */
@FeignClient(value = "${quan.app.feign.core.name}", url = "${quan.app.feign.core.url:}",
        fallbackFactory = ArticleCategoryRepositoryFallback.class)
public interface ArticleCategoryRepositoryFeign {

    /**
     * 查询列表.
     * @param query 查询参数
     * @return 查询结果
     */
    @GetMapping("/core/article/category/page")
    Result<PageResult<ArticleCategoryDTO>> page(@SpringQueryMap ArticleCategoryQuery query);

    /**
     * 根据ID查询.
     * @param id 主键
     * @return 查询结果
     */
    @GetMapping("/core/article/category/details")
    Result<ArticleCategoryDTO> details(@RequestParam Long id);

    /**
     * 根据主键更新.
     * @param cmd 更新指令参数
     * @return 操作是否成功
     */
    @PutMapping("/core/article/category/update")
    Result<Boolean> update(@RequestBody ArticleCategoryUpdateCommand cmd);

    /**
     * 新增.
     * @param cmd 新增指令参数
     * @return 操作是否成功
     */
    @PostMapping("/core/article/category/save")
    Result<Boolean> save(@RequestBody ArticleCategoryAddCommand cmd);

    /**
     * 批量新增.
     * @param cmds 新增参数
     * @return 新增结果
     */
    @PostMapping("/core/article/category/saveBatch")
    Result saveBatch(@RequestBody List<ArticleCategoryAddCommand> cmds);

    /**
     * 删除.
     * @param ids 主键
     * @return 操作是否成功
     */
    @DeleteMapping("/core/article/category/deleteByIds")
    Result<Boolean> deleteByIds(@RequestBody List<Long> ids);

}
