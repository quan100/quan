package cn.javaquan.app.core.friendly.controller;

import cn.javaquan.app.core.friendly.repository.FriendlyLinkRepository;
import cn.javaquan.common.base.message.PageResult;
import cn.javaquan.app.common.module.friendly.FriendlyLinkAddCommand;
import cn.javaquan.app.common.module.friendly.FriendlyLinkQuery;
import cn.javaquan.app.common.module.friendly.FriendlyLinkUpdateCommand;
import cn.javaquan.common.base.message.Result;
import cn.javaquan.app.core.friendly.convert.FriendlyLinkAssembler;
import cn.javaquan.app.core.friendly.entity.FriendlyLinkPO;
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
 * 友情链接.
 *
 * @author javaquan
 * @since 1.0.0
 */
@RequiredArgsConstructor
@RestController
@RequestMapping("/core/friendly/link/")
public class FriendlyLinkController {

    private final FriendlyLinkRepository friendlyLinkRepository;

    /**
     * 查询列表.
     * @param query 查询参数
     * @return 查询结果
     */
    @GetMapping("page")
    public Result<PageResult<FriendlyLinkPO>> page(FriendlyLinkQuery query) {
        FriendlyLinkPO po = FriendlyLinkAssembler.INSTANCE.toQueryPO(query);
        return Result.success(friendlyLinkRepository.page(po, query));
    }

    /**
     * 根据ID查询.
     * @param id 主键
     * @return 查询结果
     */
    @GetMapping("details")
    public Result<FriendlyLinkPO> details(@RequestParam Long id) {
        return Result.success(friendlyLinkRepository.getById(id));
    }

    /**
     * 根据主键更新.
     * @param cmd 更新指令参数
     * @return 更新操作是否成功
     */
    @PutMapping("update")
    public Result<Boolean> update(@RequestBody FriendlyLinkUpdateCommand cmd) {
        FriendlyLinkPO po = FriendlyLinkAssembler.INSTANCE.toUpdatePO(cmd);
        return Result.success(friendlyLinkRepository.updateById(po));
    }

    /**
     * 新增.
     * @param cmd 新增指令参数
     * @return 新增操作是否成功
     */
    @PostMapping("save")
    public Result<Boolean> save(@RequestBody FriendlyLinkAddCommand cmd) {
        FriendlyLinkPO po = FriendlyLinkAssembler.INSTANCE.toAddPO(cmd);
        return Result.success(friendlyLinkRepository.save(po));
    }

    /**
     * 批量新增.
     * @param cmds 新增参数
     * @return 新增操作是否成功
     */
    @PostMapping("saveBatch")
    public Result<Boolean> saveBatch(@RequestBody List<FriendlyLinkAddCommand> cmds) {
        List<FriendlyLinkPO> pos = FriendlyLinkAssembler.INSTANCE.toAddPOS(cmds);
        return Result.success(friendlyLinkRepository.saveBatch(pos));
    }

    /**
     * 删除.
     * @param ids 主键
     * @return 操作是否成功
     */
    @DeleteMapping("deleteByIds")
    public Result<Boolean> deleteByIds(@RequestBody List<Long> ids) {
        return Result.success(friendlyLinkRepository.removeByIds(ids));
    }

}
