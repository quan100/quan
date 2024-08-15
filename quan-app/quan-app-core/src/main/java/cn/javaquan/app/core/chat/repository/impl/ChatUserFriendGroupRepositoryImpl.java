package cn.javaquan.app.core.chat.repository.impl;

import cn.javaquan.app.core.chat.repository.ChatUserFriendGroupRepository;
import cn.javaquan.app.core.convert.PageAssembler;
import cn.javaquan.app.core.convert.PageResultAssembler;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import cn.javaquan.common.base.message.BasePage;
import cn.javaquan.common.base.message.PageResult;
import cn.javaquan.app.core.chat.entity.ChatUserFriendGroupPO;
import cn.javaquan.app.core.chat.mapper.ChatUserFriendGroupMapper;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 用户好友分组表.
 *
 * @author javaquan
 * @since 1.0.0
 */
@Repository
public class ChatUserFriendGroupRepositoryImpl extends ServiceImpl<ChatUserFriendGroupMapper, ChatUserFriendGroupPO>
        implements ChatUserFriendGroupRepository {

    @Override
    public PageResult<ChatUserFriendGroupPO> page(ChatUserFriendGroupPO po, BasePage basePage) {
        Page<ChatUserFriendGroupPO> page = PageAssembler.INSTANCE.toPage(basePage);
        LambdaQueryWrapper<ChatUserFriendGroupPO> queryWrapper = Wrappers.lambdaQuery(po);
        page = this.page(page, queryWrapper);
        return PageResultAssembler.INSTANCE.toPageResult(page);
    }

    @Override
    public List<ChatUserFriendGroupPO> queryByUserId(String userId) {
        LambdaQueryWrapper<ChatUserFriendGroupPO> queryWrapper = Wrappers.lambdaQuery();
        queryWrapper.eq(ChatUserFriendGroupPO::getUserId, userId);
        return this.list(queryWrapper);
    }

}
