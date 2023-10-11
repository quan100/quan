package com.quan.app.pm.bff.dictionary.controller;

import com.quan.app.common.module.dictionary.DictionaryAddCommand;
import com.quan.app.common.module.dictionary.DictionaryDTO;
import com.quan.app.common.module.dictionary.DictionaryQuery;
import com.quan.app.common.module.dictionary.DictionaryUpdateCommand;
import com.quan.common.base.message.PageResult;
import com.quan.common.base.message.Result;
import com.quan.app.pm.bff.dictionary.feign.DictionaryServiceFeign;
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
@RequestMapping("/dictionary/")
public class DictionaryController {

    private final DictionaryServiceFeign dictionaryServiceFeign;

    /**
     * 查询列表
     *
     * @param query
     * @return
     */
    @GetMapping("page")
    public Result<PageResult<DictionaryDTO>> page(DictionaryQuery query) {
        return dictionaryServiceFeign.page(query);
    }

    /**
     * 根据ID查询
     *
     * @param id
     * @return
     */
    @GetMapping("details")
    public Result<DictionaryDTO> details(@RequestParam Long id) {
        return dictionaryServiceFeign.details(id);
    }

    /**
     * 根据主键更新
     *
     * @param cmd
     * @return
     */
    @PutMapping("update")
    public Result<Boolean> update(@RequestBody DictionaryUpdateCommand cmd) {
        return dictionaryServiceFeign.update(cmd);
    }

    /**
     * 新增
     *
     * @param cmd
     * @return
     */
    @PostMapping("save")
    public Result<Boolean> save(@RequestBody DictionaryAddCommand cmd) {
        return dictionaryServiceFeign.save(cmd);
    }

    /**
     * 新增
     *
     * @param cmds
     * @return
     */
    @PostMapping("saveBatch")
    public Result<Boolean> saveBatch(@RequestBody List<DictionaryAddCommand> cmds) {
        return dictionaryServiceFeign.saveBatch(cmds);
    }

    /**
     * 删除
     *
     * @param ids
     * @return
     */
    @DeleteMapping("deleteByIds")
    public Result<Boolean> deleteByIds(@RequestBody List<Long> ids) {
        return dictionaryServiceFeign.deleteByIds(ids);
    }

    /**
     * 根据字典编码获取字典值
     *
     * @param code
     * @return
     */
    @GetMapping("value")
    public Result getValue(@RequestParam String code) {
        DictionaryQuery query = new DictionaryQuery();
        query.setCode(code);
        return dictionaryServiceFeign.getValue(query);
    }

}
