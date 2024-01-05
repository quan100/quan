package cn.javaquan.app.core.dictionary.repository.impl;

import cn.javaquan.app.core.convert.PageAssembler;
import cn.javaquan.app.core.convert.PageResultAssembler;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import cn.javaquan.app.common.module.dictionary.DictionaryQuery;
import cn.javaquan.app.common.util.Validate;
import cn.javaquan.common.base.constant.CommonConstant;
import cn.javaquan.common.base.message.BasePage;
import cn.javaquan.common.base.message.PageResult;
import cn.javaquan.app.core.dictionary.entity.DictionaryPO;
import cn.javaquan.app.core.dictionary.mapper.DictionaryMapper;
import cn.javaquan.app.core.dictionary.repository.DictionaryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 字典配置
 * </p>
 *
 * @author JavaQuan
 * @version 1.0.0
 * @date 2023-04-14 16:43:32
 */
@RequiredArgsConstructor
@Repository
public class DictionaryRepositoryImpl extends ServiceImpl<DictionaryMapper, DictionaryPO> implements DictionaryRepository {

    @Override
    public PageResult<DictionaryPO> page(DictionaryPO po, BasePage basePage) {
        Page<DictionaryPO> page = PageAssembler.INSTANCE.toPage(basePage);
        LambdaQueryWrapper<DictionaryPO> queryWrapper = Wrappers.lambdaQuery(po).eq(DictionaryPO::getDelFlag, CommonConstant.FALSE);
        queryWrapper.select(
                DictionaryPO::getId,
                DictionaryPO::getName,
                DictionaryPO::getOpen,
                DictionaryPO::getCode,
                DictionaryPO::getUpdateTime,
                DictionaryPO::getType
        );
        queryWrapper.orderByDesc(DictionaryPO::getUpdateTime);
        page = this.page(page, queryWrapper);
        return PageResultAssembler.INSTANCE.toPageResult(page);
    }

    @Override
    public Object getValue(DictionaryQuery query) {
        LambdaQueryWrapper<DictionaryPO> queryWrapper = Wrappers.lambdaQuery();
        queryWrapper.select(DictionaryPO::getValue);
        queryWrapper.eq(DictionaryPO::getCode, query.getCode());
        queryWrapper.eq(Validate.isNotNull(query.getOpen()), DictionaryPO::getOpen, query.getOpen());
        queryWrapper.eq(DictionaryPO::getDelFlag, CommonConstant.FALSE);
        DictionaryPO dictionaryPo = this.getOne(queryWrapper);
        if (null != dictionaryPo) {
            return dictionaryPo.getValue();
        }
        return null;
    }

    @Override
    public DictionaryPO getDictionary(DictionaryQuery query) {
        LambdaQueryWrapper<DictionaryPO> queryWrapper = Wrappers.lambdaQuery();
        queryWrapper.eq(DictionaryPO::getCode, query.getCode());
        queryWrapper.eq(Validate.isNotNull(query.getOpen()), DictionaryPO::getOpen, query.getOpen());
        queryWrapper.eq(DictionaryPO::getDelFlag, CommonConstant.FALSE);
        return this.getOne(queryWrapper);
    }

    @Override
    public List<DictionaryPO> getDictionaries(DictionaryQuery query) {
        LambdaQueryWrapper<DictionaryPO> queryWrapper = Wrappers.lambdaQuery();
        queryWrapper.eq(DictionaryPO::getCode, query.getCode());
        queryWrapper.eq(DictionaryPO::getDelFlag, CommonConstant.FALSE);
        return this.list(queryWrapper);
    }
}
