package cn.javaquan.app.pm.bff.friendly.feign;

import cn.javaquan.app.pm.bff.friendly.feign.fallback.FriendlyLinkServiceFallback;
import cn.javaquan.app.common.module.friendly.FriendlyLinkAddCommand;
import cn.javaquan.app.common.module.friendly.FriendlyLinkDTO;
import cn.javaquan.app.common.module.friendly.FriendlyLinkQuery;
import cn.javaquan.app.common.module.friendly.FriendlyLinkUpdateCommand;
import cn.javaquan.common.base.message.PageResult;
import cn.javaquan.common.base.message.Result;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.cloud.openfeign.SpringQueryMap;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 友情链接
 *
 * @author JavaQuan
 * @version 1.0.0
 * @date 2023-04-14 16:43:32
 */
@FeignClient(value = "${quan.app.feign.service.name}", url = "${quan.app.feign.service.url:}", fallbackFactory = FriendlyLinkServiceFallback.class)
public interface FriendlyLinkServiceFeign {

    /**
     * 查询列表
     *
     * @param query
     * @return
     */
    @GetMapping("/service/friendly/link/page")
    Result<PageResult<FriendlyLinkDTO>> page(@SpringQueryMap FriendlyLinkQuery query);

    /**
     * 根据ID查询
     *
     * @param id
     * @return
     */
    @GetMapping("/service/friendly/link/details")
    Result<FriendlyLinkDTO> details(@RequestParam(value = "id") Long id);

    /**
     * 根据主键更新
     *
     * @param cmd
     * @return
     */
    @PutMapping("/service/friendly/link/update")
    Result<Boolean> update(@RequestBody FriendlyLinkUpdateCommand cmd);

    /**
     * 新增
     *
     * @param cmd
     * @return
     */
    @PostMapping("/service/friendly/link/save")
    Result<Boolean> save(@RequestBody FriendlyLinkAddCommand cmd);

    /**
     * 批量新增
     *
     * @param cmds
     * @return
     */
    @PostMapping("/service/friendly/link/saveBatch")
    Result<Boolean> saveBatch(@RequestBody List<FriendlyLinkAddCommand> cmds);

    /**
     * 删除
     *
     * @param ids
     * @return
     */
    @DeleteMapping("/service/friendly/link/deleteByIds")
    Result<Boolean> deleteByIds(@RequestBody List<Long> ids);

}
