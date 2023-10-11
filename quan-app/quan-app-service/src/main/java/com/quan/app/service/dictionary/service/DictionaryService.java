package com.quan.app.service.dictionary.service;

import com.quan.app.common.constant.ErrorCodeEnum;
import com.quan.app.common.module.dictionary.DictionaryAddCommand;
import com.quan.app.common.module.dictionary.DictionaryDTO;
import com.quan.app.common.module.dictionary.DictionaryQuery;
import com.quan.app.common.module.dictionary.DictionaryUpdateCommand;
import com.quan.app.common.util.Validate;
import com.quan.common.base.message.PageResult;
import com.quan.common.base.message.Result;
import com.quan.app.service.dictionary.feign.DictionaryRepositoryFeign;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * 字典
 *
 * @author JavaQuan
 * @version 1.0.0
 * @date 2023-04-14 16:43:32
 */
@RequiredArgsConstructor
@Component
public class DictionaryService {

    private final DictionaryRepositoryFeign dictionaryRepositoryFeign;

    /**
     * 查询列表
     *
     * @param query
     * @return
     */
    public Result<PageResult<DictionaryDTO>> page(DictionaryQuery query) {
        return dictionaryRepositoryFeign.page(query);
    }

    /**
     * 根据ID查询
     *
     * @param id
     * @return
     */
    public Result<DictionaryDTO> details(Long id) {
        return dictionaryRepositoryFeign.details(id);
    }

    /**
     * 根据主键更新
     *
     * @param cmd
     * @return
     */
    public Result<Boolean> update(DictionaryUpdateCommand cmd) {
        DictionaryQuery query = new DictionaryQuery();
        query.setCode(cmd.getCode());
        Result<DictionaryDTO> result = dictionaryRepositoryFeign.getDictionary(query);
        Validate.isFalse(result.isData() && !result.getData().getId().equals(cmd.getId()), ErrorCodeEnum.PM_DICTIONARY_CODE_EXISTED);
        return dictionaryRepositoryFeign.update(cmd);
    }

    /**
     * 新增
     *
     * @param cmd
     * @return
     */
    public Result<Boolean> save(DictionaryAddCommand cmd) {
        DictionaryQuery query = new DictionaryQuery();
        query.setCode(cmd.getCode());
        Result<DictionaryDTO> result = dictionaryRepositoryFeign.getDictionary(query);
        Validate.isFalse(result.isData(), ErrorCodeEnum.PM_DICTIONARY_CODE_EXISTED);
        return dictionaryRepositoryFeign.save(cmd);
    }

    /**
     * 批量新增
     *
     * @param cmds
     * @return
     */
    public Result<Boolean> saveBatch(List<DictionaryAddCommand> cmds) {
        return dictionaryRepositoryFeign.saveBatch(cmds);
    }

    /**
     * 删除
     *
     * @param ids
     * @return
     */
    public Result<Boolean> deleteByIds(List<Long> ids) {
        return dictionaryRepositoryFeign.deleteByIds(ids);
    }

    /**
     * 根据字典编码获取字典值
     *
     * @param query
     * @return
     */
    public Result getValue(DictionaryQuery query) {
        return dictionaryRepositoryFeign.getValue(query);
    }

    /**
     * 根据字典编码获取字典值
     *
     * @param query
     * @return
     */
    public Result<DictionaryDTO> getDictionary(DictionaryQuery query) {
        return dictionaryRepositoryFeign.getDictionary(query);
    }

    /**
     * 根据字典编码获取字典值
     *
     * @param query
     * @return
     */
    public Result<List<DictionaryDTO>> getDictionaries(DictionaryQuery query) {
        return dictionaryRepositoryFeign.getDictionaries(query);
    }
}
