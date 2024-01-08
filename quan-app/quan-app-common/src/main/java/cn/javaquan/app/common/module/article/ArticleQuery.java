package cn.javaquan.app.common.module.article;

import cn.javaquan.common.base.message.BasePage;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * 文章
 *
 * @author JavaQuan
 * @since 2023-04-04 10:34:58
 */
@Data
public class ArticleQuery extends BasePage implements Serializable {

    private static final long serialVersionUID = 742833405930788595L;

    /**
     * 自增主键
     */
    private Long id;

    /**
     * 文章ID
     */
    private String articleId;

    /**
     * 用户ID
     */
    private String userId;

    /**
     * 标题
     */
    private String title;

    /**
     * 作者
     */
    private String author;

    /**
     * 作者链接
     */
    private String authorUrl;

    /**
     * 文章类型, 1：原创，2：转载，3：官方
     */
    private Integer type;

    /**
     * 发布类型, 1：全部可见，2：仅自己可见，3：粉丝可见
     */
    private Integer publishType;

    /**
     * 文章来源
     */
    private String source;

    /**
     * 文章来源链接
     */
    private String sourceUrl;

    /**
     * 头像地址
     */
    private String avatar;

    /**
     * 封面图
     */
    private String cover;

    /**
     * 作者联系账号
     */
    private String authorAccounts;

    /**
     * 作者联系账号是否公开
     */
    private Boolean authorAccountsPublic;

    /**
     * 备注
     */
    private String remarks;

    /**
     * 内容跳转链接
     */
    private String jumpUrl;

    /**
     * 跳转类型
     */
    private Integer jumpType;

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
     * 状态，0：正常，1：审核中，2：审核不通过
     */
    private Integer status;

    /**
     * 删除状态，false：未删除，true：已删除
     */
    private Boolean delFlag;

    /**
     * 文章缩略文
     */
    private String briefContent;

    /**
     * 分类ID
     */
    private String categoryId;

    /**
     * 是否置顶
     */
    private Boolean topping;
}
