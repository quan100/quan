package cn.javaquan.app.common.module.article;

import lombok.Data;

import java.io.Serializable;

/**
 * 文章分类.
 *
 * @author javaquan
 * @since 1.0.0
 */
@Data
public class ArticleCategoryVO implements Serializable {

    private static final long serialVersionUID = -137770378920503836L;

    /**
     * 自增主键.
     */
    private Long id;

    /**
     * 分类ID.
     */
    private String categoryId;

    /**
     * 分类名称.
     */
    private String name;

    /**
     * 0：自定义分类；1：系统分类.
     */
    private Integer type;

    /**
     * 字体颜色.
     */
    private String color;

    /**
     * 排序.
     */
    private Integer sort;

}
