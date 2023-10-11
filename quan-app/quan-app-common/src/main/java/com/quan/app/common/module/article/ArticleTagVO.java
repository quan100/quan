package com.quan.app.common.module.article;

import lombok.Data;

import java.io.Serializable;

/**
 * 文章标签
 *
 * @author JavaQuan
 * @since 2023-04-04 10:34:58
 */
@Data
public class ArticleTagVO implements Serializable {

    private static final long serialVersionUID = -137770378920503836L;

    /**
     * 标签ID
     */
    private String tagId;

    /**
     * 标签名称
     */
    private String name;

    /**
     * 标签颜色
     */
    private String color;

    /**
     * 标签图标
     */
    private String icon;

}
