package com.quan.app.core.base.repository.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.quan.common.base.message.BasePage;
import com.quan.common.base.message.PageResult;
import com.quan.app.core.base.entity.BaseConfigPO;
import com.quan.app.core.base.mapper.BaseConfigMapper;
import com.quan.app.core.base.repository.BaseConfigRepository;
import com.quan.app.core.convert.PageAssembler;
import com.quan.app.core.convert.PageResultAssembler;
import org.springframework.stereotype.Repository;

/**
 * 系统通用配置
 *
 * @author JavaQuan
 * @version 1.0.0
 * @date 2023-04-04 10:38:39
 */
@Repository
public class BaseConfigRepositoryImpl extends ServiceImpl<BaseConfigMapper, BaseConfigPO> implements BaseConfigRepository {

    @Override
    public PageResult<BaseConfigPO> page(BaseConfigPO po, BasePage basePage) {
        Page<BaseConfigPO> page = PageAssembler.INSTANCE.toPage(basePage);
        LambdaQueryWrapper<BaseConfigPO> queryWrapper = Wrappers.lambdaQuery(po);
        page = this.page(page, queryWrapper);
        return PageResultAssembler.INSTANCE.toPageResult(page);
    }

}
