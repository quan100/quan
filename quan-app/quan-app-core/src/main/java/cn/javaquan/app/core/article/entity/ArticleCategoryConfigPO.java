package cn.javaquan.app.core.article.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 文章分类配置.
 *
 * @author javaquan
 * @since 1.0.0
 */
@Data
@EqualsAndHashCode(callSuper = true)
@TableName("article_category_config")
public class ArticleCategoryConfigPO extends Model<ArticleCategoryConfigPO> {

    private static final long serialVersionUID = 1L;

    /**
     * 自增主键.
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 文章ID.
     */
    private String articleId;

    /**
     * 分类ID.
     */
    private String categoryId;

    /**
     * 删除状态，false：未删除，true：已删除.
     */
    @TableLogic
    private Boolean delFlag;

}
