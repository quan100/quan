package cn.javaquan.app.common.module.system;

import cn.javaquan.app.common.util.Validate;
import lombok.Data;

import java.io.Serializable;
import java.util.List;


/**
 * 树形权限查询数据
 *
 * @author wangquan
 */
@Data
public class SysPermissionTreeDTO implements Serializable {

    private static final long serialVersionUID = 6925253564195438576L;


    /**
     * 主键
     */
    private Long id;

    /**
     * 上级ID，顶级默认为0
     */
    private Long parentId;

    /**
     * 菜单的名字
     */
    private String name;

    /**
     * 配置路由跳转
     */
    private String redirect;

    /**
     * 配置路由组件的包装组件，通过包装组件可以为当前的路由组件组合进更多的功能。
     * 比如，可以用于路由级别的权限校验
     */
    private String wrappers;

    /**
     * 在菜单中隐藏子节点
     */
    private Boolean hideChildrenInMenu;

    /**
     * 在菜单中隐藏自己和子节点
     */
    private Boolean hideInMenu;

    /**
     * 隐藏自己，并且将子节点提升到与自己平级
     */
    private Boolean flatMenu;

    /**
     * 自定义菜单的国际化
     */
    private String locale;

    /**
     * disable 菜单选项
     */
    private Boolean disabled;

    /**
     * 配置路由的标题。
     */
    private String title;

    /**
     * 配置是否让生成的文件包含 hash 后缀，通常用于增量发布和避免浏览器加载缓存。
     */
    private Boolean hash;

    /**
     * 配置可以被 path-to-regexp@^1.7.0 理解的路径通配符。
     */
    private String path;

    /**
     * menuItem 的 tooltip 显示的路径
     */
    private String tooltip;

    /**
     * 用于标定选中的值，默认是 path
     */
    private String key;

    /**
     * 当此节点被选中的时候也会选中 parentKeys 的节点
     */
    private String parentKeys;

    /**
     * 配置 location 和 path 匹配后用于渲染的 React 组件路径。
     * 可以是绝对路径，也可以是相对路径，如果是相对路径，会从 src/pages 开始找起。
     */
    private String component;

    /**
     * 表示是否严格匹配，即 location 是否和 path 完全对应上。
     */
    private Boolean exact;

    /**
     * 菜单的icon
     */
    private String icon;

    /**
     * 指定外链打开形式，同a标签
     */
    private String target;

    /**
     * 应用编码，标识权限所属的应用
     */
    private String appType;

    /**
     * 与项目提供的权限拦截匹配的权限，如果不匹配，则会被禁止访问该路由页面
     */
    private String permission;

    /**
     * 类型，0：一级菜单，1：菜单，2：按钮
     */
    private Integer type;

    /**
     * 资源描述
     */
    private String description;

    /**
     * 排序
     */
    private Integer sort;

    /**
     * 子集权限菜单
     */
    private List<SysPermissionTreeDTO> children;

    /**
     * 若子节点为空。将参数置空，避免前端组件解析
     *
     * @param children
     */
    public void setChildren(List<SysPermissionTreeDTO> children) {
        if (Validate.isEmpty(children)) {
            this.children = null;
            return;
        }
        this.children = children;
    }
}