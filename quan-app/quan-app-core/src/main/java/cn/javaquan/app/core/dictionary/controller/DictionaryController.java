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
import org.springframework.web.bind.annotation.*;

import java.util.List;


/**
 * 字典
 *
 * @author JavaQuan
 * @version 1.0.0
 * @date 2023-04-14 16:43:32
 */
@RequiredArgsConstructor
@RestController
@RequestMapping("/core/dictionary/")
public class DictionaryController {

    private final DictionaryRepository dictionaryRepository;

    /**
     * 查询列表
     *
     * @param query
     * @return
     */
    @GetMapping("page")
    public Result<PageResult> page(DictionaryQuery query) {
        DictionaryPO po = DictionaryAssembler.INSTANCE.toQueryPO(query);
        return Result.success(dictionaryRepository.page(po, query));
    }

    /**
     * 根据ID查询
     *
     * @param id
     * @return
     */
    @GetMapping("details")
    public Result details(@RequestParam Long id) {
        return Result.success(dictionaryRepository.getById(id));
    }

    /**
     * 根据主键更新
     *
     * @param cmd
     * @return
     */
    @PutMapping("update")
    public Result update(@RequestBody DictionaryUpdateCommand cmd) {
        DictionaryPO po = DictionaryAssembler.INSTANCE.toUpdatePO(cmd);
        return Result.success(dictionaryRepository.updateById(po));
    }

    /**
     * 新增
     *
     * @param cmd
     * @return
     */
    @PostMapping("save")
    public Result save(@RequestBody DictionaryAddCommand cmd) {
        DictionaryPO po = DictionaryAssembler.INSTANCE.toAddPO(cmd);
        return Result.success(dictionaryRepository.save(po));
    }

    /**
     * 批量新增
     *
     * @param cmds
     * @return
     */
    @PostMapping("saveBatch")
    public Result saveBatch(@RequestBody List<DictionaryAddCommand> cmds) {
        List<DictionaryPO> pos = DictionaryAssembler.INSTANCE.toAddPOS(cmds);
        return Result.success(dictionaryRepository.saveBatch(pos));
    }

    /**
     * 删除
     *
     * @param ids
     * @return
     */
    @DeleteMapping("deleteByIds")
    public Result deleteByIds(@RequestBody List<Long> ids) {
        return Result.success(dictionaryRepository.removeByIds(ids));
    }

    /**
     * 根据字典编码获取字典值
     *
     * @param query
     * @return
     */
    @GetMapping("value")
    public Result getValue(DictionaryQuery query) {
        return Result.success(dictionaryRepository.getValue(query));
    }

    /**
     * 根据字典编码获取字典
     *
     * @param query
     * @return
     */
    @GetMapping("dictionary")
    public Result getDictionary(DictionaryQuery query) {
        return Result.success(dictionaryRepository.getDictionary(query));
    }

    /**
     * 根据字典编码获取字典
     *
     * @param query
     * @return
     */
    @GetMapping("dictionaries")
    public Result getDictionaries(DictionaryQuery query) {
        return Result.success(dictionaryRepository.getDictionaries(query));
    }

}
