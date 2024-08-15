package cn.javaquan.app.common.module.article;

import cn.javaquan.common.base.message.BasePage;
import lombok.Data;

import java.io.Serializable;

/**
 * 文章标签配置.
 *
 * @author javaquan
 * @since 1.0.0
 */
@Data
public class ArticleTagConfigQuery extends BasePage implements Serializable {

    private static final long serialVersionUID = 742833405930788595L;

    /**
     * 自增主键.
     */
    private Long id;

    /**
     * 文章ID.
     */
    private String articleId;

    /**
     * 标签ID.
     */
    private String tagId;

    /**
     * 删除状态，false：未删除，true：已删除.
     */
    private Boolean delFlag;

}
