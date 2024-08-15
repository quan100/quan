package cn.javaquan.app.mobile.bff.friendly.feign;

import cn.javaquan.app.common.module.friendly.FriendlyLinkAddCommand;
import cn.javaquan.app.common.module.friendly.FriendlyLinkDTO;
import cn.javaquan.app.common.module.friendly.FriendlyLinkQuery;
import cn.javaquan.app.common.module.friendly.FriendlyLinkUpdateCommand;
import cn.javaquan.common.base.message.PageResult;
import cn.javaquan.common.base.message.Result;
import cn.javaquan.app.mobile.bff.friendly.feign.fallback.OpenFriendlyLinkServiceFallback;
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
 * 友情链接.
 *
 * @author javaquan
 * @since 1.0.0
 */
@FeignClient(value = "${quan.app.feign.service.name}", url = "${quan.app.feign.service.url:}",
        fallbackFactory = OpenFriendlyLinkServiceFallback.class)
public interface OpenFriendlyLinkServiceFeign {

    /**
     * 查询列表.
     * @param query 查询参数
     * @return 查询结果
     */
    @GetMapping("/service/friendly/link/page")
    Result<PageResult<FriendlyLinkDTO>> page(@SpringQueryMap FriendlyLinkQuery query);

    /**
     * 根据ID查询.
     * @param id 主键
     * @return 查询结果
     */
    @GetMapping("/service/friendly/link/details")
    Result<FriendlyLinkDTO> details(@RequestParam Long id);

    /**
     * 根据主键更新.
     * @param cmd 更新指令参数
     * @return 操作是否成功
     */
    @PutMapping("/service/friendly/link/update")
    Result<Boolean> update(@RequestBody FriendlyLinkUpdateCommand cmd);

    /**
     * 新增.
     * @param cmd 新增指令参数
     * @return 操作是否成功
     */
    @PostMapping("/service/friendly/link/save")
    Result<Boolean> save(@RequestBody FriendlyLinkAddCommand cmd);

    /**
     * 批量新增.
     * @param cmds 新增参数
     * @return 新增结果
     */
    @PostMapping("/service/friendly/link/saveBatch")
    Result<Boolean> saveBatch(@RequestBody List<FriendlyLinkAddCommand> cmds);

    /**
     * 删除.
     * @param ids 主键
     * @return 操作是否成功
     */
    @DeleteMapping("/service/friendly/link/deleteByIds")
    Result<Boolean> deleteByIds(@RequestBody List<Long> ids);

}
