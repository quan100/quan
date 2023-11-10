package com.quan.app.core.chat.repository;

import com.baomidou.mybatisplus.extension.service.IService;
import com.quan.common.base.message.BasePage;
import com.quan.common.base.message.PageResult;
import com.quan.app.core.chat.entity.ChatGroupInfoPO;

/**
 * 群组信息
 *
 * @author JavaQuan
 * @version 1.0.0
 */
public interface ChatGroupInfoRepository extends IService<ChatGroupInfoPO> {

    /**
     * 分页查询
     *
     * @param po
     * @param basePage
     * @return
     */
    PageResult<ChatGroupInfoPO> page(ChatGroupInfoPO po, BasePage basePage);

}

