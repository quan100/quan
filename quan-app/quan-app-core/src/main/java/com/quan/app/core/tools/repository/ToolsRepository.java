package com.quan.app.core.tools.repository;

import com.quan.common.base.message.PageResult;
import com.baomidou.mybatisplus.extension.service.IService;
import com.quan.app.common.module.tools.ToolsQuery;
import com.quan.common.base.message.BasePage;
import com.quan.app.core.tools.entity.ToolsPO;

/**
 * 工具
 *
 * @author JavaQuan
 * @version 1.0.0
 * @date 2023-04-14 16:43:32
 */
public interface ToolsRepository extends IService<ToolsPO> {

    /**
     * 分页查询
     * <p>
     * 当有排序参数 sort 时，优先根据sort 升序，然后根据创建时间降序
     *
     * @param po
     * @param basePage
     * @return
     */
    PageResult<ToolsPO> page(ToolsPO po, BasePage basePage);

    /**
     * 获取工具列表
     *
     * @param query
     * @return
     */
    PageResult<ToolsPO> getTools(ToolsQuery query);


}

