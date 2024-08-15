package cn.javaquan.app.core.chat.repository;

import com.baomidou.mybatisplus.extension.service.IService;
import cn.javaquan.common.base.message.BasePage;
import cn.javaquan.common.base.message.PageResult;
import cn.javaquan.app.core.chat.entity.ChatUserFriendPO;

import java.util.List;

/**
 * 用户好友信息关联表.
 *
 * @author javaquan
 * @since 1.0.0
 */
public interface ChatUserFriendRepository extends IService<ChatUserFriendPO> {

    /**
     * 分页查询.
     * @param po 查询参数
     * @param basePage 分页参数
     * @return 查询结果
     */
    PageResult<ChatUserFriendPO> page(ChatUserFriendPO po, BasePage basePage);

    /**
     * 查询用户所有的好友ID.
     * @param userId 用户ID
     * @return 查询结果
     */
    List<ChatUserFriendPO> queryByUserId(String userId);

}
