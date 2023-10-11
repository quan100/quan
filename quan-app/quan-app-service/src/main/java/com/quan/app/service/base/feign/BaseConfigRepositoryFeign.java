package com.quan.app.service.base.feign;

import com.quan.app.common.module.base.BaseConfigAddCommand;
import com.quan.app.common.module.base.BaseConfigDTO;
import com.quan.app.common.module.base.BaseConfigQuery;
import com.quan.app.common.module.base.BaseConfigUpdateCommand;
import com.quan.common.base.message.PageResult;
import com.quan.common.base.message.Result;
import com.quan.app.service.base.feign.fallback.BaseConfigRepositoryFallback;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.cloud.openfeign.SpringQueryMap;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 系统通用配置
 *
 * @author JavaQuan
 * @version 1.0.0
 * @date 2023-04-04 10:53:59
 */
@FeignClient(value = "${quan.app.feign.core.name}", url = "${quan.app.feign.core.url:}", fallbackFactory = BaseConfigRepositoryFallback.class)
public interface BaseConfigRepositoryFeign {

    /**
     * 查询列表
     *
     * @param query
     * @return
     */
    @GetMapping("/core/base/config/page")
    Result<PageResult<BaseConfigDTO>> page(@SpringQueryMap BaseConfigQuery query);

    /**
     * 根据ID查询
     *
     * @param id
     * @return
     */
    @GetMapping("/core/base/config/details")
    Result<BaseConfigDTO> details(@RequestParam(value = "id") Integer id);

    /**
     * 根据主键更新
     *
     * @param cmd
     * @return
     */
    @PutMapping("/core/base/config/update")
    Result<Boolean> update(@RequestBody BaseConfigUpdateCommand cmd);

    /**
     * 新增
     *
     * @param cmd
     * @return
     */
    @PostMapping("/core/base/config/save")
    Result<Boolean> save(@RequestBody BaseConfigAddCommand cmd);

    /**
     * 批量新增
     *
     * @param cmds
     * @return
     */
    @PostMapping("/core/base/config/saveBatch")
    Result saveBatch(@RequestBody List<BaseConfigAddCommand> cmds);

    /**
     * 删除
     *
     * @param ids
     * @return
     */
    @DeleteMapping("/core/base/config/deleteByIds")
    Result<Boolean> deleteByIds(@RequestBody List<Integer> ids);

}
