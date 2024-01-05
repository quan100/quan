package cn.javaquan.app.pm.bff.dictionary.feign;

import cn.javaquan.app.pm.bff.dictionary.feign.fallback.DictionaryServiceFallback;
import cn.javaquan.app.common.module.dictionary.DictionaryAddCommand;
import cn.javaquan.app.common.module.dictionary.DictionaryDTO;
import cn.javaquan.app.common.module.dictionary.DictionaryQuery;
import cn.javaquan.app.common.module.dictionary.DictionaryUpdateCommand;
import cn.javaquan.common.base.message.PageResult;
import cn.javaquan.common.base.message.Result;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.cloud.openfeign.SpringQueryMap;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 字典
 *
 * @author JavaQuan
 * @version 1.0.0
 * @date 2023-04-14 16:43:32
 */
@FeignClient(value = "${quan.app.feign.service.name}", url = "${quan.app.feign.service.url:}", fallbackFactory = DictionaryServiceFallback.class)
public interface DictionaryServiceFeign {

    /**
     * 查询列表
     *
     * @param query
     * @return
     */
    @GetMapping("/service/dictionary/page")
    Result<PageResult<DictionaryDTO>> page(@SpringQueryMap DictionaryQuery query);

    /**
     * 根据ID查询
     *
     * @param id
     * @return
     */
    @GetMapping("/service/dictionary/details")
    Result<DictionaryDTO> details(@RequestParam(value = "id") Long id);

    /**
     * 根据主键更新
     *
     * @param cmd
     * @return
     */
    @PutMapping("/service/dictionary/update")
    Result<Boolean> update(@RequestBody DictionaryUpdateCommand cmd);

    /**
     * 新增
     *
     * @param cmd
     * @return
     */
    @PostMapping("/service/dictionary/save")
    Result<Boolean> save(@RequestBody DictionaryAddCommand cmd);

    /**
     * 批量新增
     *
     * @param cmds
     * @return
     */
    @PostMapping("/service/dictionary/saveBatch")
    Result<Boolean> saveBatch(@RequestBody List<DictionaryAddCommand> cmds);

    /**
     * 删除
     *
     * @param ids
     * @return
     */
    @DeleteMapping("/service/dictionary/deleteByIds")
    Result<Boolean> deleteByIds(@RequestBody List<Long> ids);

    /**
     * 根据字典编码获取字典值
     *
     * @param query
     * @return
     */
    @GetMapping("/service/dictionary/value")
    Result getValue(@SpringQueryMap DictionaryQuery query);
}
