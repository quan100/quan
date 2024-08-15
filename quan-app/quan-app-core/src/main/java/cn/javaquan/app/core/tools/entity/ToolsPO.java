package cn.javaquan.app.core.tools.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Date;

/**
 * 工具.
 *
 * @author javaquan
 * @since 1.0.0
 */
@Data
@EqualsAndHashCode(callSuper = true)
@TableName("tools")
public class ToolsPO extends Model<ToolsPO> {

    private static final long serialVersionUID = 1L;

    /**
     * 自增主键.
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 图标地址.
     */
    private String avatar;

    /**
     * 封面图.
     */
    private String cover;

    /**
     * 备注.
     */
    private String remarks;

    /**
     * 标题.
     */
    private String title;

    /**
     * 数据类型.
     */
    private Integer dataType;

    /**
     * 列表类型.
     */
    private Integer listType;

    /**
     * 创建时间.
     */
    private Date createTime;

    /**
     * 更新时间.
     */
    private Date updateTime;

    /**
     * 创建人.
     */
    private String createUser;

    /**
     * 更新人.
     */
    private String updateUser;

    /**
     * 状态.
     * <p>
     * 0：正常，1：审核中，2：审核不通过
     */
    private Integer status;

    /**
     * 删除状态.
     * <p>
     * false：未删除，true：已删除.
     */
    @TableLogic
    private Boolean delFlag;

    /**
     * 内容.
     */
    private String content;

    /**
     * 内容跳转链接.
     */
    private String jumpUrl;

    /**
     * 跳转类型.
     */
    private Integer jumpType;

    /**
     * 排序.
     */
    private Integer sort;

}
