package cn.javaquan.app.common.module.article;

import lombok.Data;

import java.io.Serializable;

/**
 * 文章分类配置.
 *
 * @author javaquan
 * @since 1.0.0
 */
@Data
public class ArticleCategoryConfigVO implements Serializable {

    private static final long serialVersionUID = -137770378920503836L;

    /**
     * 自增主键.
     */
    private Long id;

    /**
     * 文章ID.
     */
    private String articleId;

    /**
     * 分类ID.
     */
    private String categoryId;

    /**
     * 删除状态，false：未删除，true：已删除.
     */
    private Boolean delFlag;

}
