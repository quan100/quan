package cn.javaquan.app.service.friendly.service;

import cn.javaquan.app.service.friendly.feign.FriendlyLinkRepositoryFeign;
import cn.javaquan.app.common.module.friendly.FriendlyLinkAddCommand;
import cn.javaquan.app.common.module.friendly.FriendlyLinkDTO;
import cn.javaquan.app.common.module.friendly.FriendlyLinkQuery;
import cn.javaquan.app.common.module.friendly.FriendlyLinkUpdateCommand;
import cn.javaquan.common.base.message.PageResult;
import cn.javaquan.common.base.message.Result;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * 友情链接.
 *
 * @author javaquan
 * @since 1.0.0
 */
@RequiredArgsConstructor
@Component
public class FriendlyLinkService {

    private final FriendlyLinkRepositoryFeign friendlyLinkRepositoryFeign;

    /**
     * 查询列表.
     * @param query 查询参数
     * @return 查询结果
     */
    public Result<PageResult<FriendlyLinkDTO>> page(FriendlyLinkQuery query) {
        return friendlyLinkRepositoryFeign.page(query);
    }

    /**
     * 根据ID查询.
     * @param id 主键
     * @return 查询结果
     */
    public Result<FriendlyLinkDTO> details(Long id) {
        return friendlyLinkRepositoryFeign.details(id);
    }

    /**
     * 根据主键更新.
     * @param cmd 更新指令参数
     * @return 操作是否成功
     */
    public Result<Boolean> update(FriendlyLinkUpdateCommand cmd) {
        return friendlyLinkRepositoryFeign.update(cmd);
    }

    /**
     * 新增.
     * @param cmd 新增指令参数
     * @return 操作是否成功
     */
    public Result<Boolean> save(FriendlyLinkAddCommand cmd) {
        return friendlyLinkRepositoryFeign.save(cmd);
    }

    /**
     * 批量新增.
     * @param cmds 新增参数
     * @return 新增结果
     */
    public Result<Boolean> saveBatch(List<FriendlyLinkAddCommand> cmds) {
        return friendlyLinkRepositoryFeign.saveBatch(cmds);
    }

    /**
     * 删除.
     * @param ids 主键
     * @return 操作是否成功
     */
    public Result<Boolean> deleteByIds(List<Long> ids) {
        return friendlyLinkRepositoryFeign.deleteByIds(ids);
    }

}
