package cn.javaquan.app.core.article.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import lombok.Data;
import lombok.EqualsAndHashCode;


/**
 * 文章标签配置
 *
 * @author JavaQuan
 * @since 2023-04-04 10:38:39
 */
@Data
@EqualsAndHashCode(callSuper = true)
@TableName("article_tag_config")
public class ArticleTagConfigPO extends Model<ArticleTagConfigPO> {
    private static final long serialVersionUID = 1L;

    /**
     * 自增主键
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 文章ID
     */
    private String articleId;

    /**
     * 标签ID
     */
    private String tagId;

    /**
     * 删除状态，false：未删除，true：已删除
     */
    @TableLogic
    private Boolean delFlag;

}
