package cn.javaquan.app.core.chat.repository;

import com.baomidou.mybatisplus.extension.service.IService;
import cn.javaquan.common.base.message.BasePage;
import cn.javaquan.common.base.message.PageResult;
import cn.javaquan.app.core.chat.entity.ChatGroupInfoPO;

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

