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
public class FriendlyLinkVO implements Serializable {

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
     * 前端样式配置.
     */
    private String style;

}
