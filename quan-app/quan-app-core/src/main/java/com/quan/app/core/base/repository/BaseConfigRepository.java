package com.quan.app.core.base.repository;

import com.quan.common.base.message.PageResult;
import com.baomidou.mybatisplus.extension.service.IService;
import com.quan.common.base.message.BasePage;
import com.quan.app.core.base.entity.BaseConfigPO;

/**
 * 系统通用配置
 *
 * @author JavaQuan
 * @version 1.0.0
 * @date 2023-04-04 10:38:39
 */
public interface BaseConfigRepository extends IService<BaseConfigPO> {

    /**
     * 分页查询
     * <p>
     * 当有排序参数 sort 时，优先根据sort 升序，然后根据创建时间降序
     *
     * @param po
     * @param basePage
     * @return
     */
    PageResult<BaseConfigPO> page(BaseConfigPO po, BasePage basePage);

}

