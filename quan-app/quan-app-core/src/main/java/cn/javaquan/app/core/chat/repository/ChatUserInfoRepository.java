package cn.javaquan.app.core.chat.repository;

import com.baomidou.mybatisplus.extension.service.IService;
import cn.javaquan.common.base.message.BasePage;
import cn.javaquan.common.base.message.PageResult;
import cn.javaquan.app.core.chat.entity.ChatUserInfoPO;

import java.util.List;

/**
 * 用户信息表.
 *
 * @author javaquan
 * @since 1.0.0
 */
public interface ChatUserInfoRepository extends IService<ChatUserInfoPO> {

    /**
     * 分页查询.
     * @param po 查询参数
     * @param basePage 分页参数
     * @return 查询结果
     */
    PageResult<ChatUserInfoPO> page(ChatUserInfoPO po, BasePage basePage);

    /**
     * 根据用户ID查询.
     * @param userId 用户ID
     * @return 查询结果
     */
    ChatUserInfoPO queryByUserId(String userId);

    /**
     * 根据用户ID查询.
     * @param userIds 用户ID
     * @return 查询结果
     */
    List<ChatUserInfoPO> queryByUserIds(List<String> userIds);

}
