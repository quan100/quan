package com.quan.app.common.module.system;

import com.quan.common.base.message.BasePage;
import lombok.Data;

import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.List;

/**
 * 系统资源权限配置
 *
 * @author JavaQuan
 * @since 2023-04-04 10:53:59
 */
@Data
public class SubsetPermissionsQuery extends BasePage implements Serializable {

    private static final long serialVersionUID = 742833405930788595L;

    /**
     * 上级ID，顶级默认为0
     */
    @NotNull(message = "上级ID不可为空")
    private Long parentId;

    /**
     * 路由名称, 必须设置,且不能重名
     */
    private String name;

    /**
     * 类型，1：菜单，2：按钮
     */
    private List<Integer> type;

    private boolean loadMenu;

    /**
     * 应用编码，标识权限所属的应用
     */
    private String appType;

}
