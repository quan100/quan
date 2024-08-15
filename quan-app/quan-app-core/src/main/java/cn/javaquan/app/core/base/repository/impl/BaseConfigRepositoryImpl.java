package cn.javaquan.app.core.base.repository.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import cn.javaquan.common.base.message.BasePage;
import cn.javaquan.common.base.message.PageResult;
import cn.javaquan.app.core.base.entity.BaseConfigPO;
import cn.javaquan.app.core.base.mapper.BaseConfigMapper;
import cn.javaquan.app.core.base.repository.BaseConfigRepository;
import cn.javaquan.app.core.convert.PageAssembler;
import cn.javaquan.app.core.convert.PageResultAssembler;
import org.springframework.stereotype.Repository;

/**
 * 系统通用配置.
 *
 * @author javaquan
 * @since 1.0.0
 */
@Repository
public class BaseConfigRepositoryImpl extends ServiceImpl<BaseConfigMapper, BaseConfigPO>
        implements BaseConfigRepository {

    @Override
    public PageResult<BaseConfigPO> page(BaseConfigPO po, BasePage basePage) {
        Page<BaseConfigPO> page = PageAssembler.INSTANCE.toPage(basePage);
        LambdaQueryWrapper<BaseConfigPO> queryWrapper = Wrappers.lambdaQuery(po);
        page = this.page(page, queryWrapper);
        return PageResultAssembler.INSTANCE.toPageResult(page);
    }

}
