package cn.javaquan.app.core.system.repository.impl;

import cn.javaquan.app.core.convert.PageAssembler;
import cn.javaquan.app.core.convert.PageResultAssembler;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import cn.javaquan.app.common.module.system.SubsetPermissionsQuery;
import cn.javaquan.app.common.module.system.SysPermissionTreeDTO;
import cn.javaquan.app.common.module.system.SysRolePermissionQuery;
import cn.javaquan.app.common.util.Validate;
import cn.javaquan.common.base.constant.CommonConstant;
import cn.javaquan.common.base.message.BasePage;
import cn.javaquan.common.base.message.PageResult;
import cn.javaquan.app.core.system.convert.SysPermissionAssembler;
import cn.javaquan.app.core.system.entity.SysPermissionPO;
import cn.javaquan.app.core.system.mapper.SysPermissionMapper;
import cn.javaquan.app.core.system.repository.SysPermissionRepository;
import cn.javaquan.app.core.system.repository.SysRolePermissionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.Collections;
import java.util.List;

/**
 * 系统资源权限配置
 *
 * @author wangquan
 * @since 2020-12-27 17:50:38
 */
@RequiredArgsConstructor
@Repository
public class SysPermissionRepositoryImpl extends ServiceImpl<SysPermissionMapper, SysPermissionPO> implements SysPermissionRepository {

    private final SysRolePermissionRepository sysRolePermissionRepository;

    @Override
    public PageResult<SysPermissionPO> page(SysPermissionPO po, BasePage basePage) {
        Page<SysPermissionPO> page = PageAssembler.INSTANCE.toPage(basePage);
        LambdaQueryWrapper<SysPermissionPO> queryWrapper = Wrappers.lambdaQuery(po);
        page = this.page(page, queryWrapper);
        return PageResultAssembler.INSTANCE.toPageResult(page);
    }

    /**
     * 获取角色有效权限列表
     *
     * @param query type类型，0：一级菜单，1：菜单，2：按钮；默认查询全部
     * @return
     */
    @Override
    public List<SysPermissionPO> getRolePermission(SysRolePermissionQuery query) {
        List<Long> permissionIds = sysRolePermissionRepository.getRolePermissionIds(query);

        if (Validate.isEmpty(permissionIds)) {
            return Collections.emptyList();
        }

        LambdaQueryWrapper<SysPermissionPO> queryWrapper = Wrappers.lambdaQuery();
        queryWrapper.in(SysPermissionPO::getId, permissionIds);
        queryWrapper.eq(null != query.getType(), SysPermissionPO::getType, query.getType());
        queryWrapper.eq(SysPermissionPO::getDelFlag, CommonConstant.FALSE);
        queryWrapper.orderByAsc(SysPermissionPO::getSort);
        List<SysPermissionPO> sysPermissionPos = this.list(queryWrapper);
        return sysPermissionPos;
    }

    /**
     * 子权限查询
     *
     * @param query
     * @return
     */
    @Override
    public PageResult<SysPermissionPO> getSubsetPermissions(SubsetPermissionsQuery query) {
        Page<SysPermissionPO> page = PageAssembler.INSTANCE.toPage(query);
        LambdaQueryWrapper<SysPermissionPO> queryWrapper = Wrappers.lambdaQuery();
        queryWrapper.eq(Validate.isNotNull(query.getParentId()), SysPermissionPO::getParentId, query.getParentId());
        queryWrapper.eq(Validate.isNotBlank(query.getAppType()), SysPermissionPO::getAppType, query.getAppType());
        queryWrapper.in(Validate.isNotEmpty(query.getType()), SysPermissionPO::getType, query.getType());
        queryWrapper.like(Validate.isNotBlank(query.getName()), SysPermissionPO::getName, query.getName());
        queryWrapper.orderByAsc(SysPermissionPO::getSort);
        page = this.page(page, queryWrapper);
        return PageResultAssembler.INSTANCE.toPageResult(page);
    }

    /**
     * 根据上级权限ID查询
     *
     * @param parentId
     * @return
     */
    public List<SysPermissionTreeDTO> getSubsetPermissions(Long parentId, List<Integer> type) {
        LambdaQueryWrapper<SysPermissionPO> queryWrapper = Wrappers.lambdaQuery();
        queryWrapper.eq(SysPermissionPO::getParentId, parentId);
        queryWrapper.in(Validate.isNotEmpty(type), SysPermissionPO::getType, type);
        queryWrapper.orderByAsc(SysPermissionPO::getSort);
        List<SysPermissionPO> sysPermissionPOS = this.list(queryWrapper);
        return SysPermissionAssembler.INSTANCE.toSysPermissionTreeDtoList(sysPermissionPOS);
    }

    @Override
    public PageResult<SysPermissionTreeDTO> getTreePermissions(SubsetPermissionsQuery query) {
        PageResult<SysPermissionPO> treePermissionsPage = getSubsetPermissions(query);
        List<SysPermissionPO> treePermissionsPO = treePermissionsPage.getRecords();
        List<SysPermissionTreeDTO> treePermissionsDTO = SysPermissionAssembler.INSTANCE.toSysPermissionTreeDtoList(treePermissionsPO);
        if (Validate.isNotEmpty(treePermissionsDTO)) {
            treePermissionsDTO.stream().forEach(permissionDTO -> {
                permissionDTO.setChildren(getTreePermissions(permissionDTO.getId(), query.getType()));
            });
        }
        PageResult<SysPermissionTreeDTO> pageResult = PageResultAssembler.INSTANCE.toPageResult(treePermissionsPage);
        pageResult.setRecords(treePermissionsDTO);
        return pageResult;
    }

    /**
     * 获取树形权限
     *
     * @param parentId 上级ID
     * @return
     */
    public List<SysPermissionTreeDTO> getTreePermissions(Long parentId, List<Integer> type) {
        List<SysPermissionTreeDTO> treePermissionsDTOList = getSubsetPermissions(parentId, type);
        if (Validate.isNotEmpty(treePermissionsDTOList)) {
            for (SysPermissionTreeDTO permissionTreeDTO : treePermissionsDTOList) {
                permissionTreeDTO.setChildren(getTreePermissions(permissionTreeDTO.getId(), type));
            }
        }
        return treePermissionsDTOList;
    }

    @Override
    public List<SysPermissionPO> getPerms() {
        // 不匹配菜单权限（菜单权限由路由控制）
        LambdaQueryWrapper<SysPermissionPO> queryWrapper = Wrappers.lambdaQuery();
        queryWrapper.eq(SysPermissionPO::getType, 2);
        queryWrapper.eq(SysPermissionPO::getDelFlag, CommonConstant.FALSE);
        List<SysPermissionPO> sysPermissionPos = this.list(queryWrapper);
        if (Validate.isEmpty(sysPermissionPos)) {
            return Collections.emptyList();
        }
        return sysPermissionPos;
    }
}
