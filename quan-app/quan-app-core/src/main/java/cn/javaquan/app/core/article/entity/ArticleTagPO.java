package cn.javaquan.app.core.article.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Date;

/**
 * 文章标签.
 *
 * @author javaquan
 * @since 1.0.0
 */
@Data
@EqualsAndHashCode(callSuper = true)
@TableName("article_tag")
public class ArticleTagPO extends Model<ArticleTagPO> {

    private static final long serialVersionUID = 1L;

    /**
     * 自增主键.
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 标签ID.
     */
    private String tagId;

    /**
     * 标签名称.
     */
    private String name;

    /**
     * 标签颜色.
     */
    private String color;

    /**
     * 标签图标.
     */
    private String icon;

    /**
     * 0：自定义标签；1：系统标签.
     */
    private Integer type;

    /**
     * 更新时间.
     */
    private Date updateTime;

    /**
     * 删除状态，false：未删除，true：已删除.
     */
    @TableLogic
    private Boolean delFlag;

}
