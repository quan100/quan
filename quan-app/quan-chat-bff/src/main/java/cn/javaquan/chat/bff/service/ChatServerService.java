package cn.javaquan.chat.bff.service;

import cn.javaquan.chat.bff.convert.MineInfoAssembler;
import cn.javaquan.chat.bff.feign.*;
import com.quan.app.common.module.chat.ChatUserFriendDTO;
import com.quan.app.common.module.chat.ChatUserFriendGroupDTO;
import com.quan.app.common.module.chat.ChatUserGroupDTO;
import com.quan.app.common.module.chat.ChatUserInfoDTO;
import com.quan.app.common.module.chat.layim.MineInfoVo;
import com.quan.common.base.message.Result;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;


/**
 * 聊天服务接口
 *
 * @author JavaQuan
 * @version 1.0.0
 */
@RequiredArgsConstructor
@Service
public class ChatServerService {

    private final ChatUserInfoServiceFeign chatUserInfoService;
    private final ChatUserFriendServiceFeign chatUserFriendService;
    private final ChatUserFriendGroupServiceFeign chatUserFriendGroupService;

    private final ChatGroupInfoServiceFeign chatGroupInfoService;
    private final ChatUserGroupServiceFeign chatUserGroupService;

    /**
     * 获取好友信息列表
     *
     * @param userId
     * @return
     */
    public MineInfoVo getList(String userId) {
        // 获取用户信息
        Result<ChatUserInfoDTO> userInfo = chatUserInfoService.queryByUserId(userId);
        if (!userInfo.isData()) {
            throw new RuntimeException("用户未注册");
        }
        // 获取好友信息
        Result<List<ChatUserFriendDTO>> userFriends = chatUserFriendService.queryByUserId(userId);
        Result<List<ChatUserInfoDTO>> friendUserInfos = Result.success();
        if (userFriends.isData()) {
            List<String> friendUserIds = userFriends.getData().stream().map(ChatUserFriendDTO::getFriendUserId).collect(Collectors.toList());
            friendUserInfos = chatUserInfoService.queryByUserIds(friendUserIds);
        }

        // 获取好友分组信息
        Result<List<ChatUserFriendGroupDTO>> userFriendGroups = chatUserFriendGroupService.queryByUserId(userId);
        Result<List<ChatUserGroupDTO>> userGroups = Result.success();
        if (userFriendGroups.isData()) {
            // 获取用户加入的群组
            userGroups = chatUserGroupService.queryByUserId(userId);
        }

        return MineInfoAssembler.INSTANCE.toMineInfoVo(userInfo.getData(), userFriends.getData(), friendUserInfos.getData(), userFriendGroups.getData(), userGroups.getData());
    }

}
