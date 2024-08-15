package cn.javaquan.app.pm.bff.article.feign;

import cn.javaquan.app.pm.bff.article.feign.fallback.ArticleServiceFallback;
import cn.javaquan.app.common.module.article.ArticleAddCommand;
import cn.javaquan.app.common.module.article.ArticleDTO;
import cn.javaquan.app.common.module.article.ArticleQuery;
import cn.javaquan.app.common.module.article.ArticleUpdateCommand;
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
 * 文章.
 *
 * @author javaquan
 * @since 1.0.0
 */
@FeignClient(value = "${quan.app.feign.service.name}", url = "${quan.app.feign.service.url:}",
        fallbackFactory = ArticleServiceFallback.class)
public interface ArticleServiceFeign {

    /**
     * 查询列表.
     * @param query 查询参数
     * @return 查询结果
     */
    @GetMapping("/service/article/page")
    Result<PageResult<ArticleDTO>> page(@SpringQueryMap ArticleQuery query);

    /**
     * 根据ID查询.
     * @param id 主键
     * @return 查询结果
     */
    @GetMapping("/service/article/details")
    Result<ArticleDTO> details(@RequestParam Long id);

    /**
     * 根据主键更新.
     * @param cmd 更新指令参数
     * @return 操作是否成功
     */
    @PutMapping("/service/article/update")
    Result<Boolean> update(@RequestBody ArticleUpdateCommand cmd);

    /**
     * 新增.
     * @param cmd 新增指令参数
     * @return 操作是否成功
     */
    @PostMapping("/service/article/save")
    Result<Boolean> save(@RequestBody ArticleAddCommand cmd);

    /**
     * 批量新增.
     * @param cmds 新增参数
     * @return 新增结果
     */
    @PostMapping("/service/article/saveBatch")
    Result<Boolean> saveBatch(@RequestBody List<ArticleAddCommand> cmds);

    /**
     * 删除.
     * @param ids 主键
     * @return 操作是否成功
     */
    @DeleteMapping("/service/article/deleteByIds")
    Result<Boolean> deleteByIds(@RequestBody List<Long> ids);

}
