package cn.javaquan.app.common.module.tools;

import cn.javaquan.common.base.message.BasePage;
import lombok.Data;

import java.io.Serializable;

/**
 * 提供开放接口的在线工具查询参数.
 *
 * @author wangquan
 * @since 1.0.0
 */
@Data
public class OpenToolsQuery extends BasePage implements Serializable {

    private static final long serialVersionUID = 3495832154878623290L;

    /**
     * 标题.
     */
    private String title;

    /**
     * 数据类型.
     */
    private Integer dataType;

}
