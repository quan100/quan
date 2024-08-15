package cn.javaquan.code.entity;

import java.util.List;

/**
 * 表数据
 *
 * @author chenshun/sunlightcs@gmail.com
 * @since 1.0.0
 */
public class TableEntity {

    /** 表的名称. */
    private String tableName;

    /** 表的备注. */
    private String comments;

    /** 表的主键. */
    private ColumnEntity pk;

    /** 表的列名(不包含主键). */
    private List<ColumnEntity> columns;

    /** 类名(第一个字母大写)，如：sys_user => SysUser. */
    private String className;

    /** 类名(第一个字母小写)，如：sys_user => sysUser. */
    private String classname;

    private Boolean hasBigDecimal;

    private Boolean hasDate;

    private String mapping;

    /**
     * tableName
     * @return tableName
     */
    public String getTableName() {
        return this.tableName;
    }

    /**
     * tableName.
     * @param tableName tableName
     */
    public void setTableName(String tableName) {
        this.tableName = tableName;
    }

    /**
     * comments
     * @return comments
     */
    public String getComments() {
        return this.comments;
    }

    /**
     * comments.
     * @param comments comments
     */
    public void setComments(String comments) {
        this.comments = comments;
    }

    /**
     * pk
     * @return pk
     */
    public ColumnEntity getPk() {
        return this.pk;
    }

    /**
     * pk.
     * @param pk pk
     */
    public void setPk(ColumnEntity pk) {
        this.pk = pk;
    }

    /**
     * columns
     * @return columns
     */
    public List<ColumnEntity> getColumns() {
        return this.columns;
    }

    /**
     * columns.
     * @param columns columns
     */
    public void setColumns(List<ColumnEntity> columns) {
        this.columns = columns;
    }

    /**
     * className
     * @return className
     */
    public String getClassName() {
        return this.className;
    }

    /**
     * className.
     * @param className className
     */
    public void setClassName(String className) {
        this.className = className;
    }

    /**
     * classname
     * @return classname
     */
    public String getClassname() {
        return this.classname;
    }

    /**
     * classname.
     * @param classname classname
     */
    public void setClassname(String classname) {
        this.classname = classname;
    }

    /**
     * hasBigDecimal
     * @return hasBigDecimal
     */
    public Boolean getHasBigDecimal() {
        return this.hasBigDecimal;
    }

    /**
     * hasBigDecimal.
     * @param hasBigDecimal hasBigDecimal
     * @return TableEntity
     */
    public TableEntity setHasBigDecimal(Boolean hasBigDecimal) {
        this.hasBigDecimal = hasBigDecimal;
        return this;
    }

    /**
     * hasDate
     * @return hasDate
     */
    public Boolean getHasDate() {
        return this.hasDate;
    }

    /**
     * hasDate.
     * @param hasDate hasDate
     * @return TableEntity
     */
    public TableEntity setHasDate(Boolean hasDate) {
        this.hasDate = hasDate;
        return this;
    }

    /**
     * mapping
     * @return mapping
     */
    public String getMapping() {
        return this.mapping;
    }

    /**
     * mapping.
     * @param mapping mapping
     * @return TableEntity
     */
    public TableEntity setMapping(String mapping) {
        this.mapping = mapping;
        return this;
    }

}
