package cn.javaquan.app.core.base.controller;

import cn.javaquan.common.base.message.PageResult;
import cn.javaquan.app.common.module.base.BaseConfigAddCommand;
import cn.javaquan.app.common.module.base.BaseConfigQuery;
import cn.javaquan.app.common.module.base.BaseConfigUpdateCommand;
import cn.javaquan.common.base.message.Result;
import cn.javaquan.app.core.base.convert.BaseConfigAssembler;
import cn.javaquan.app.core.base.entity.BaseConfigPO;
import cn.javaquan.app.core.base.repository.BaseConfigRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;


/**
 * 系统通用配置
 *
 * @author JavaQuan
 * @version 1.0.0
 * @date 2023-04-04 10:38:39
 */
@RequiredArgsConstructor
@RestController
@RequestMapping("/core/base/config/")
public class BaseConfigController {

    private final BaseConfigRepository baseConfigRepository;

    /**
     * 查询列表
     *
     * @param query
     * @return
     */
    @GetMapping("page")
    public Result<PageResult> page(BaseConfigQuery query) {
        BaseConfigPO po = BaseConfigAssembler.INSTANCE.toQueryPO(query);
        return Result.success(baseConfigRepository.page(po, query));
    }

    /**
     * 根据ID查询
     *
     * @param id
     * @return
     */
    @GetMapping("details")
    public Result details(@RequestParam Integer id) {
        return Result.success(baseConfigRepository.getById(id));
    }

    /**
     * 根据主键更新
     *
     * @param cmd
     * @return
     */
    @PutMapping("update")
    public Result update(@RequestBody BaseConfigUpdateCommand cmd) {
        BaseConfigPO po = BaseConfigAssembler.INSTANCE.toUpdatePO(cmd);
        return Result.success(baseConfigRepository.updateById(po));
    }

    /**
     * 新增
     *
     * @param cmd
     * @return
     */
    @PostMapping("save")
    public Result save(@RequestBody BaseConfigAddCommand cmd) {
        BaseConfigPO po = BaseConfigAssembler.INSTANCE.toAddPO(cmd);
        return Result.success(baseConfigRepository.save(po));
    }

    /**
     * 批量新增
     *
     * @param cmds
     * @return
     */
    @PostMapping("saveBatch")
    public Result saveBatch(@RequestBody List<BaseConfigAddCommand> cmds) {
        List<BaseConfigPO> pos = BaseConfigAssembler.INSTANCE.toAddPOS(cmds);
        return Result.success(baseConfigRepository.saveBatch(pos));
    }

    /**
     * 删除
     *
     * @param ids
     * @return
     */
    @DeleteMapping("deleteByIds")
    public Result deleteByIds(@RequestBody List<Integer> ids) {
        return Result.success(baseConfigRepository.removeByIds(ids));
    }

}
