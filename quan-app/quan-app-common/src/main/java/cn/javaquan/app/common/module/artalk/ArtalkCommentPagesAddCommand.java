package cn.javaquan.app.common.module.artalk;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * @author JavaQuan
 * @version 1.0.0
 */
@Data
public class ArtalkCommentPagesAddCommand implements Serializable {

    private static final long serialVersionUID = -137770378920503836L;

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
