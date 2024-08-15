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
@RequestMapping("/core/base/config/")
public class BaseConfigController {

    private final BaseConfigRepository baseConfigRepository;

    /**
     * 查询列表.
     * @param query 查询参数
     * @return 查询结果
     */
    @GetMapping("page")
    public Result<PageResult<BaseConfigPO>> page(BaseConfigQuery query) {
        BaseConfigPO po = BaseConfigAssembler.INSTANCE.toQueryPO(query);
        return Result.success(baseConfigRepository.page(po, query));
    }

    /**
     * 根据ID查询.
     * @param id 主键
     * @return 查询结果
     */
    @GetMapping("details")
    public Result<BaseConfigPO> details(@RequestParam Integer id) {
        return Result.success(baseConfigRepository.getById(id));
    }

    /**
     * 根据主键更新.
     * @param cmd 更新指令参数
     * @return 更新操作是否成功
     */
    @PutMapping("update")
    public Result<Boolean> update(@RequestBody BaseConfigUpdateCommand cmd) {
        BaseConfigPO po = BaseConfigAssembler.INSTANCE.toUpdatePO(cmd);
        return Result.success(baseConfigRepository.updateById(po));
    }

    /**
     * 新增.
     * @param cmd 新增指令参数
     * @return 新增操作是否成功
     */
    @PostMapping("save")
    public Result<Boolean> save(@RequestBody BaseConfigAddCommand cmd) {
        BaseConfigPO po = BaseConfigAssembler.INSTANCE.toAddPO(cmd);
        return Result.success(baseConfigRepository.save(po));
    }

    /**
     * 批量新增.
     * @param cmds 新增参数
     * @return 新增操作是否成功
     */
    @PostMapping("saveBatch")
    public Result<Boolean> saveBatch(@RequestBody List<BaseConfigAddCommand> cmds) {
        List<BaseConfigPO> pos = BaseConfigAssembler.INSTANCE.toAddPOS(cmds);
        return Result.success(baseConfigRepository.saveBatch(pos));
    }

    /**
     * 删除.
     * @param ids 主键
     * @return 操作是否成功
     */
    @DeleteMapping("deleteByIds")
    public Result<Boolean> deleteByIds(@RequestBody List<Integer> ids) {
        return Result.success(baseConfigRepository.removeByIds(ids));
    }

}
