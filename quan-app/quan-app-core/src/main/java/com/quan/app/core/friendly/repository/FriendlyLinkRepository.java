package com.quan.app.core.friendly.repository;

import com.quan.common.base.message.PageResult;
import com.baomidou.mybatisplus.extension.service.IService;
import com.quan.common.base.message.BasePage;
import com.quan.app.core.friendly.entity.FriendlyLinkPO;

/**
 * 友情链接
 *
 * @author JavaQuan
 * @version 1.0.0
 * @date 2023-04-04 10:53:59
 */
public interface FriendlyLinkRepository extends IService<FriendlyLinkPO> {

    /**
     * 分页查询
     * <p>
     * 当有排序参数 sort 时，优先根据sort 升序，然后根据创建时间降序
     *
     * @param po
     * @param basePage
     * @return
     */
    PageResult<FriendlyLinkPO> page(FriendlyLinkPO po, BasePage basePage);

}

