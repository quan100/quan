package com.quan.app.service.friendly.service;

import com.quan.app.common.module.friendly.FriendlyLinkAddCommand;
import com.quan.app.common.module.friendly.FriendlyLinkDTO;
import com.quan.app.common.module.friendly.FriendlyLinkQuery;
import com.quan.app.common.module.friendly.FriendlyLinkUpdateCommand;
import com.quan.common.base.message.PageResult;
import com.quan.common.base.message.Result;
import com.quan.app.service.friendly.feign.FriendlyLinkRepositoryFeign;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * 友情链接
 *
 * @author JavaQuan
 * @version 1.0.0
 * @date 2023-04-14 16:43:32
 */
@RequiredArgsConstructor
@Component
public class FriendlyLinkService {

    private final FriendlyLinkRepositoryFeign friendlyLinkRepositoryFeign;

    /**
     * 查询列表
     *
     * @param query
     * @return
     */
    public Result<PageResult<FriendlyLinkDTO>> page(FriendlyLinkQuery query) {
        return friendlyLinkRepositoryFeign.page(query);
    }

    /**
     * 根据ID查询
     *
     * @param id
     * @return
     */
    public Result<FriendlyLinkDTO> details(Long id) {
        return friendlyLinkRepositoryFeign.details(id);
    }

    /**
     * 根据主键更新
     *
     * @param cmd
     * @return
     */
    public Result<Boolean> update(FriendlyLinkUpdateCommand cmd) {
        return friendlyLinkRepositoryFeign.update(cmd);
    }

    /**
     * 新增
     *
     * @param cmd
     * @return
     */
    public Result<Boolean> save(FriendlyLinkAddCommand cmd) {
        return friendlyLinkRepositoryFeign.save(cmd);
    }

    /**
     * 批量新增
     *
     * @param cmds
     * @return
     */
    public Result<Boolean> saveBatch(List<FriendlyLinkAddCommand> cmds) {
        return friendlyLinkRepositoryFeign.saveBatch(cmds);
    }

    /**
     * 删除
     *
     * @param ids
     * @return
     */
    public Result<Boolean> deleteByIds(List<Long> ids) {
        return friendlyLinkRepositoryFeign.deleteByIds(ids);
    }
}