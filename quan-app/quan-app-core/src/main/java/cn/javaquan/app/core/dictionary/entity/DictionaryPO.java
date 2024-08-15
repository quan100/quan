package cn.javaquan.app.core.dictionary.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import com.baomidou.mybatisplus.extension.handlers.JacksonTypeHandler;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Date;

/**
 * 字典.
 *
 * @author javaquan
 * @since 1.0.0
 */
@Data
@EqualsAndHashCode(callSuper = true)
@TableName(value = "dictionary", autoResultMap = true)
public class DictionaryPO extends Model<DictionaryPO> {

    private static final long serialVersionUID = 1L;

    /**
     * 自增主键.
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 字典编码.
     */
    @TableField("`code`")
    private String code;

    /**
     * 字典值 JSON格式.
     */
    @TableField(value = "`value`", typeHandler = JacksonTypeHandler.class)
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
    @TableField("`name`")
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

    /**
     * 删除状态，false：未删除，true：已删除.
     */
    @TableLogic
    private Boolean delFlag;

}
