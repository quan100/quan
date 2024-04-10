package cn.javaquan.app.common.module.artalk;

import cn.javaquan.common.base.message.BasePage;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * @author JavaQuan
 * @version 1.0.0
 */
@Data
public class ArtalkCommentPagesQuery extends BasePage implements Serializable {

    private static final long serialVersionUID = 742833405930788595L;

    /**
     *
     */
    private Long id;

    /**
     *
     */
    private Date createdAt;

    /**
     *
     */
    private Date updatedAt;

    /**
     *
     */
    private Date deletedAt;

    /**
     *
     */
    private String key;

    /**
     *
     */
    private String title;

    /**
     *
     */
    private Integer adminOnly;

    /**
     *
     */
    private String siteName;

    /**
     *
     */
    private Long voteUp;

    /**
     *
     */
    private Long voteDown;

    /**
     *
     */
    private Long pv;

}
