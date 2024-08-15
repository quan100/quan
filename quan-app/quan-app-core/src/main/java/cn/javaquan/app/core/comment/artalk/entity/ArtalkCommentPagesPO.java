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
 * artalk 评论数据模型.
 *
 * @author javaquan
 * @since 1.0.0
 */
@Data
@EqualsAndHashCode(callSuper = true)
@TableName("comment_pages")
public class ArtalkCommentPagesPO extends Model<ArtalkCommentPagesPO> {

    private static final long serialVersionUID = 1L;

    /**
     * 自增主键.
     */
    @TableId(value = "id", type = IdType.AUTO)
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
    @TableField("`key`")
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
