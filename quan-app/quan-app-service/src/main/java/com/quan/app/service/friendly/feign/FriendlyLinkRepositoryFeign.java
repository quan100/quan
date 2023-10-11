package com.quan.app.service.friendly.feign;

import com.quan.app.common.module.friendly.FriendlyLinkAddCommand;
import com.quan.app.common.module.friendly.FriendlyLinkDTO;
import com.quan.app.common.module.friendly.FriendlyLinkQuery;
import com.quan.app.common.module.friendly.FriendlyLinkUpdateCommand;
import com.quan.common.base.message.PageResult;
import com.quan.common.base.message.Result;
import com.quan.app.service.friendly.feign.fallback.FriendlyLinkRepositoryFallback;
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
@FeignClient(value = "${quan.app.feign.core.name}", url = "${quan.app.feign.core.url:}", fallbackFactory = FriendlyLinkRepositoryFallback.class)
public interface FriendlyLinkRepositoryFeign {

    /**
     * 查询列表
     *
     * @param query
     * @return
     */
    @GetMapping("/core/friendly/link/page")
    Result<PageResult<FriendlyLinkDTO>> page(@SpringQueryMap FriendlyLinkQuery query);

    /**
     * 根据ID查询
     *
     * @param id
     * @return
     */
    @GetMapping("/core/friendly/link/details")
    Result<FriendlyLinkDTO> details(@RequestParam(value = "id") Long id);

    /**
     * 根据主键更新
     *
     * @param cmd
     * @return
     */
    @PutMapping("/core/friendly/link/update")
    Result<Boolean> update(@RequestBody FriendlyLinkUpdateCommand cmd);

    /**
     * 新增
     *
     * @param cmd
     * @return
     */
    @PostMapping("/core/friendly/link/save")
    Result<Boolean> save(@RequestBody FriendlyLinkAddCommand cmd);

    /**
     * 批量新增
     *
     * @param cmds
     * @return
     */
    @PostMapping("/core/friendly/link/saveBatch")
    Result saveBatch(@RequestBody List<FriendlyLinkAddCommand> cmds);

    /**
     * 删除
     *
     * @param ids
     * @return
     */
    @DeleteMapping("/core/friendly/link/deleteByIds")
    Result<Boolean> deleteByIds(@RequestBody List<Long> ids);

}
