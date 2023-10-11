package com.quan.app.common.module.article;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * 文章
 *
 * @author JavaQuan
 * @since 2023-04-04 10:34:58
 */
@Data
public class ArticleVO implements Serializable {

    private static final long serialVersionUID = -137770378920503836L;

    /**
     * 自增主键
     */
    private Long id;

    /**
     * 文章ID
     */
    private String articleId;

    /**
     * 用户ID
     */
    private String userId;

    /**
     * 标题
     */
    private String title;

    /**
     * 作者
     */
    private String author;

    /**
     * 作者链接
     */
    private String authorUrl;

    /**
     * 文章类型, 1：原创，2：转载，3：官方
     */
    private Integer type;

    /**
     * 发布类型, 1：全部可见，2：仅自己可见，3：粉丝可见
     */
    private Integer publishType;

    /**
     * 文章来源
     */
    private String source;

    /**
     * 文章来源链接
     */
    private String sourceUrl;

    /**
     * 头像地址
     */
    private String avatar;

    /**
     * 封面图
     */
    private String cover;

    /**
     * 作者联系账号
     */
    private String authorAccounts;

    /**
     * 作者联系账号是否公开
     */
    private Integer authorAccountsPublic;

    /**
     * 备注
     */
    private String remarks;

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
     * 文章缩略文
     */
    private String briefContent;

    /**
     * 文章内容
     */
    private Object content;

    /**
     * 分类ID
     */
    private String categoryId;

}
