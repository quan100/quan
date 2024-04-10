package cn.javaquan.app.core.comment.artalk.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Date;

/**
 * @author JavaQuan
 * @version 1.0.0
 */
@Data
@EqualsAndHashCode(callSuper = true)
@TableName("comment_pages")
public class ArtalkCommentPagesPO extends Model<ArtalkCommentPagesPO> {
    private static final long serialVersionUID = 1L;

    /**
     *
     */
    @TableId(value = "id", type = IdType.AUTO)
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
    @TableField("`key`")
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
