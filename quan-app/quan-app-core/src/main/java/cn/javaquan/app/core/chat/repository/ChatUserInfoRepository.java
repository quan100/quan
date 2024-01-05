package cn.javaquan.app.core.chat.repository;

import com.baomidou.mybatisplus.extension.service.IService;
import cn.javaquan.common.base.message.BasePage;
import cn.javaquan.common.base.message.PageResult;
import cn.javaquan.app.core.chat.entity.ChatUserInfoPO;

import java.util.List;

/**
 * 用户信息表
 *
 * @author JavaQuan
 * @version 1.0.0
 */
public interface ChatUserInfoRepository extends IService<ChatUserInfoPO> {

    /**
     * 分页查询
     *
     * @param po
     * @param basePage
     * @return
     */
    PageResult<ChatUserInfoPO> page(ChatUserInfoPO po, BasePage basePage);

    /**
     * 根据用户ID查询
     *
     * @param userId
     * @return
     */
    ChatUserInfoPO queryByUserId(String userId);

    List<ChatUserInfoPO> queryByUserIds(List<String> userIds);

}

