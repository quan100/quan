package com.quan.app.pm.bff.friendly.controller;

import com.quan.app.common.module.friendly.FriendlyLinkAddCommand;
import com.quan.app.common.module.friendly.FriendlyLinkDTO;
import com.quan.app.common.module.friendly.FriendlyLinkQuery;
import com.quan.app.common.module.friendly.FriendlyLinkUpdateCommand;
import com.quan.common.base.message.PageResult;
import com.quan.common.base.message.Result;
import com.quan.app.pm.bff.friendly.feign.FriendlyLinkServiceFeign;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;


/**
 * 友情链接
 *
 * @author JavaQuan
 * @version 1.0.0
 * @date 2023-04-14 16:43:32
 */
@RequiredArgsConstructor
@RestController
@RequestMapping("/friendly/link/")
public class FriendlyLinkController {

    private final FriendlyLinkServiceFeign friendlyLinkServiceFeign;

    /**
     * 查询列表
     *
     * @param query
     * @return
     */
    @GetMapping("page")
    public Result<PageResult<FriendlyLinkDTO>> page(FriendlyLinkQuery query) {
        return friendlyLinkServiceFeign.page(query);
    }

    /**
     * 根据ID查询
     *
     * @param id
     * @return
     */
    @GetMapping("details")
    public Result<FriendlyLinkDTO> details(@RequestParam Long id) {
        return friendlyLinkServiceFeign.details(id);
    }

    /**
     * 根据主键更新
     *
     * @param cmd
     * @return
     */
    @PutMapping("update")
    public Result<Boolean> update(@RequestBody FriendlyLinkUpdateCommand cmd) {
        return friendlyLinkServiceFeign.update(cmd);
    }

    /**
     * 新增
     *
     * @param cmd
     * @return
     */
    @PostMapping("save")
    public Result<Boolean> save(@RequestBody FriendlyLinkAddCommand cmd) {
        return friendlyLinkServiceFeign.save(cmd);
    }

    /**
     * 新增
     *
     * @param cmds
     * @return
     */
    @PostMapping("saveBatch")
    public Result<Boolean> saveBatch(@RequestBody List<FriendlyLinkAddCommand> cmds) {
        return friendlyLinkServiceFeign.saveBatch(cmds);
    }

    /**
     * 删除
     *
     * @param ids
     * @return
     */
    @DeleteMapping("deleteByIds")
    public Result<Boolean> deleteByIds(@RequestBody List<Long> ids) {
        return friendlyLinkServiceFeign.deleteByIds(ids);
    }

}
