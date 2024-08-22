package cn.javaquan.app.common.module.system;

import cn.javaquan.app.common.util.tree.ITreeNode;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

/**
 * 系统资源权限配置，用户权限列表 { Route } 对象.
 *
 * @author wangquan
 * @since 1.0.0
 */
@Data
public class UserPermissionTreeDTO extends UserPermissionDTO implements ITreeNode<UserPermissionTreeDTO> {

    private static final long serialVersionUID = 680401617324510559L;

    /**
     * 子集权限菜单.
     */
    private List<UserPermissionTreeDTO> routes;

    @Override
    public List<UserPermissionTreeDTO> determineChildren() {
        if (null == this.routes) {
            this.routes = new ArrayList<>();
        }
        return this.routes;
    }

    @Override
    public int compareTo(UserPermissionTreeDTO node) {
        // 升序排序
        return this.getSort().compareTo(node.getSort());
    }

}
