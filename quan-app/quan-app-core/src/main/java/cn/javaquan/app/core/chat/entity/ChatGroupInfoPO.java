package cn.javaquan.app.core.chat.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Date;

/**
 * 群组信息.
 *
 * @author javaquan
 * @since 1.0.0
 */
@Data
@EqualsAndHashCode(callSuper = true)
@TableName("chat_group_info")
public class ChatGroupInfoPO extends Model<ChatGroupInfoPO> {

    private static final long serialVersionUID = 1L;

    /**
     * 主键.
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 群组ID.
     */
    private String groupId;

    /**
     * 群组名称.
     */
    private String groupName;

    /**
     * 群公告，最大200个字符.
     */
    private String groupNotice;

    /**
     * 群主用户ID.
     */
    private String userId;

    /**
     * 群成员数量限制，默认：500.
     */
    private Integer groupLimit;

    /**
     * 群成员数量.
     */
    private Integer userCount;

    /**
     * 创建时间.
     */
    private Date createTime;

    /**
     * 更新时间.
     */
    private Date updateTime;

    /**
     * 删除状态.
     */
    @TableLogic
    private Boolean delFlag;

}
