package cn.javaquan.code.entity;

import lombok.Data;

/**
 * 列的属性
 *
 * @author chenshun/sunlightcs@gmail.com
 * @since 1.0.0
 */
@Data
public class ColumnEntity {

    /**
     * 列名.
     */
    private String columnName;

    /**
     * 列名类型.
     */
    private String dataType;

    /**
     * 列名备注.
     */
    private String comments;

    /**
     * 属性名称(第一个字母大写)，如：user_name => UserName.
     */
    private String attrName;

    /**
     * 属性名称(第一个字母小写)，如：user_name => userName.
     */
    private String attrname;

    /**
     * 属性类型.
     */
    private String attrType;

    /**
     * auto_increment.
     */
    private String extra;

    /**
     * attrName
     * @return attrName
     */
    public String getAttrName() {
        return attrName;
    }

    /**
     * attrName.
     * @param attrName attrName
     */
    public void setAttrName(String attrName) {
        this.attrName = attrName;
    }

    /**
     * attrname
     * @return attrname
     */
    public String getAttrname() {
        return attrname;
    }

    /**
     * attrname.
     * @param attrname attrname
     */
    public void setAttrname(String attrname) {
        this.attrname = attrname;
    }

}
