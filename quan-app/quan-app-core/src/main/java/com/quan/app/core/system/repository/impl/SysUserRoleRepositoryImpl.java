package com.quan.app.core.system.repository.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.quan.app.common.util.RunUtil;
import com.quan.app.common.util.Validate;
import com.quan.common.base.message.BasePage;
import com.quan.common.base.message.PageResult;
import com.quan.app.core.convert.PageAssembler;
import com.quan.app.core.convert.PageResultAssembler;
import com.quan.app.core.system.convert.SysUserRoleAssembler;
import com.quan.app.core.system.entity.SysUserRolePO;
import com.quan.app.core.system.mapper.SysUserRoleMapper;
import com.quan.app.core.system.repository.SysUserRoleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

/**
 * 用户角色配置
 *
 * @author wangquan
 * @since 2020-12-27 17:50:38
 */
@RequiredArgsConstructor
@Repository
public class SysUserRoleRepositoryImpl extends ServiceImpl<SysUserRoleMapper, SysUserRolePO> implements SysUserRoleRepository {

    @Override
    public PageResult<SysUserRolePO> page(SysUserRolePO po, BasePage basePage) {
        Page<SysUserRolePO> page = PageAssembler.INSTANCE.toPage(basePage);
        LambdaQueryWrapper<SysUserRolePO> queryWrapper = Wrappers.lambdaQuery(po);
        page = this.page(page, queryWrapper);
        return PageResultAssembler.INSTANCE.toPageResult(page);
    }

    @Override
    public List<SysUserRolePO> getUserRole(String userId) {
        LambdaQueryWrapper<SysUserRolePO> queryWrapper = Wrappers.lambdaQuery();
        queryWrapper.eq(SysUserRolePO::getUserId, userId);
        return this.list(queryWrapper);
    }

    @Override
    public int getCount(List<Long> roleId) {
        LambdaQueryWrapper<SysUserRolePO> queryWrapper = Wrappers.lambdaQuery();
        queryWrapper.in(SysUserRolePO::getRoleId, roleId);
        return this.count(queryWrapper);
    }

    @Override
    public boolean delByRoleId(List<Long> roleId) {
        LambdaQueryWrapper<SysUserRolePO> queryWrapper = Wrappers.lambdaQuery();
        queryWrapper.in(SysUserRolePO::getRoleId, roleId);
        return this.remove(queryWrapper);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void saveUserRole(List<Long> roleIds, String userId) {
        if (Validate.isEmpty(roleIds)) {
            return;
        }

        List<SysUserRolePO> sysUserRolePOS = this.getUserRole(userId);

        // 需要新增的数据
        List<SysUserRolePO> insertData = roleIds.stream().filter(roleId -> (sysUserRolePOS.parallelStream().noneMatch(sysUserRolePO -> sysUserRolePO.getRoleId().equals(roleId))))
                .map(roleId -> {
                    return SysUserRoleAssembler.INSTANCE.toAddPO(roleId, userId);
                })
                .collect(Collectors.toList());
        // 需要删除的数据
        List<Long> removeData = sysUserRolePOS.stream().filter(sysUserRolePO -> (roleIds.parallelStream().noneMatch(roleId -> sysUserRolePO.getRoleId().equals(roleId))))
                .map(SysUserRolePO::getId)
                .collect(Collectors.toList());

        RunUtil.doRun(Validate.isNotEmpty(insertData) ? this.saveOrUpdateBatch(insertData) : true, () -> {
            return Validate.isNotEmpty(removeData) ? this.removeByIds(removeData) : true;
        });
    }
}
