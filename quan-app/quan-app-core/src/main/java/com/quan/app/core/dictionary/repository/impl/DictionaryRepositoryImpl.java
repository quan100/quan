package com.quan.app.core.dictionary.repository.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.quan.app.common.module.dictionary.DictionaryQuery;
import com.quan.app.common.util.Validate;
import com.quan.common.base.message.BasePage;
import com.quan.common.base.message.PageResult;
import com.quan.app.core.convert.PageAssembler;
import com.quan.app.core.convert.PageResultAssembler;
import com.quan.app.core.dictionary.entity.DictionaryPO;
import com.quan.app.core.dictionary.mapper.DictionaryMapper;
import com.quan.app.core.dictionary.repository.DictionaryRepository;
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
        LambdaQueryWrapper<DictionaryPO> queryWrapper = Wrappers.lambdaQuery(po);
        queryWrapper.select(
                DictionaryPO::getId,
                DictionaryPO::getName,
                DictionaryPO::getOpen,
                DictionaryPO::getCode,
                DictionaryPO::getUpdateTime
        );
        queryWrapper.orderByDesc(DictionaryPO::getCreateTime, DictionaryPO::getUpdateTime);
        page = this.page(page, queryWrapper);
        return PageResultAssembler.INSTANCE.toPageResult(page);
    }

    @Override
    public Object getValue(DictionaryQuery query) {
        LambdaQueryWrapper<DictionaryPO> queryWrapper = Wrappers.lambdaQuery();
        queryWrapper.select(DictionaryPO::getValue);
        queryWrapper.eq(DictionaryPO::getCode, query.getCode());
        queryWrapper.eq(Validate.isNotNull(query.getOpen()), DictionaryPO::getOpen, query.getOpen());
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
        return this.getOne(queryWrapper);
    }

    @Override
    public List<DictionaryPO> getDictionaries(DictionaryQuery query) {
        LambdaQueryWrapper<DictionaryPO> queryWrapper = Wrappers.lambdaQuery();
        queryWrapper.eq(DictionaryPO::getCode, query.getCode());
        return this.list(queryWrapper);
    }
}
