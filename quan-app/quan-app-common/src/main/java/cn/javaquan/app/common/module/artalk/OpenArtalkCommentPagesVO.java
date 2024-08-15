package cn.javaquan.app.common.module.artalk;

import lombok.Data;

import java.io.Serializable;

/**
 * Artalk 评论数据展示参数.
 *
 * @author javaquan
 * @since 1.0.0
 */
@Data
public class OpenArtalkCommentPagesVO implements Serializable {

    private static final long serialVersionUID = -137770378920503836L;

    /**
     * 主键.
     */
    private Long id;

    /**
     * 页面唯一键.
     */
    private String key;

    /**
     * 点赞数.
     */
    private Long voteUp;

    /**
     * 反对数.
     */
    private Long voteDown;

    /**
     * 浏览量.
     */
    private Long pv;

}
