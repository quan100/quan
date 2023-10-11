package com.quan.app.common.module.tools;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * @author JavaQuan
 * @since 2023-04-03 18:46:16
 */
@Data
public class ToolsVO implements Serializable {

    private static final long serialVersionUID = -137770378920503836L;

    /**
     * 自增主键
     */
    private Long id;

    /**
     * 图标地址
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
     *
     */
    private Date createTime;

    /**
     *
     */
    private Date updateTime;

    /**
     *
     */
    private String createUser;

    /**
     *
     */
    private String updateUser;

    /**
     * 状态，0：正常，1：审核中，2：审核不通过
     */
    private Integer status;

    /**
     * 删除状态，false：未删除，true：已删除
     */
    private Boolean delFlag;

    /**
     * 内容
     */
    private String content;

    /**
     * 内容跳转链接
     */
    private String jumpUrl;

    /**
     * 跳转类型
     */
    private Integer jumpType;

    /**
     * 排序
     */
    private Integer sort;

}
