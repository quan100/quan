package cn.javaquan.app.service.dictionary.feign;

import cn.javaquan.app.common.module.dictionary.DictionaryAddCommand;
import cn.javaquan.app.common.module.dictionary.DictionaryDTO;
import cn.javaquan.app.common.module.dictionary.DictionaryQuery;
import cn.javaquan.app.common.module.dictionary.DictionaryUpdateCommand;
import cn.javaquan.common.base.message.PageResult;
import cn.javaquan.common.base.message.Result;
import cn.javaquan.app.service.dictionary.feign.fallback.DictionaryRepositoryFallback;
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
@FeignClient(value = "${quan.app.feign.core.name}", url = "${quan.app.feign.core.url:}", fallbackFactory = DictionaryRepositoryFallback.class)
public interface DictionaryRepositoryFeign {

    /**
     * 查询列表
     *
     * @param query
     * @return
     */
    @GetMapping("/core/dictionary/page")
    Result<PageResult<DictionaryDTO>> page(@SpringQueryMap DictionaryQuery query);

    /**
     * 根据ID查询
     *
     * @param id
     * @return
     */
    @GetMapping("/core/dictionary/details")
    Result<DictionaryDTO> details(@RequestParam(value = "id") Long id);

    /**
     * 根据主键更新
     *
     * @param cmd
     * @return
     */
    @PutMapping("/core/dictionary/update")
    Result<Boolean> update(@RequestBody DictionaryUpdateCommand cmd);

    /**
     * 新增
     *
     * @param cmd
     * @return
     */
    @PostMapping("/core/dictionary/save")
    Result<Boolean> save(@RequestBody DictionaryAddCommand cmd);

    /**
     * 批量新增
     *
     * @param cmds
     * @return
     */
    @PostMapping("/core/dictionary/saveBatch")
    Result saveBatch(@RequestBody List<DictionaryAddCommand> cmds);

    /**
     * 删除
     *
     * @param ids
     * @return
     */
    @DeleteMapping("/core/dictionary/deleteByIds")
    Result<Boolean> deleteByIds(@RequestBody List<Long> ids);

    /**
     * 根据字典编码获取字典值
     *
     * @param query
     * @return
     */
    @GetMapping("/core/dictionary/value")
    Result<Object> getValue(@SpringQueryMap DictionaryQuery query);

    /**
     * 根据字典编码获取字典
     *
     * @param query
     * @return
     */
    @GetMapping("/core/dictionary/dictionary")
    Result<DictionaryDTO> getDictionary(@SpringQueryMap DictionaryQuery query);

    /**
     * 根据字典编码获取字典
     *
     * @param query
     * @return
     */
    @GetMapping("/core/dictionary/dictionaries")
    Result<List<DictionaryDTO>> getDictionaries(@SpringQueryMap DictionaryQuery query);

}
