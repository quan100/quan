package cn.javaquan.app.pm.bff.base.controller;

import cn.javaquan.app.common.module.base.BaseConfigAddCommand;
import cn.javaquan.app.common.module.base.BaseConfigDTO;
import cn.javaquan.app.common.module.base.BaseConfigQuery;
import cn.javaquan.app.common.module.base.BaseConfigUpdateCommand;
import cn.javaquan.common.base.message.PageResult;
import cn.javaquan.common.base.message.Result;
import cn.javaquan.app.pm.bff.base.feign.BaseConfigServiceFeign;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * 系统通用配置.
 *
 * @author javaquan
 * @since 1.0.0
 */
@RequiredArgsConstructor
@RestController
@RequestMapping("/base/config/")
public class BaseConfigController {

    private final BaseConfigServiceFeign baseConfigServiceFeign;

    /**
     * 查询列表.
     * @param query 查询参数
     * @return 查询结果
     */
    @GetMapping("page")
    public Result<PageResult<BaseConfigDTO>> page(BaseConfigQuery query) {
        return baseConfigServiceFeign.page(query);
    }

    /**
     * 根据ID查询.
     * @param id 主键
     * @return 查询结果
     */
    @GetMapping("details")
    public Result<BaseConfigDTO> details(@RequestParam Integer id) {
        return baseConfigServiceFeign.details(id);
    }

    /**
     * 根据主键更新.
     * @param cmd 更新指令参数
     * @return 操作是否成功
     */
    @PutMapping("update")
    public Result<Boolean> update(@RequestBody BaseConfigUpdateCommand cmd) {
        return baseConfigServiceFeign.update(cmd);
    }

    /**
     * 新增.
     * @param cmd 新增指令参数
     * @return 操作是否成功
     */
    @PostMapping("save")
    public Result<Boolean> save(@RequestBody BaseConfigAddCommand cmd) {
        return baseConfigServiceFeign.save(cmd);
    }

    /**
     * 新增.
     * @param cmds 新增指令参数
     * @return 新增操作是否成功
     */
    @PostMapping("saveBatch")
    public Result<Boolean> saveBatch(@RequestBody List<BaseConfigAddCommand> cmds) {
        return baseConfigServiceFeign.saveBatch(cmds);
    }

    /**
     * 删除.
     * @param ids 主键
     * @return 操作是否成功
     */
    @DeleteMapping("deleteByIds")
    public Result<Boolean> deleteByIds(@RequestBody List<Integer> ids) {
        return baseConfigServiceFeign.deleteByIds(ids);
    }

}
