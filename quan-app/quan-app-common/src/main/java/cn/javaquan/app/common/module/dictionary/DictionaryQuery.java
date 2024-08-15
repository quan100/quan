package cn.javaquan.app.common.module.dictionary;

import cn.javaquan.common.base.message.BasePage;
import lombok.Data;

import java.io.Serializable;

/**
 * 字典查询.
 *
 * @author javaquan
 * @since 1.0.0
 */
@Data
public class DictionaryQuery extends BasePage implements Serializable {

    private static final long serialVersionUID = 742833405930788595L;

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
     * 类型.
     */
    private String type;

}
