package com.quan.app.core.article.entity;

import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;
import org.springframework.data.mongodb.core.mapping.MongoId;

import java.util.Date;

/**
 * <p>
 * 文章内容
 * </p>
 *
 * @author wangquan
 * @since 2023-01-03 21:31:47
 */
@Data
@Document(value = "article_content")
public class ArticleContentPO {
    private static final long serialVersionUID = 1L;

    /**
     * 文章ID
     */
    @MongoId
    private String articleId;

    /**
     * 内容
     */
    @Field("content")
    private String content;

    /**
     * 内容代码
     */
    private String contentCode;

    /**
     * 文章缩略文
     */
    private String briefContent;

    /**
     *
     */
    private Date updateTime;

    /**
     * 删除状态，false：未删除，true：已删除
     */
    private Boolean delFlag;

}
