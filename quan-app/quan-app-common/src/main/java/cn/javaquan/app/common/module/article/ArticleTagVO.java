package cn.javaquan.app.common.module.article;

import lombok.Data;

import java.io.Serializable;

/**
 * 文章标签.
 *
 * @author javaquan
 * @since 1.0.0
 */
@Data
public class ArticleTagVO implements Serializable {

    private static final long serialVersionUID = -137770378920503836L;

    /**
     * 标签ID.
     */
    private String tagId;

    /**
     * 标签名称.
     */
    private String name;

    /**
     * 标签颜色.
     */
    private String color;

    /**
     * 标签图标.
     */
    private String icon;

}
