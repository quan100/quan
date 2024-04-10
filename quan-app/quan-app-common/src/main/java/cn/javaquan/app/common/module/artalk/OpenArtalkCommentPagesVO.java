package cn.javaquan.app.common.module.artalk;

import lombok.Data;

import java.io.Serializable;

/**
 * @author JavaQuan
 * @version 1.0.0
 */
@Data
public class OpenArtalkCommentPagesVO implements Serializable {

    private static final long serialVersionUID = -137770378920503836L;

    /**
     *
     */
    private Long id;

    /**
     *
     */
    private String key;

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
