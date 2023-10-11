package com.quan.app.common.module.base;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * 消息配置
 *
 * @author JavaQuan
 * @since 2023-04-04 10:38:39
 */
@Data
public class BaseConfigVO implements Serializable {

    private static final long serialVersionUID = -137770378920503836L;

    /**
     * 
     */
    private Integer id;

    /**
     * 服务器地址
     */
    private String host;

    /**
     * 服务端口
     */
    private Integer port;

    /**
     * 用户名
     */
    private String userName;

    /**
     * 密码
     */
    private String password;

    /**
     * 协议
     */
    private String protocol;

    /**
     * 
     */
    private Date createTime;

    /**
     * 
     */
    private Date updateTime;

}
