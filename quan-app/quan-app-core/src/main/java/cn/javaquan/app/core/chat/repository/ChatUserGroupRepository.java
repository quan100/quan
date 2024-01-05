package cn.javaquan.app.core.chat.repository;

import com.baomidou.mybatisplus.extension.service.IService;
import cn.javaquan.common.base.message.BasePage;
import cn.javaquan.common.base.message.PageResult;
import cn.javaquan.app.core.chat.entity.ChatUserGroupPO;

import java.util.List;

/**
 * 用户群组表
 *
 * @author JavaQuan
 * @version 1.0.0
 */
public interface ChatUserGroupRepository extends IService<ChatUserGroupPO> {

    /**
     * 分页查询
     *
     * @param po
     * @param basePage
     * @return
     */
    PageResult<ChatUserGroupPO> page(ChatUserGroupPO po, BasePage basePage);

    /**
     * 根据用户ID查询用户加入的群
     *
     * @param userId
     * @return
     */
    List<ChatUserGroupPO> queryByUserId(String userId);

    /**
     * 根据群ID查询所有群员
     *
     * @param groupId
     * @return
     */
    List<ChatUserGroupPO> queryByGroupId(String groupId);

}

