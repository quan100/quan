package cn.javaquan.app.common.module.friendly;

import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import java.io.Serializable;

/**
 * 友情链接
 *
 * @author JavaQuan
 * @since 2023-04-04 10:53:59
 */
@Data
public class FriendlyLinkApplyCommand implements Serializable {

    private static final long serialVersionUID = -137770378920503836L;

    /**
     * 站点名称
     */
    @NotBlank(message = "站点名称不可为空")
    private String name;

    /**
     * 链接
     */
    @NotBlank(message = "站点链接不可为空")
    private String linkUrl;

    /**
     * 图标地址
     */
    @NotBlank(message = "头像链接不可为空")
    private String avatar;

    /**
     * 联系邮箱
     */
    @Email(message = "邮箱格式不正确")
    @NotBlank(message = "联系邮箱不可为空")
    private String email;

    /**
     * 描述信息
     */
    @NotBlank(message = "站点介绍不可为空")
    private String description;
}
