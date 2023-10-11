package com.quan.app.core.article.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Date;

/**
 * 文章分类
 *
 * @author JavaQuan
 * @since 2023-04-04 10:38:40
 */
@Data
@EqualsAndHashCode(callSuper = true)
@TableName("article_category")
public class ArticleCategoryPO extends Model<ArticleCategoryPO> {
    private static final long serialVersionUID = 1L;

    /**
     * 自增主键
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 分类ID
     */
    private String categoryId;

    /**
     * 分类名称
     */
    private String name;

    /**
     * 0：自定义分类；1：系统分类
     */
    private Integer type;

    /**
     * 字体颜色
     */
    private String color;

    /**
     * 排序
     */
    private Integer sort;

    /**
     *
     */
    private Date createTime;

    /**
     *
     */
    private Date updateTime;

    /**
     *
     */
    private String createUser;

    /**
     *
     */
    private String updateUser;

    /**
     * 删除状态，false：未删除，true：已删除
     */
    @TableLogic
    private Boolean delFlag;

}
