package cn.javaquan.app.core.article.entity;

import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;
import org.springframework.data.mongodb.core.mapping.MongoId;

import java.io.Serializable;
import java.util.Date;

/**
 * 文章内容.
 *
 * @author wangquan
 * @since 1.0.0
 */
@Data
@Document("article_content")
public class ArticleContentPO implements Serializable {

    private static final long serialVersionUID = 2508095953284066797L;

    /**
     * 文章ID.
     */
    @MongoId
    private String articleId;

    /**
     * 内容.
     */
    @Field("content")
    private String content;

    /**
     * 内容代码.
     */
    private String contentCode;

    /**
     * 文章缩略文.
     */
    private String briefContent;

    /**
     * 更新时间.
     */
    private Date updateTime;

    /**
     * 删除状态，false：未删除，true：已删除.
     */
    private Boolean delFlag;

}
