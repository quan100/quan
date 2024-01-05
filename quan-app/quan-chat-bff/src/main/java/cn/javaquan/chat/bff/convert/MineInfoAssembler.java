package cn.javaquan.chat.bff.convert;

import cn.javaquan.app.common.module.chat.ChatUserFriendDTO;
import cn.javaquan.app.common.module.chat.ChatUserFriendGroupDTO;
import cn.javaquan.app.common.module.chat.ChatUserGroupDTO;
import cn.javaquan.app.common.module.chat.ChatUserInfoDTO;
import cn.javaquan.app.common.module.chat.layim.MineFriendGroupVO;
import cn.javaquan.app.common.module.chat.layim.MineGroupVO;
import cn.javaquan.app.common.module.chat.layim.MineInfoVo;
import cn.javaquan.app.common.module.chat.layim.MineVO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;
import org.springframework.util.CollectionUtils;

import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * 参数转换
 *
 * @author JavaQuan
 * @version 1.0.0
 */
@Mapper
public interface MineInfoAssembler {

    MineInfoAssembler INSTANCE = Mappers.getMapper(MineInfoAssembler.class);

    /**
     * 转换为layim数据结构
     *
     * @param userInfo         当前用户信息
     * @param userFriends      好友关联信息
     * @param friendUserInfos  好友详细信息
     * @param userFriendGroups 好友分组详细信息
     * @param userGroups       用户加入的群组
     * @return
     */
    default MineInfoVo toMineInfoVo(ChatUserInfoDTO userInfo, List<ChatUserFriendDTO> userFriends, List<ChatUserInfoDTO> friendUserInfos, List<ChatUserFriendGroupDTO> userFriendGroups, List<ChatUserGroupDTO> userGroups) {
        MineInfoVo mineInfoVo = new MineInfoVo();
        mineInfoVo.setMine(toMineVO(userInfo));

        if (CollectionUtils.isEmpty(userFriends)) {
            return mineInfoVo;
        }

        Map<String, ChatUserInfoDTO> friendUserInfoToMap = friendUserInfos.stream().collect(Collectors.toMap(ChatUserInfoDTO::getUserId, v -> v));
        Map<String, List<ChatUserFriendDTO>> userFriendGroup = userFriends.stream().collect(Collectors.groupingBy(ChatUserFriendDTO::getFriendGroupId));

        List<MineFriendGroupVO> friendGroups = userFriendGroups.stream().map(userFriendGroupPO -> {
            MineFriendGroupVO friendGroup = toMineFriendGroupVO(userFriendGroupPO);

            List<ChatUserFriendDTO> userFriendsByGroup = userFriendGroup.get(friendGroup.getGroupId());
            List<ChatUserInfoDTO> userFriendsInfo = userFriendsByGroup.stream().map(userFriendPO -> {
                return friendUserInfoToMap.get(userFriendPO.getFriendUserId());
            }).collect(Collectors.toList());

            friendGroup.setList(toMineVOList(userFriendsInfo));
            return friendGroup;
        }).sorted(Comparator.comparing(MineFriendGroupVO::getOrder)).collect(Collectors.toList());

        mineInfoVo.setFriend(friendGroups);
        mineInfoVo.setGroup(toMineGroupVOList(userGroups));
        return mineInfoVo;
    }

    @Mapping(target = "id", source = "userId")
    @Mapping(target = "username", source = "userName")
    MineVO toMineVO(ChatUserInfoDTO dto);

    List<MineVO> toMineVOList(List<ChatUserInfoDTO> dtoList);

    @Mapping(target = "groupname", source = "groupName")
    MineFriendGroupVO toMineFriendGroupVO(ChatUserFriendGroupDTO dto);

    MineGroupVO toMineGroupVO(ChatUserGroupDTO dto);

    List<MineGroupVO> toMineGroupVOList(List<ChatUserGroupDTO> dtoList);
}
