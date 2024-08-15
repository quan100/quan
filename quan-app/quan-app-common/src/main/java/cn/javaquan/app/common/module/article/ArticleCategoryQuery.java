package cn.javaquan.app.common.module.article;

import cn.javaquan.common.base.message.BasePage;
import lombok.Data;

import java.io.Serializable;

/**
 * 文章分类.
 *
 * @author javaquan
 * @since 1.0.0
 */
@Data
public class ArticleCategoryQuery extends BasePage implements Serializable {

    private static final long serialVersionUID = 742833405930788595L;

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

}
