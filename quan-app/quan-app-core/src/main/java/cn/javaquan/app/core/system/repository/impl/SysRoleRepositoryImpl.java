package cn.javaquan.app.core.system.repository.impl;

import cn.javaquan.app.core.convert.PageAssembler;
import cn.javaquan.app.core.convert.PageResultAssembler;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import cn.javaquan.app.common.module.system.SysRolePermissionEvent;
import cn.javaquan.app.common.module.system.SysRoleQuery;
import cn.javaquan.app.common.util.RunUtil;
import cn.javaquan.app.common.util.Validate;
import cn.javaquan.common.base.constant.CommonConstant;
import cn.javaquan.common.base.message.BasePage;
import cn.javaquan.common.base.message.PageResult;
import cn.javaquan.app.core.system.convert.SysRoleAssembler;
import cn.javaquan.app.core.system.entity.SysRolePO;
import cn.javaquan.app.core.system.mapper.SysRoleMapper;
import cn.javaquan.app.core.system.repository.SysRolePermissionRepository;
import cn.javaquan.app.core.system.repository.SysRoleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 角色配置
 *
 * @author wangquan
 * @since 2020-12-27 17:50:38
 */
@RequiredArgsConstructor
@Repository
public class SysRoleRepositoryImpl extends ServiceImpl<SysRoleMapper, SysRolePO> implements SysRoleRepository {

    private final SysRolePermissionRepository sysRolePermissionRepository;

    @Override
    public PageResult<SysRolePO> page(SysRolePO po, BasePage basePage) {
        Page<SysRolePO> page = PageAssembler.INSTANCE.toPage(basePage);
        String name = po.getName();
        po.setName(null);
        LambdaQueryWrapper<SysRolePO> queryWrapper = Wrappers.lambdaQuery(po);
        queryWrapper.like(Validate.isNotBlank(name), SysRolePO::getName, name);
        page = this.page(page, queryWrapper);
        return PageResultAssembler.INSTANCE.toPageResult(page);
    }

    @Override
    public SysRolePO getRole(SysRoleQuery query) {
        LambdaQueryWrapper<SysRolePO> queryWrapper = Wrappers.lambdaQuery();
        queryWrapper.eq(Validate.isNotBlank(query.getCode()), SysRolePO::getCode, query.getCode());
        queryWrapper.eq(Validate.isNotBlank(query.getAppType()), SysRolePO::getAppType, query.getAppType());
        queryWrapper.eq(Validate.isNotNull(query.getStatus()), SysRolePO::getStatus, query.getStatus());
        queryWrapper.eq(SysRolePO::getDelFlag, CommonConstant.FALSE);
        return this.getOne(queryWrapper);
    }

    @Override
    public List<SysRolePO> getRoles(SysRoleQuery query) {
        SysRolePO sysRolePO = SysRoleAssembler.INSTANCE.toQueryPO(query);
        LambdaQueryWrapper<SysRolePO> queryWrapper = Wrappers.lambdaQuery(sysRolePO);
        queryWrapper.in(Validate.isNotEmpty(query.getRoleIds()), SysRolePO::getId, query.getRoleIds());
        return this.list(queryWrapper);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public boolean deleteByIds(List<Long> ids) {
        return RunUtil.doRun(this.removeByIds(ids), () -> {
            SysRolePermissionEvent sysRolePermissionEvent = new SysRolePermissionEvent();
            sysRolePermissionEvent.setRoleIds(ids);
            return sysRolePermissionRepository.delRolePermission(sysRolePermissionEvent);
        });
    }
}
