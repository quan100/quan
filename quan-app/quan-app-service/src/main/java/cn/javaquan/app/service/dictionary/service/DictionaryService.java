package cn.javaquan.app.service.dictionary.service;

import cn.javaquan.app.service.dictionary.feign.DictionaryRepositoryFeign;
import cn.javaquan.app.common.constant.ErrorCodeEnum;
import cn.javaquan.app.common.module.dictionary.DictionaryAddCommand;
import cn.javaquan.app.common.module.dictionary.DictionaryDTO;
import cn.javaquan.app.common.module.dictionary.DictionaryQuery;
import cn.javaquan.app.common.module.dictionary.DictionaryUpdateCommand;
import cn.javaquan.app.common.util.Validate;
import cn.javaquan.common.base.message.PageResult;
import cn.javaquan.common.base.message.Result;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * 字典.
 *
 * @author javaquan
 * @since 1.0.0
 */
@RequiredArgsConstructor
@Component
public class DictionaryService {

    private final DictionaryRepositoryFeign dictionaryRepositoryFeign;

    /**
     * 查询列表.
     * @param query 查询参数
     * @return 查询结果
     */
    public Result<PageResult<DictionaryDTO>> page(DictionaryQuery query) {
        return dictionaryRepositoryFeign.page(query);
    }

    /**
     * 根据ID查询.
     * @param id 主键
     * @return 查询结果
     */
    public Result<DictionaryDTO> details(Long id) {
        return dictionaryRepositoryFeign.details(id);
    }

    /**
     * 根据主键更新.
     * @param cmd 更新指令参数
     * @return 操作是否成功
     */
    public Result<Boolean> update(DictionaryUpdateCommand cmd) {
        DictionaryQuery query = new DictionaryQuery();
        query.setCode(cmd.getCode());
        Result<DictionaryDTO> result = dictionaryRepositoryFeign.getDictionary(query);
        Validate.isFalse(result.isData() && !result.getData().getId().equals(cmd.getId()),
                ErrorCodeEnum.PM_DICTIONARY_CODE_EXISTED);
        return dictionaryRepositoryFeign.update(cmd);
    }

    /**
     * 新增.
     * @param cmd 新增指令参数
     * @return 操作是否成功
     */
    public Result<Boolean> save(DictionaryAddCommand cmd) {
        DictionaryQuery query = new DictionaryQuery();
        query.setCode(cmd.getCode());
        Result<DictionaryDTO> result = dictionaryRepositoryFeign.getDictionary(query);
        Validate.isFalse(result.isData(), ErrorCodeEnum.PM_DICTIONARY_CODE_EXISTED);
        return dictionaryRepositoryFeign.save(cmd);
    }

    /**
     * 批量新增.
     * @param cmds 新增参数
     * @return 新增结果
     */
    public Result<Boolean> saveBatch(List<DictionaryAddCommand> cmds) {
        return dictionaryRepositoryFeign.saveBatch(cmds);
    }

    /**
     * 删除.
     * @param ids 主键
     * @return 操作是否成功
     */
    public Result<Boolean> deleteByIds(List<Long> ids) {
        return dictionaryRepositoryFeign.deleteByIds(ids);
    }

    /**
     * 根据字典编码获取字典值.
     * @param query 查询参数
     * @return 字典值
     */
    public Result getValue(DictionaryQuery query) {
        return dictionaryRepositoryFeign.getValue(query);
    }

    /**
     * 根据字典编码获取字典值.
     * @param query 查询参数
     * @return 字典值
     */
    public Result<DictionaryDTO> getDictionary(DictionaryQuery query) {
        return dictionaryRepositoryFeign.getDictionary(query);
    }

    /**
     * 根据字典编码获取字典值.
     * @param query 查询参数
     * @return 字典值
     */
    public Result<List<DictionaryDTO>> getDictionaries(DictionaryQuery query) {
        return dictionaryRepositoryFeign.getDictionaries(query);
    }

}
