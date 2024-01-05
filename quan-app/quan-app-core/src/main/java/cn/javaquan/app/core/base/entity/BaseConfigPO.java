package cn.javaquan.app.core.base.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Date;

/**
 * 系统通用配置
 *
 * @author JavaQuan
 * @version 1.0.0
 * @date 2023-04-04 10:38:39
 */
@Data
@EqualsAndHashCode(callSuper = true)
@TableName("base_config")
public class BaseConfigPO extends Model<BaseConfigPO> {
    private static final long serialVersionUID = 1L;

    /**
     *
     */
    @TableId(value = "id", type = IdType.AUTO)
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
