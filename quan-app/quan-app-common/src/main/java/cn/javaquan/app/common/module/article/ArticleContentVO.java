package cn.javaquan.app.common.module.article;

import lombok.Data;

import java.io.Serializable;

/**
 * 文章内容.
 *
 * @author javaquan
 * @since 1.0.0
 */
@Data
public class ArticleContentVO implements Serializable {

    private static final long serialVersionUID = -137770378920503836L;

    /**
     * 文章ID.
     */
    private String articleId;

    /**
     * 内容.
     */
    private String content;

    /**
     * 内容代码.
     */
    private String contentCode;

    /**
     * 文章缩略文.
     */
    private String briefContent;

}
