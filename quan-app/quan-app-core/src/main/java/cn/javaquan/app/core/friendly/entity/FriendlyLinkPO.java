package cn.javaquan.app.core.friendly.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Date;

/**
 * 友情链接.
 *
 * @author javaquan
 * @since 1.0.0
 */
@Data
@EqualsAndHashCode(callSuper = true)
@TableName("friendly_link")
public class FriendlyLinkPO extends Model<FriendlyLinkPO> {

    private static final long serialVersionUID = 1L;

    /**
     * 自增主键.
     */
    @TableId(value = "id", type = IdType.AUTO)
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
     * 删除状态，false：未删除，true：已删除.
     */
    @TableLogic
    private Boolean delFlag;

    /**
     * 前端样式配置.
     */
    private String style;

}
