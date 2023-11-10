package com.quan.app.core.chat.convert;

import com.quan.app.common.module.chat.ChatUserFriendGroupAddCommand;
import com.quan.app.common.module.chat.ChatUserFriendGroupQuery;
import com.quan.app.common.module.chat.ChatUserFriendGroupUpdateCommand;
import com.quan.app.core.chat.entity.ChatUserFriendGroupPO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.List;

/**
 * 用户好友分组表参数转换
 *
 * @author JavaQuan
 * @version 1.0.0
 */
@Mapper
public interface ChatUserFriendGroupAssembler {

    ChatUserFriendGroupAssembler INSTANCE = Mappers.getMapper(ChatUserFriendGroupAssembler.class);

    /**
     * 转换为查询参数
     *
     * @param query
     * @return
     */
    ChatUserFriendGroupPO toQueryPO(ChatUserFriendGroupQuery query);

    /**
     * 转换为更新参数
     * <p>
     * 更新自动配置更新时间。
     * 更新时不处理删除状态，删除状态交由删除功能处理。
     *
     * @param cmd
     * @return
     */
    @Mapping(target = "delFlag", ignore = true)
    ChatUserFriendGroupPO toUpdatePO(ChatUserFriendGroupUpdateCommand cmd);

    /**
     * 转换为新增参数
     * <p>
     * 新增时删除状态默认为正常。
     *
     * @param cmd
     * @return
     */
    @Mapping(target = "delFlag", constant = "false")
    ChatUserFriendGroupPO toAddPO(ChatUserFriendGroupAddCommand cmd);

    /**
     * 转换为新增参数
     *
     * @param cmds
     * @return
     */
    List<ChatUserFriendGroupPO> toAddPOS(List<ChatUserFriendGroupAddCommand> cmds);
}
