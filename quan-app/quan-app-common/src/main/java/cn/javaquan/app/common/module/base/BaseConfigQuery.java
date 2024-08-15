package cn.javaquan.app.common.module.base;

import cn.javaquan.common.base.message.BasePage;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * 消息配置.
 *
 * @author javaquan
 * @since 1.0.0
 */
@Data
public class BaseConfigQuery extends BasePage implements Serializable {

    private static final long serialVersionUID = 742833405930788595L;

    /**
     * id.
     */
    private Integer id;

    /**
     * 服务器地址.
     */
    private String host;

    /**
     * 服务端口.
     */
    private Integer port;

    /**
     * 用户名.
     */
    private String userName;

    /**
     * 密码.
     */
    private String password;

    /**
     * 协议.
     */
    private String protocol;

    /**
     * 创建时间.
     */
    private Date createTime;

    /**
     * 更新时间.
     */
    private Date updateTime;

}
