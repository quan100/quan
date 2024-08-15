package cn.javaquan.app.core.dictionary.controller;

import cn.javaquan.app.core.dictionary.convert.DictionaryAssembler;
import cn.javaquan.app.core.dictionary.repository.DictionaryRepository;
import cn.javaquan.app.common.module.dictionary.DictionaryAddCommand;
import cn.javaquan.app.common.module.dictionary.DictionaryQuery;
import cn.javaquan.app.common.module.dictionary.DictionaryUpdateCommand;
import cn.javaquan.common.base.message.PageResult;
import cn.javaquan.common.base.message.Result;
import cn.javaquan.app.core.dictionary.entity.DictionaryPO;
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
 * 字典.
 *
 * @author javaquan
 * @since 1.0.0
 */
@RequiredArgsConstructor
@RestController
@RequestMapping("/core/dictionary/")
public class DictionaryController {

    private final DictionaryRepository dictionaryRepository;

    /**
     * 查询列表.
     * @param query 查询参数
     * @return 查询结果
     */
    @GetMapping("page")
    public Result<PageResult<DictionaryPO>> page(DictionaryQuery query) {
        DictionaryPO po = DictionaryAssembler.INSTANCE.toQueryPO(query);
        return Result.success(dictionaryRepository.page(po, query));
    }

    /**
     * 根据ID查询.
     * @param id 主键
     * @return 查询结果
     */
    @GetMapping("details")
    public Result<DictionaryPO> details(@RequestParam Long id) {
        return Result.success(dictionaryRepository.getById(id));
    }

    /**
     * 根据主键更新.
     * @param cmd 更新指令参数
     * @return 更新操作是否成功
     */
    @PutMapping("update")
    public Result<Boolean> update(@RequestBody DictionaryUpdateCommand cmd) {
        DictionaryPO po = DictionaryAssembler.INSTANCE.toUpdatePO(cmd);
        return Result.success(dictionaryRepository.updateById(po));
    }

    /**
     * 新增.
     * @param cmd 新增指令参数
     * @return 新增操作是否成功
     */
    @PostMapping("save")
    public Result<Boolean> save(@RequestBody DictionaryAddCommand cmd) {
        DictionaryPO po = DictionaryAssembler.INSTANCE.toAddPO(cmd);
        return Result.success(dictionaryRepository.save(po));
    }

    /**
     * 批量新增.
     * @param cmds 新增参数
     * @return 新增操作是否成功
     */
    @PostMapping("saveBatch")
    public Result<Boolean> saveBatch(@RequestBody List<DictionaryAddCommand> cmds) {
        List<DictionaryPO> pos = DictionaryAssembler.INSTANCE.toAddPOS(cmds);
        return Result.success(dictionaryRepository.saveBatch(pos));
    }

    /**
     * 删除.
     * @param ids 主键
     * @return 操作是否成功
     */
    @DeleteMapping("deleteByIds")
    public Result<Boolean> deleteByIds(@RequestBody List<Long> ids) {
        return Result.success(dictionaryRepository.removeByIds(ids));
    }

    /**
     * 根据字典编码获取字典值.
     * @param query 查询参数
     * @return 查询参数
     */
    @GetMapping("value")
    public Result getValue(DictionaryQuery query) {
        return Result.success(dictionaryRepository.getValue(query));
    }

    /**
     * 根据字典编码获取字典.
     * @param query 查询参数
     * @return 查询参数
     */
    @GetMapping("dictionary")
    public Result<DictionaryPO> getDictionary(DictionaryQuery query) {
        return Result.success(dictionaryRepository.getDictionary(query));
    }

    /**
     * 根据字典编码获取字典.
     * @param query 查询参数
     * @return 查询参数
     */
    @GetMapping("dictionaries")
    public Result<List<DictionaryPO>> getDictionaries(DictionaryQuery query) {
        return Result.success(dictionaryRepository.getDictionaries(query));
    }

}
