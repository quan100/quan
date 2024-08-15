package cn.javaquan.app.core.tools.repository.impl;

import cn.javaquan.app.core.convert.PageAssembler;
import cn.javaquan.app.core.convert.PageResultAssembler;
import cn.javaquan.app.core.tools.repository.ToolsRepository;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import cn.javaquan.app.common.module.tools.ToolsQuery;
import cn.javaquan.app.common.util.Validate;
import cn.javaquan.common.base.constant.CommonConstant;
import cn.javaquan.common.base.message.BasePage;
import cn.javaquan.common.base.message.PageResult;
import cn.javaquan.app.core.tools.entity.ToolsPO;
import cn.javaquan.app.core.tools.mapper.ToolsMapper;
import org.springframework.stereotype.Repository;

/**
 * 工具.
 *
 * @author javaquan
 * @since 1.0.0
 */
@Repository
public class ToolsRepositoryImpl extends ServiceImpl<ToolsMapper, ToolsPO> implements ToolsRepository {

    @Override
    public PageResult<ToolsPO> page(ToolsPO po, BasePage basePage) {
        Page<ToolsPO> page = PageAssembler.INSTANCE.toPage(basePage);
        LambdaQueryWrapper<ToolsPO> queryWrapper = Wrappers.lambdaQuery(po);
        queryWrapper.orderByAsc(ToolsPO::getSort);
        queryWrapper.orderByDesc(ToolsPO::getCreateTime, ToolsPO::getUpdateTime);
        page = this.page(page, queryWrapper);
        return PageResultAssembler.INSTANCE.toPageResult(page);
    }

    @Override
    public PageResult<ToolsPO> getTools(ToolsQuery query) {
        Page<ToolsPO> page = PageAssembler.INSTANCE.toPage(query);
        LambdaQueryWrapper<ToolsPO> queryWrapper = Wrappers.lambdaQuery();
        queryWrapper.eq(Validate.isNotNull(query.getDataType()), ToolsPO::getDataType, query.getDataType());
        queryWrapper.eq(ToolsPO::getListType, query.getListType());
        queryWrapper.eq(ToolsPO::getStatus, query.getStatus());
        queryWrapper.eq(ToolsPO::getDelFlag, CommonConstant.FALSE);
        queryWrapper.orderByAsc(ToolsPO::getSort);
        queryWrapper.orderByDesc(ToolsPO::getCreateTime, ToolsPO::getUpdateTime);
        return PageResultAssembler.INSTANCE.toPageResult(this.page(page, queryWrapper));
    }

}
