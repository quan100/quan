package cn.javaquan.app.common.module.article;

import cn.javaquan.common.base.message.BasePage;
import lombok.Data;

import java.io.Serializable;

/**
 * 应用层.
 *
 * @author wangquan
 * @since 1.0.0
 */
@Data
public class OpenArticleQuery extends BasePage implements Serializable {

    private static final long serialVersionUID = 1876294478340132249L;

    /**
     * 文章ID.
     */
    private String articleId;

    /**
     * 标题.
     */
    private String title;

    /**
     * 作者.
     */
    private String author;

    /**
     * 文章类型, 1：原创，2：转载，3：官方.
     */
    private Integer type;

    /**
     * 文章来源.
     */
    private String source;

    /**
     * 跳转类型.
     */
    private Integer jumpType;

    /**
     * 分类ID.
     */
    private String categoryId;

    /**
     * 标签ID.
     */
    private String tagId;

}
