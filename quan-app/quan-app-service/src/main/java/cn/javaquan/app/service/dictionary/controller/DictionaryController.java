package cn.javaquan.app.service.dictionary.controller;

import cn.javaquan.app.common.module.dictionary.DictionaryAddCommand;
import cn.javaquan.app.common.module.dictionary.DictionaryDTO;
import cn.javaquan.app.common.module.dictionary.DictionaryQuery;
import cn.javaquan.app.common.module.dictionary.DictionaryUpdateCommand;
import cn.javaquan.common.base.message.PageResult;
import cn.javaquan.common.base.message.Result;
import cn.javaquan.app.service.dictionary.service.DictionaryService;
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
@RequestMapping("/service/dictionary/")
public class DictionaryController {

    private final DictionaryService dictionaryService;

    /**
     * 查询列表
     *
     * @param query
     * @return
     */
    @GetMapping("page")
    public Result<PageResult<DictionaryDTO>> page(DictionaryQuery query) {
        return dictionaryService.page(query);
    }

    /**
     * 根据ID查询
     *
     * @param id
     * @return
     */
    @GetMapping("details")
    public Result<DictionaryDTO> details(@RequestParam Long id) {
        return dictionaryService.details(id);
    }

    /**
     * 根据主键更新
     *
     * @param cmd
     * @return
     */
    @PutMapping("update")
    public Result<Boolean> update(@RequestBody DictionaryUpdateCommand cmd) {
        return dictionaryService.update(cmd);
    }

    /**
     * 新增
     *
     * @param cmd
     * @return
     */
    @PostMapping("save")
    public Result<Boolean> save(@RequestBody DictionaryAddCommand cmd) {
        return dictionaryService.save(cmd);
    }

    /**
     * 批量新增
     *
     * @param cmds
     * @return
     */
    @PostMapping("saveBatch")
    public Result<Boolean> saveBatch(@RequestBody List<DictionaryAddCommand> cmds) {
        return dictionaryService.saveBatch(cmds);
    }

    /**
     * 删除
     *
     * @param ids
     * @return
     */
    @DeleteMapping("deleteByIds")
    public Result<Boolean> deleteByIds(@RequestBody List<Long> ids) {
        return dictionaryService.deleteByIds(ids);
    }

    /**
     * 根据字典编码获取字典值
     *
     * @param query
     * @return
     */
    @GetMapping("value")
    public Result<String> getValue(DictionaryQuery query) {
        return dictionaryService.getValue(query);
    }

    /**
     * 根据字典编码获取字典
     *
     * @param query
     * @return
     */
    @GetMapping("dictionary")
    public Result<DictionaryDTO> getDictionary(DictionaryQuery query) {
        return dictionaryService.getDictionary(query);
    }

    /**
     * 根据字典编码获取字典
     *
     * @param query
     * @return
     */
    @GetMapping("dictionaries")
    public Result<List<DictionaryDTO>> getDictionaries(DictionaryQuery query) {
        return dictionaryService.getDictionaries(query);
    }
}
