package cn.javaquan.app.core.chat.repository.impl;

import cn.javaquan.app.core.convert.PageAssembler;
import cn.javaquan.app.core.convert.PageResultAssembler;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import cn.javaquan.common.base.message.BasePage;
import cn.javaquan.common.base.message.PageResult;
import cn.javaquan.app.core.chat.entity.ChatGroupInfoPO;
import cn.javaquan.app.core.chat.mapper.ChatGroupInfoMapper;
import cn.javaquan.app.core.chat.repository.ChatGroupInfoRepository;
import org.springframework.stereotype.Repository;

/**
 * 群组信息
 *
 * @author JavaQuan
 * @version 1.0.0
 */
@Repository
public class ChatGroupInfoRepositoryImpl extends ServiceImpl<ChatGroupInfoMapper, ChatGroupInfoPO> implements ChatGroupInfoRepository {

    @Override
    public PageResult<ChatGroupInfoPO> page(ChatGroupInfoPO po, BasePage basePage) {
        Page<ChatGroupInfoPO> page = PageAssembler.INSTANCE.toPage(basePage);
        LambdaQueryWrapper<ChatGroupInfoPO> queryWrapper = Wrappers.lambdaQuery(po);
        page = this.page(page, queryWrapper);
        return PageResultAssembler.INSTANCE.toPageResult(page);
    }

}
