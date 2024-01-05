package cn.javaquan.app.common.module.dictionary;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * @author JavaQuan
 * @since 2023-04-04 10:53:59
 */
@Data
public class DictionaryDTO implements Serializable {

    private static final long serialVersionUID = -137770378920503836L;

    /**
     * 自增主键
     */
    private Long id;

    /**
     * 字典编码
     */
    private String code;

    /**
     * 字典值
     */
    private Object value;

    /**
     * 是否开放权限，0：否，1：是
     */
    private Integer open;

    /**
     * 备注
     */
    private String remark;

    /**
     * 字典名称
     */
    private String name;

    /**
     *
     */
    private Date createTime;

    /**
     *
     */
    private Date updateTime;

    /**
     * 类型
     */
    private String type;

}
