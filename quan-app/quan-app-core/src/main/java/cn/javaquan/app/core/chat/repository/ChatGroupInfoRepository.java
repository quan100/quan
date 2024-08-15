package cn.javaquan.app.core.chat.repository;

import com.baomidou.mybatisplus.extension.service.IService;
import cn.javaquan.common.base.message.BasePage;
import cn.javaquan.common.base.message.PageResult;
import cn.javaquan.app.core.chat.entity.ChatGroupInfoPO;

/**
 * 群组信息.
 *
 * @author javaquan
 * @since 1.0.0
 */
public interface ChatGroupInfoRepository extends IService<ChatGroupInfoPO> {

    /**
     * 分页查询.
     * @param po 查询参数
     * @param basePage 分页参数
     * @return 查询结果
     */
    PageResult<ChatGroupInfoPO> page(ChatGroupInfoPO po, BasePage basePage);

}
