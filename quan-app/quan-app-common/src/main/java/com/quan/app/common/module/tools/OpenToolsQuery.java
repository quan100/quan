package com.quan.app.common.module.tools;

import com.quan.common.base.message.BasePage;
import lombok.Data;

import java.io.Serializable;

/**
 * 工具
 *
 * @author wangquan
 * @since 2020-12-27 17:50:38
 */
@Data
public class OpenToolsQuery extends BasePage implements Serializable {

    private static final long serialVersionUID = 3495832154878623290L;

    /**
     * 标题
     */
    private String title;

    /**
     * 数据类型
     */
    private Integer dataType;

}
