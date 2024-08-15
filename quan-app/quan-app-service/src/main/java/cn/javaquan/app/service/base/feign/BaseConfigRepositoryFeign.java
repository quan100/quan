package cn.javaquan.app.service.base.feign;

import cn.javaquan.app.service.base.feign.fallback.BaseConfigRepositoryFallback;
import cn.javaquan.app.common.module.base.BaseConfigAddCommand;
import cn.javaquan.app.common.module.base.BaseConfigDTO;
import cn.javaquan.app.common.module.base.BaseConfigQuery;
import cn.javaquan.app.common.module.base.BaseConfigUpdateCommand;
import cn.javaquan.common.base.message.PageResult;
import cn.javaquan.common.base.message.Result;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.cloud.openfeign.SpringQueryMap;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

/**
 * 系统通用配置.
 *
 * @author javaquan
 * @since 1.0.0
 */
@FeignClient(value = "${quan.app.feign.core.name}", url = "${quan.app.feign.core.url:}",
        fallbackFactory = BaseConfigRepositoryFallback.class)
public interface BaseConfigRepositoryFeign {

    /**
     * 查询列表.
     * @param query 查询参数
     * @return 查询结果
     */
    @GetMapping("/core/base/config/page")
    Result<PageResult<BaseConfigDTO>> page(@SpringQueryMap BaseConfigQuery query);

    /**
     * 根据ID查询.
     * @param id 主键
     * @return 查询结果
     */
    @GetMapping("/core/base/config/details")
    Result<BaseConfigDTO> details(@RequestParam Integer id);

    /**
     * 根据主键更新.
     * @param cmd 更新指令参数
     * @return 操作是否成功
     */
    @PutMapping("/core/base/config/update")
    Result<Boolean> update(@RequestBody BaseConfigUpdateCommand cmd);

    /**
     * 新增.
     * @param cmd 新增指令参数
     * @return 操作是否成功
     */
    @PostMapping("/core/base/config/save")
    Result<Boolean> save(@RequestBody BaseConfigAddCommand cmd);

    /**
     * 批量新增.
     * @param cmds 新增参数
     * @return 新增结果
     */
    @PostMapping("/core/base/config/saveBatch")
    Result saveBatch(@RequestBody List<BaseConfigAddCommand> cmds);

    /**
     * 删除.
     * @param ids 主键
     * @return 操作是否成功
     */
    @DeleteMapping("/core/base/config/deleteByIds")
    Result<Boolean> deleteByIds(@RequestBody List<Integer> ids);

}
