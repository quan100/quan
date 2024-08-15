package cn.javaquan.app.core.system.repository.impl;

import cn.javaquan.app.core.convert.PageAssembler;
import cn.javaquan.app.core.convert.PageResultAssembler;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import cn.javaquan.app.common.module.system.PermissionRoleDTO;
import cn.javaquan.app.common.module.system.SysRolePermissionEvent;
import cn.javaquan.app.common.module.system.SysRolePermissionQuery;
import cn.javaquan.app.common.util.Validate;
import cn.javaquan.common.base.message.BasePage;
import cn.javaquan.common.base.message.PageResult;
import cn.javaquan.app.core.system.entity.SysRolePermissionPO;
import cn.javaquan.app.core.system.mapper.SysRolePermissionMapper;
import cn.javaquan.app.core.system.repository.SysRolePermissionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.util.CollectionUtils;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 角色权限配置.
 *
 * @author wangquan
 * @since 1.0.0
 */
@RequiredArgsConstructor
@Repository
public class SysRolePermissionRepositoryImpl extends ServiceImpl<SysRolePermissionMapper, SysRolePermissionPO>
        implements SysRolePermissionRepository {

    private final SysRolePermissionMapper sysRolePermissionMapper;

    @Override
    public PageResult<SysRolePermissionPO> page(SysRolePermissionPO po, BasePage basePage) {
        Page<SysRolePermissionPO> page = PageAssembler.INSTANCE.toPage(basePage);
        LambdaQueryWrapper<SysRolePermissionPO> queryWrapper = Wrappers.lambdaQuery(po);
        page = this.page(page, queryWrapper);
        return PageResultAssembler.INSTANCE.toPageResult(page);
    }

    @Override
    public List<Long> getRolePermissionIds(SysRolePermissionQuery query) {
        LambdaQueryWrapper<SysRolePermissionPO> queryWrapper = Wrappers.lambdaQuery();
        queryWrapper.select(SysRolePermissionPO::getPermissionId);
        queryWrapper.in(Validate.isNotEmpty(query.getRoleIds()), SysRolePermissionPO::getRoleId, query.getRoleIds());
        queryWrapper.eq(Validate.isNotNull(query.getRoleId()), SysRolePermissionPO::getRoleId, query.getRoleId());
        List<SysRolePermissionPO> sysRolePermissionPOS = this.list(queryWrapper);
        if (CollectionUtils.isEmpty(sysRolePermissionPOS)) {
            return Collections.emptyList();
        }
        return sysRolePermissionPOS.stream().map(SysRolePermissionPO::getPermissionId).collect(Collectors.toList());
    }

    @Override
    public int count(SysRolePermissionQuery query) {
        LambdaQueryWrapper<SysRolePermissionPO> queryWrapper = Wrappers.lambdaQuery();
        queryWrapper.in(Validate.isNotEmpty(query.getRoleIds()), SysRolePermissionPO::getRoleId, query.getRoleIds());
        queryWrapper.eq(Validate.isNotNull(query.getRoleId()), SysRolePermissionPO::getRoleId, query.getRoleId());
        queryWrapper.in(Validate.isNotEmpty(query.getPermissionIds()), SysRolePermissionPO::getPermissionId,
                query.getPermissionIds());
        return this.count(queryWrapper);
    }

    @Override
    public boolean delRolePermission(SysRolePermissionEvent event) {
        LambdaQueryWrapper<SysRolePermissionPO> queryWrapper = Wrappers.lambdaQuery();
        queryWrapper.in(Validate.isNotEmpty(event.getRoleIds()), SysRolePermissionPO::getRoleId, event.getRoleIds());
        queryWrapper.eq(Validate.isNotNull(event.getRoleId()), SysRolePermissionPO::getRoleId, event.getRoleId());
        queryWrapper.eq(Validate.isNotNull(event.getPermissionId()), SysRolePermissionPO::getPermissionId,
                event.getPermissionId());
        queryWrapper.in(Validate.isNotEmpty(event.getPermissionIds()), SysRolePermissionPO::getPermissionId,
                event.getPermissionIds());
        return this.remove(queryWrapper);
    }

    @Override
    public List<PermissionRoleDTO> getPermissionRoles() {
        return sysRolePermissionMapper.permissionRoleList();
    }

}
