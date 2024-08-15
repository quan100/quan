package cn.javaquan.app.mobile.bff.comment.feign;

import cn.javaquan.app.common.module.artalk.ArtalkCommentPagesAddCommand;
import cn.javaquan.app.common.module.artalk.ArtalkCommentPagesDTO;
import cn.javaquan.app.common.module.artalk.ArtalkCommentPagesQuery;
import cn.javaquan.app.common.module.artalk.ArtalkCommentPagesUpdateCommand;
import cn.javaquan.app.mobile.bff.comment.feign.fallback.ArtalkCommentPagesServiceFallback;
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
 * artalk 评论数据查询服务.
 *
 * @author javaquan
 * @since 1.0.0
 */
@FeignClient(value = "${quan.app.feign.service.name}", url = "${quan.app.feign.service.url:}",
        fallbackFactory = ArtalkCommentPagesServiceFallback.class)
public interface ArtalkCommentPagesServiceFeign {

    /**
     * 查询列表.
     * @param query 查询参数
     * @return 查询结果
     */
    @GetMapping("/service/artalk/comment/pages/page")
    Result<PageResult<ArtalkCommentPagesDTO>> page(@SpringQueryMap ArtalkCommentPagesQuery query);

    /**
     * 根据ID查询.
     * @param id 主键
     * @return 查询结果
     */
    @GetMapping("/service/artalk/comment/pages/details")
    Result<ArtalkCommentPagesDTO> details(@RequestParam Long id);

    /**
     * 根据主键更新.
     * @param cmd 更新指令参数
     * @return 操作是否成功
     */
    @PutMapping("/service/artalk/comment/pages/update")
    Result<Boolean> update(@RequestBody ArtalkCommentPagesUpdateCommand cmd);

    /**
     * 新增.
     * @param cmd 新增指令参数
     * @return 操作是否成功
     */
    @PostMapping("/service/artalk/comment/pages/save")
    Result<Boolean> save(@RequestBody ArtalkCommentPagesAddCommand cmd);

    /**
     * 批量新增.
     * @param cmds 新增参数
     * @return 新增结果
     */
    @PostMapping("/service/artalk/comment/pages/saveBatch")
    Result<Boolean> saveBatch(@RequestBody List<ArtalkCommentPagesAddCommand> cmds);

    /**
     * 删除.
     * @param ids 主键
     * @return 操作是否成功
     */
    @DeleteMapping("/service/artalk/comment/pages/deleteByIds")
    Result<Boolean> deleteByIds(@RequestBody List<Long> ids);

    /**
     * 查询页面统计数据.
     * @param keys 页面唯一键
     * @return 统计数据
     */
    @GetMapping("/service/artalk/comment/pages/statistics")
    Result<List<ArtalkCommentPagesDTO>> statistics(@RequestParam List<String> keys);

}
