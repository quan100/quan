package cn.javaquan.app.core.friendly.repository;

import cn.javaquan.common.base.message.PageResult;
import com.baomidou.mybatisplus.extension.service.IService;
import cn.javaquan.common.base.message.BasePage;
import cn.javaquan.app.core.friendly.entity.FriendlyLinkPO;

/**
 * 友情链接.
 *
 * @author javaquan
 * @since 1.0.0
 */
public interface FriendlyLinkRepository extends IService<FriendlyLinkPO> {

    /**
     * 分页查询.
     * <p>
     * 当有排序参数 sort 时，优先根据sort 升序，然后根据创建时间降序
     * @param po 查询参数
     * @param basePage 分页参数
     * @return 查询结果
     */
    PageResult<FriendlyLinkPO> page(FriendlyLinkPO po, BasePage basePage);

}
