package cn.javaquan.app.common.module.article;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * 文章内容
 *
 * @author JavaQuan
 * @since 2023-04-04 10:34:58
 */
@Data
public class ArticleContentQuery implements Serializable {

    private static final long serialVersionUID = 742833405930788595L;

    /**
     * 文章ID
     */
    private String articleId;

    /**
     * 内容
     */
    private String content;

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
