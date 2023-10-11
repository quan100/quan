package com.quan.app.common.module.tools;

import lombok.Data;

import java.io.Serializable;

/**
 * 工具
 *
 * @author wangquan
 * @since 2020-12-27 17:50:38
 */
@Data
public class OpenToolsVO implements Serializable {

    private static final long serialVersionUID = 3495832154878623290L;

    private Long id;

    /**
     * 图标
     */
    private String avatar;

    /**
     * 封面图
     */
    private String cover;

    /**
     * 备注
     */
    private String remarks;

    /**
     * 内容
     */
    private String content;

    /**
     * 标题
     */
    private String title;

    /**
     * 数据类型
     */
    private Integer dataType;

    /**
     * 列表类型
     */
    private Integer listType;

    /**
     * 状态，0：正常，1：审核中，2：审核不通过
     */
    private Integer status;

    /**
     * 跳转链接
     */
    private String jumpUrl;

    /**
     * 跳转类型
     * 1：站内资源
     * 2：站外资源
     */
    private Integer jumpType;

    /**
     * 排序
     */
    private Integer sort;

    /**
     * 创建人
     */
    private String createUser;

    /**
     * 创建人
     */
    private String updateUser;

}
