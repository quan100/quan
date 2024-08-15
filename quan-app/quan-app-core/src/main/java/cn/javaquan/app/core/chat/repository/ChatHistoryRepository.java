package cn.javaquan.app.core.chat.repository;

import com.baomidou.mybatisplus.extension.service.IService;
import cn.javaquan.common.base.message.BasePage;
import cn.javaquan.common.base.message.PageResult;
import cn.javaquan.app.core.chat.entity.ChatHistoryPO;

/**
 * 聊天记录表.
 *
 * @author javaquan
 * @since 1.0.0
 */
public interface ChatHistoryRepository extends IService<ChatHistoryPO> {

    /**
     * 分页查询.
     * @param po 查询参数
     * @param basePage 分页参数
     * @return 查询结果
     */
    PageResult<ChatHistoryPO> page(ChatHistoryPO po, BasePage basePage);

}
