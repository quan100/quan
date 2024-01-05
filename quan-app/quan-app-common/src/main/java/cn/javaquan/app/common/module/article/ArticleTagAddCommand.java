package cn.javaquan.app.common.module.article;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * 文章标签
 *
 * @author JavaQuan
 * @since 2023-04-04 10:34:58
 */
@Data
public class ArticleTagAddCommand implements Serializable {

    private static final long serialVersionUID = -137770378920503836L;

    /**
     * 自增主键
     */
    private Long id;

    /**
     * 标签ID
     */
    private String tagId;

    /**
     * 标签名称
     */
    private String name;

    /**
     * 标签颜色
     */
    private String color;

    /**
     * 标签图标
     */
    private String icon;

    /**
     * 0：自定义标签；1：系统标签
     */
    private Integer type;

    /**
     *
     */
    private Date updateTime;

    /**
     * 删除状态，false：未删除，true：已删除
     */
    private Boolean delFlag;

}
