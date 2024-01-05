package cn.javaquan.app.core.chat.repository.impl;

import cn.javaquan.app.core.convert.PageAssembler;
import cn.javaquan.app.core.convert.PageResultAssembler;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import cn.javaquan.common.base.message.BasePage;
import cn.javaquan.common.base.message.PageResult;
import cn.javaquan.app.core.chat.entity.ChatUserFriendPO;
import cn.javaquan.app.core.chat.mapper.ChatUserFriendMapper;
import cn.javaquan.app.core.chat.repository.ChatUserFriendRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 用户好友信息关联表
 *
 * @author JavaQuan
 * @version 1.0.0
 */
@Repository
public class ChatUserFriendRepositoryImpl extends ServiceImpl<ChatUserFriendMapper, ChatUserFriendPO> implements ChatUserFriendRepository {

    @Override
    public PageResult<ChatUserFriendPO> page(ChatUserFriendPO po, BasePage basePage) {
        Page<ChatUserFriendPO> page = PageAssembler.INSTANCE.toPage(basePage);
        LambdaQueryWrapper<ChatUserFriendPO> queryWrapper = Wrappers.lambdaQuery(po);
        page = this.page(page, queryWrapper);
        return PageResultAssembler.INSTANCE.toPageResult(page);
    }

    @Override
    public List<ChatUserFriendPO> queryByUserId(String userId) {
        LambdaQueryWrapper<ChatUserFriendPO> queryWrapper = Wrappers.lambdaQuery();
        queryWrapper.eq(ChatUserFriendPO::getUserId, userId);
        return this.list(queryWrapper);
    }

}
