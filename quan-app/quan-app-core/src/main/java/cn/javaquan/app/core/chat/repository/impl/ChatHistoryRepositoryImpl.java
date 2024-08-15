package cn.javaquan.app.core.chat.repository.impl;

import cn.javaquan.app.core.convert.PageAssembler;
import cn.javaquan.app.core.convert.PageResultAssembler;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import cn.javaquan.common.base.message.BasePage;
import cn.javaquan.common.base.message.PageResult;
import cn.javaquan.app.core.chat.entity.ChatHistoryPO;
import cn.javaquan.app.core.chat.mapper.ChatHistoryMapper;
import cn.javaquan.app.core.chat.repository.ChatHistoryRepository;
import org.springframework.stereotype.Repository;

/**
 * 聊天记录表.
 *
 * @author javaquan
 * @since 1.0.0
 */
@Repository
public class ChatHistoryRepositoryImpl extends ServiceImpl<ChatHistoryMapper, ChatHistoryPO>
        implements ChatHistoryRepository {

    @Override
    public PageResult<ChatHistoryPO> page(ChatHistoryPO po, BasePage basePage) {
        Page<ChatHistoryPO> page = PageAssembler.INSTANCE.toPage(basePage);
        LambdaQueryWrapper<ChatHistoryPO> queryWrapper = Wrappers.lambdaQuery(po);
        page = this.page(page, queryWrapper);
        return PageResultAssembler.INSTANCE.toPageResult(page);
    }

}
