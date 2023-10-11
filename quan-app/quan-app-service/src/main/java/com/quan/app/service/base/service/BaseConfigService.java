package com.quan.app.service.base.service;

import com.quan.app.common.module.base.BaseConfigAddCommand;
import com.quan.app.common.module.base.BaseConfigDTO;
import com.quan.app.common.module.base.BaseConfigQuery;
import com.quan.app.common.module.base.BaseConfigUpdateCommand;
import com.quan.common.base.message.PageResult;
import com.quan.common.base.message.Result;
import com.quan.app.service.base.feign.BaseConfigRepositoryFeign;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * 系统通用配置
 *
 * @author JavaQuan
 * @version 1.0.0
 * @date 2023-04-04 10:53:59
 */
@RequiredArgsConstructor
@Component
public class BaseConfigService {

    private final BaseConfigRepositoryFeign baseConfigRepositoryFeign;

    /**
     * 查询列表
     *
     * @param query
     * @return
     */
    public Result<PageResult<BaseConfigDTO>> page(BaseConfigQuery query) {
        return baseConfigRepositoryFeign.page(query);
    }

    /**
     * 根据ID查询
     *
     * @param id
     * @return
     */
    public Result<BaseConfigDTO> details(Integer id) {
        return baseConfigRepositoryFeign.details(id);
    }

    /**
     * 根据主键更新
     *
     * @param cmd
     * @return
     */
    public Result<Boolean> update(BaseConfigUpdateCommand cmd) {
        return baseConfigRepositoryFeign.update(cmd);
    }

    /**
     * 新增
     *
     * @param cmd
     * @return
     */
    public Result<Boolean> save(BaseConfigAddCommand cmd) {
        return baseConfigRepositoryFeign.save(cmd);
    }

    /**
     * 批量新增
     *
     * @param cmds
     * @return
     */
    public Result<Boolean> saveBatch(List<BaseConfigAddCommand> cmds) {
        return baseConfigRepositoryFeign.saveBatch(cmds);
    }

    /**
     * 删除
     *
     * @param ids
     * @return
     */
    public Result<Boolean> deleteByIds(List<Integer> ids) {
        return baseConfigRepositoryFeign.deleteByIds(ids);
    }
}