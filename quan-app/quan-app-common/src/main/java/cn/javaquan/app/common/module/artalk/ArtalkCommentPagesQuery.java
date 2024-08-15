package cn.javaquan.app.common.module.artalk;

import cn.javaquan.common.base.message.BasePage;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * Artalk 评论数据查询参数.
 *
 * @author javaquan
 * @since 1.0.0
 */
@Data
public class ArtalkCommentPagesQuery extends BasePage implements Serializable {

    private static final long serialVersionUID = 742833405930788595L;

    /**
     * 主键.
     */
    private Long id;

    /**
     * 创建时间.
     */
    private Date createdAt;

    /**
     * 更新时间.
     */
    private Date updatedAt;

    /**
     * 删除时间.
     */
    private Date deletedAt;

    /**
     * 页面唯一键.
     */
    private String key;

    /**
     * 页面标题.
     */
    private String title;

    /**
     * 管理员.
     */
    private Integer adminOnly;

    /**
     * 站点名称.
     */
    private String siteName;

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
