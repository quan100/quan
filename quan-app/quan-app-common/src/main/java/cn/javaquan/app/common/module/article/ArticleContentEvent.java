package cn.javaquan.app.common.module.article;

import lombok.Data;

import java.io.Serializable;

/**
 * @author wangquan
 */
@Data
public class ArticleContentEvent implements Serializable {

    private static final long serialVersionUID = 2300330554469946683L;

    /**
     * 文章ID
     */
    private String articleId;

    /**
     * 内容
     */
    private String content;

    /**
     * 内容代码
     */
    private String contentCode;

    /**
     * 文章缩略文
     */
    private String briefContent;
}
