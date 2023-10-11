package com.quan.app.pm.bff.base.controller;

import com.quan.app.common.module.base.BaseConfigAddCommand;
import com.quan.app.common.module.base.BaseConfigDTO;
import com.quan.app.common.module.base.BaseConfigQuery;
import com.quan.app.common.module.base.BaseConfigUpdateCommand;
import com.quan.common.base.message.PageResult;
import com.quan.common.base.message.Result;
import com.quan.app.pm.bff.base.feign.BaseConfigServiceFeign;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;


/**
 * 系统通用配置
 *
 * @author JavaQuan
 * @version 1.0.0
 * @date 2023-04-14 16:43:32
 */
@RequiredArgsConstructor
@RestController
@RequestMapping("/base/config/")
public class BaseConfigController {

    private final BaseConfigServiceFeign baseConfigServiceFeign;

    /**
     * 查询列表
     *
     * @param query
     * @return
     */
    @GetMapping("page")
    public Result<PageResult<BaseConfigDTO>> page(BaseConfigQuery query) {
        return baseConfigServiceFeign.page(query);
    }

    /**
     * 根据ID查询
     *
     * @param id
     * @return
     */
    @GetMapping("details")
    public Result<BaseConfigDTO> details(@RequestParam Integer id) {
        return baseConfigServiceFeign.details(id);
    }

    /**
     * 根据主键更新
     *
     * @param cmd
     * @return
     */
    @PutMapping("update")
    public Result<Boolean> update(@RequestBody BaseConfigUpdateCommand cmd) {
        return baseConfigServiceFeign.update(cmd);
    }

    /**
     * 新增
     *
     * @param cmd
     * @return
     */
    @PostMapping("save")
    public Result<Boolean> save(@RequestBody BaseConfigAddCommand cmd) {
        return baseConfigServiceFeign.save(cmd);
    }

    /**
     * 新增
     *
     * @param cmds
     * @return
     */
    @PostMapping("saveBatch")
    public Result<Boolean> saveBatch(@RequestBody List<BaseConfigAddCommand> cmds) {
        return baseConfigServiceFeign.saveBatch(cmds);
    }

    /**
     * 删除
     *
     * @param ids
     * @return
     */
    @DeleteMapping("deleteByIds")
    public Result<Boolean> deleteByIds(@RequestBody List<Integer> ids) {
        return baseConfigServiceFeign.deleteByIds(ids);
    }

}
