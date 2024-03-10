package cn.javaquan.app.common.module.friendly;

import lombok.Data;
import org.hibernate.validator.constraints.Length;

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
    @Length(max = 64, message = "站点名称长度需要在64以内")
    @NotBlank(message = "站点名称不可为空")
    private String name;

    /**
     * 链接
     */
    @Length(max = 256, message = "站点链接长度需要在256以内")
    @NotBlank(message = "站点链接不可为空")
    private String linkUrl;

    /**
     * 图标地址
     */
    @Length(max = 128, message = "头像链接长度需要在128以内")
    @NotBlank(message = "头像链接不可为空")
    private String avatar;

    /**
     * 联系邮箱
     */
    @Length(max = 128, message = "联系邮箱长度需要在128以内")
    @Email(message = "邮箱格式不正确")
    @NotBlank(message = "联系邮箱不可为空")
    private String email;

    /**
     * 描述信息
     */
    @Length(max = 200, message = "站点介绍长度需要在200以内")
    @NotBlank(message = "站点介绍不可为空")
    private String description;

    /**
     * 前端样式配置
     */
    @Length(max = 128, message = "样式代码长度需要在128以内")
    private String style;
}
