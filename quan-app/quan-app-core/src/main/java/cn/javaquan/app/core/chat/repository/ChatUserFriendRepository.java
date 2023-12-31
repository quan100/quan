package cn.javaquan.app.core.chat.repository;

import com.baomidou.mybatisplus.extension.service.IService;
import cn.javaquan.common.base.message.BasePage;
import cn.javaquan.common.base.message.PageResult;
import cn.javaquan.app.core.chat.entity.ChatUserFriendPO;

import java.util.List;

/**
 * 用户好友信息关联表
 *
 * @author JavaQuan
 * @version 1.0.0
 */
public interface ChatUserFriendRepository extends IService<ChatUserFriendPO> {

    /**
     * 分页查询
     *
     * @param po
     * @param basePage
     * @return
     */
    PageResult<ChatUserFriendPO> page(ChatUserFriendPO po, BasePage basePage);

    /**
     * 查询用户所有的好友ID
     *
     * @param userId
     * @return
     */
    List<ChatUserFriendPO> queryByUserId(String userId);

}

