package com.quan.app.core.chat.convert;

import com.quan.app.common.module.chat.ChatHistoryAddCommand;
import com.quan.app.common.module.chat.ChatHistoryQuery;
import com.quan.app.common.module.chat.ChatHistoryUpdateCommand;
import com.quan.app.core.chat.entity.ChatHistoryPO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.List;

/**
 * 聊天记录表参数转换
 *
 * @author JavaQuan
 * @version 1.0.0
 */
@Mapper
public interface ChatHistoryAssembler {

    ChatHistoryAssembler INSTANCE = Mappers.getMapper(ChatHistoryAssembler.class);

    /**
     * 转换为查询参数
     *
     * @param query
     * @return
     */
    ChatHistoryPO toQueryPO(ChatHistoryQuery query);

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
    ChatHistoryPO toUpdatePO(ChatHistoryUpdateCommand cmd);

    /**
     * 转换为新增参数
     * <p>
     * 新增时删除状态默认为正常。
     *
     * @param cmd
     * @return
     */
    @Mapping(target = "delFlag", constant = "false")
    ChatHistoryPO toAddPO(ChatHistoryAddCommand cmd);

    /**
     * 转换为新增参数
     *
     * @param cmds
     * @return
     */
    List<ChatHistoryPO> toAddPOS(List<ChatHistoryAddCommand> cmds);
}
