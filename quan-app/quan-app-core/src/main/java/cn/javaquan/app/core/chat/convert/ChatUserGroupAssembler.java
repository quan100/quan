package cn.javaquan.app.core.chat.convert;

import cn.javaquan.app.common.module.chat.ChatUserGroupAddCommand;
import cn.javaquan.app.common.module.chat.ChatUserGroupQuery;
import cn.javaquan.app.common.module.chat.ChatUserGroupUpdateCommand;
import cn.javaquan.app.core.chat.entity.ChatUserGroupPO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.List;

/**
 * 用户群组表参数转换
 *
 * @author JavaQuan
 * @version 1.0.0
 */
@Mapper
public interface ChatUserGroupAssembler {

    ChatUserGroupAssembler INSTANCE = Mappers.getMapper(ChatUserGroupAssembler.class);

    /**
     * 转换为查询参数
     *
     * @param query
     * @return
     */
    ChatUserGroupPO toQueryPO(ChatUserGroupQuery query);

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
    ChatUserGroupPO toUpdatePO(ChatUserGroupUpdateCommand cmd);

    /**
     * 转换为新增参数
     * <p>
     * 新增时删除状态默认为正常。
     *
     * @param cmd
     * @return
     */
    @Mapping(target = "delFlag", constant = "false")
    ChatUserGroupPO toAddPO(ChatUserGroupAddCommand cmd);

    /**
     * 转换为新增参数
     *
     * @param cmds
     * @return
     */
    List<ChatUserGroupPO> toAddPOS(List<ChatUserGroupAddCommand> cmds);
}
