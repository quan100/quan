package com.quan.app.common.module.dictionary;

import lombok.Data;

import java.io.Serializable;

/**
 * @author JavaQuan
 * @since 2023-04-03 18:23:54
 */
@Data
public class DictionaryUpdateCommand implements Serializable {

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
     * 类型
     */
    private String type;
}
