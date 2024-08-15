package cn.javaquan.app.core.dictionary.repository;

import com.baomidou.mybatisplus.extension.service.IService;
import cn.javaquan.app.common.module.dictionary.DictionaryQuery;
import cn.javaquan.common.base.message.BasePage;
import cn.javaquan.common.base.message.PageResult;
import cn.javaquan.app.core.dictionary.entity.DictionaryPO;

import java.util.List;

/**
 * 字典配置.
 *
 * @author wangquan
 * @since 1.0.0
 */
public interface DictionaryRepository extends IService<DictionaryPO> {

    /**
     * 分页查询.
     * <p>
     * 当有排序参数 sort 时，优先根据sort 升序，然后根据创建时间降序
     * @param po 查询参数
     * @param basePage 分页参数
     * @return 查询结果
     */
    PageResult<DictionaryPO> page(DictionaryPO po, BasePage basePage);

    /**
     * 根据字典编码获取字典值.
     * @param query 查询参数
     * @return 查询参数
     */
    Object getValue(DictionaryQuery query);

    /**
     * 根据字典编码获取字典值.
     * @param query 查询参数
     * @return 查询参数
     */
    DictionaryPO getDictionary(DictionaryQuery query);

    /**
     * 根据字典编码获取字典值.
     * @param query 查询参数
     * @return 查询参数
     */
    List<DictionaryPO> getDictionaries(DictionaryQuery query);

}
