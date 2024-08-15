package cn.javaquan.app.mobile.bff.article.feign;

import cn.javaquan.app.mobile.bff.article.feign.fallback.ArticleTagConfigServiceFallback;
import cn.javaquan.app.common.module.article.ArticleTagConfigAddCommand;
import cn.javaquan.app.common.module.article.ArticleTagConfigDTO;
import cn.javaquan.app.common.module.article.ArticleTagConfigQuery;
import cn.javaquan.app.common.module.article.ArticleTagConfigUpdateCommand;
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
 * 文章标签配置.
 *
 * @author javaquan
 * @since 1.0.0
 */
@FeignClient(value = "${quan.app.feign.service.name}", url = "${quan.app.feign.service.url:}",
        fallbackFactory = ArticleTagConfigServiceFallback.class)
public interface ArticleTagConfigServiceFeign {

    /**
     * 查询列表.
     * @param query 查询参数
     * @return 查询结果
     */
    @GetMapping("/service/article/tag/config/page")
    Result<PageResult<ArticleTagConfigDTO>> page(@SpringQueryMap ArticleTagConfigQuery query);

    /**
     * 根据ID查询.
     * @param id 主键
     * @return 查询结果
     */
    @GetMapping("/service/article/tag/config/details")
    Result<ArticleTagConfigDTO> details(@RequestParam Long id);

    /**
     * 根据主键更新.
     * @param cmd 更新指令参数
     * @return 操作是否成功
     */
    @PutMapping("/service/article/tag/config/update")
    Result<Boolean> update(@RequestBody ArticleTagConfigUpdateCommand cmd);

    /**
     * 新增.
     * @param cmd 新增指令参数
     * @return 操作是否成功
     */
    @PostMapping("/service/article/tag/config/save")
    Result<Boolean> save(@RequestBody ArticleTagConfigAddCommand cmd);

    /**
     * 批量新增.
     * @param cmds 新增参数
     * @return 新增结果
     */
    @PostMapping("/service/article/tag/config/saveBatch")
    Result<Boolean> saveBatch(@RequestBody List<ArticleTagConfigAddCommand> cmds);

    /**
     * 删除.
     * @param ids 主键
     * @return 操作是否成功
     */
    @DeleteMapping("/service/article/tag/config/deleteByIds")
    Result<Boolean> deleteByIds(@RequestBody List<Long> ids);

}
