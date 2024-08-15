package cn.javaquan.app.common.module.friendly;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * 友情链接.
 *
 * @author javaquan
 * @since 1.0.0
 */
@Data
public class FriendlyLinkUpdateCommand implements Serializable {

    private static final long serialVersionUID = -137770378920503836L;

    /**
     * 自增主键.
     */
    private Long id;

    /**
     * 站点名称.
     */
    private String name;

    /**
     * 链接.
     */
    private String linkUrl;

    /**
     * 图标地址.
     */
    private String avatar;

    /**
     * 联系邮箱.
     */
    private String email;

    /**
     * 联系邮箱是否公开.
     */
    private Boolean emailPublic;

    /**
     * 备注.
     */
    private String remarks;

    /**
     * 描述信息.
     */
    private String description;

    /**
     * 排序.
     */
    private Integer sort;

    /**
     * 创建时间.
     */
    private Date createTime;

    /**
     * 更新时间.
     */
    private Date updateTime;

    /**
     * 创建人.
     */
    private String createUser;

    /**
     * 更新人.
     */
    private String updateUser;

    /**
     * 状态，0：正常，1：审核中，2：审核不通过.
     */
    private Integer status;

    /**
     * 前端样式配置.
     */
    private String style;

}
