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
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

/**
 * 字典.
 *
 * @author javaquan
 * @since 1.0.0
 */
@FeignClient(value = "${quan.app.feign.core.name}", url = "${quan.app.feign.core.url:}",
        fallbackFactory = DictionaryRepositoryFallback.class)
public interface DictionaryRepositoryFeign {

    /**
     * 查询列表.
     * @param query 查询参数
     * @return 查询结果
     */
    @GetMapping("/core/dictionary/page")
    Result<PageResult<DictionaryDTO>> page(@SpringQueryMap DictionaryQuery query);

    /**
     * 根据ID查询.
     * @param id 主键
     * @return 查询结果
     */
    @GetMapping("/core/dictionary/details")
    Result<DictionaryDTO> details(@RequestParam Long id);

    /**
     * 根据主键更新.
     * @param cmd 更新指令参数
     * @return 操作是否成功
     */
    @PutMapping("/core/dictionary/update")
    Result<Boolean> update(@RequestBody DictionaryUpdateCommand cmd);

    /**
     * 新增.
     * @param cmd 新增指令参数
     * @return 操作是否成功
     */
    @PostMapping("/core/dictionary/save")
    Result<Boolean> save(@RequestBody DictionaryAddCommand cmd);

    /**
     * 批量新增.
     * @param cmds 新增参数
     * @return 新增结果
     */
    @PostMapping("/core/dictionary/saveBatch")
    Result saveBatch(@RequestBody List<DictionaryAddCommand> cmds);

    /**
     * 删除.
     * @param ids 主键
     * @return 操作是否成功
     */
    @DeleteMapping("/core/dictionary/deleteByIds")
    Result<Boolean> deleteByIds(@RequestBody List<Long> ids);

    /**
     * 根据字典编码获取字典值.
     * @param query 查询参数
     * @return 字典值
     */
    @GetMapping("/core/dictionary/value")
    Result<Object> getValue(@SpringQueryMap DictionaryQuery query);

    /**
     * 根据字典编码获取字典.
     * @param query 查询参数
     * @return 字典值
     */
    @GetMapping("/core/dictionary/dictionary")
    Result<DictionaryDTO> getDictionary(@SpringQueryMap DictionaryQuery query);

    /**
     * 根据字典编码获取字典.
     * @param query 查询参数
     * @return 字典值
     */
    @GetMapping("/core/dictionary/dictionaries")
    Result<List<DictionaryDTO>> getDictionaries(@SpringQueryMap DictionaryQuery query);

}
