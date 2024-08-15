package cn.javaquan.app.common.module.dictionary;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * 字典查询数据参数.
 *
 * @author javaquan
 * @since 1.0.0
 */
@Data
public class DictionaryVO implements Serializable {

    private static final long serialVersionUID = -137770378920503836L;

    /**
     * 自增主键.
     */
    private Long id;

    /**
     * 字典编码.
     */
    private String code;

    /**
     * 字典值.
     */
    private Object value;

    /**
     * 是否开放权限，0：否，1：是.
     */
    private Integer open;

    /**
     * 备注.
     */
    private String remark;

    /**
     * 字典名称.
     */
    private String name;

    /**
     * 创建时间.
     */
    private Date createTime;

    /**
     * 更新时间.
     */
    private Date updateTime;

    /**
     * 类型.
     */
    private String type;

}
