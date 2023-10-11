package com.quan.app.core.friendly.controller;

import com.quan.common.base.message.PageResult;
import com.quan.app.common.module.friendly.FriendlyLinkAddCommand;
import com.quan.app.common.module.friendly.FriendlyLinkQuery;
import com.quan.app.common.module.friendly.FriendlyLinkUpdateCommand;
import com.quan.common.base.message.Result;
import com.quan.app.core.friendly.convert.FriendlyLinkAssembler;
import com.quan.app.core.friendly.entity.FriendlyLinkPO;
import com.quan.app.core.friendly.repository.FriendlyLinkRepository;
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
@RequestMapping("/core/friendly/link/")
public class FriendlyLinkController {

    private final FriendlyLinkRepository friendlyLinkRepository;

    /**
     * 查询列表
     *
     * @param query
     * @return
     */
    @GetMapping("page")
    public Result<PageResult> page(FriendlyLinkQuery query) {
        FriendlyLinkPO po = FriendlyLinkAssembler.INSTANCE.toQueryPO(query);
        return Result.success(friendlyLinkRepository.page(po, query));
    }

    /**
     * 根据ID查询
     *
     * @param id
     * @return
     */
    @GetMapping("details")
    public Result details(@RequestParam Long id) {
        return Result.success(friendlyLinkRepository.getById(id));
    }

    /**
     * 根据主键更新
     *
     * @param cmd
     * @return
     */
    @PutMapping("update")
    public Result update(@RequestBody FriendlyLinkUpdateCommand cmd) {
        FriendlyLinkPO po = FriendlyLinkAssembler.INSTANCE.toUpdatePO(cmd);
        return Result.success(friendlyLinkRepository.updateById(po));
    }

    /**
     * 新增
     *
     * @param cmd
     * @return
     */
    @PostMapping("save")
    public Result save(@RequestBody FriendlyLinkAddCommand cmd) {
        FriendlyLinkPO po = FriendlyLinkAssembler.INSTANCE.toAddPO(cmd);
        return Result.success(friendlyLinkRepository.save(po));
    }

    /**
     * 批量新增
     *
     * @param cmds
     * @return
     */
    @PostMapping("saveBatch")
    public Result saveBatch(@RequestBody List<FriendlyLinkAddCommand> cmds) {
        List<FriendlyLinkPO> pos = FriendlyLinkAssembler.INSTANCE.toAddPOS(cmds);
        return Result.success(friendlyLinkRepository.saveBatch(pos));
    }

    /**
     * 删除
     *
     * @param ids
     * @return
     */
    @DeleteMapping("deleteByIds")
    public Result deleteByIds(@RequestBody List<Long> ids) {
        return Result.success(friendlyLinkRepository.removeByIds(ids));
    }

}
