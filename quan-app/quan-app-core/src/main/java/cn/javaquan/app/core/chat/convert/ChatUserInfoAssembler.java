package cn.javaquan.app.core.chat.convert;

import cn.javaquan.app.common.module.chat.ChatUserInfoAddCommand;
import cn.javaquan.app.common.module.chat.ChatUserInfoQuery;
import cn.javaquan.app.common.module.chat.ChatUserInfoUpdateCommand;
import cn.javaquan.app.core.chat.entity.ChatUserInfoPO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.List;

/**
 * 用户信息表参数转换.
 *
 * @author javaquan
 * @since 1.0.0
 */
@Mapper
public interface ChatUserInfoAssembler {

    /**
     * 返回给定映射器类型的实例.
     */
    ChatUserInfoAssembler INSTANCE = Mappers.getMapper(ChatUserInfoAssembler.class);

    /**
     * 转换为查询参数.
     * @param query 查询参数
     * @return 查询参数
     */
    ChatUserInfoPO toQueryPO(ChatUserInfoQuery query);

    /**
     * 转换为更新参数.
     * <p>
     * 更新自动配置更新时间。 更新时不处理删除状态，删除状态交由删除功能处理。
     * @param cmd 更新指令参数
     * @return 更新参数
     */
    @Mapping(target = "delFlag", ignore = true)
    ChatUserInfoPO toUpdatePO(ChatUserInfoUpdateCommand cmd);

    /**
     * 转换为新增参数.
     * <p>
     * 新增时删除状态默认为正常。
     * @param cmd 新增指令参数
     * @return 新增参数
     */
    @Mapping(target = "delFlag", constant = "false")
    ChatUserInfoPO toAddPO(ChatUserInfoAddCommand cmd);

    /**
     * 转换为新增参数.
     * @param cmds 新增参数
     * @return 新增参数
     */
    List<ChatUserInfoPO> toAddPOS(List<ChatUserInfoAddCommand> cmds);

}
