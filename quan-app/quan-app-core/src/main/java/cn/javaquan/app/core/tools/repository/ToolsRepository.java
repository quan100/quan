package cn.javaquan.app.core.tools.repository;

import cn.javaquan.common.base.message.PageResult;
import com.baomidou.mybatisplus.extension.service.IService;
import cn.javaquan.app.common.module.tools.ToolsQuery;
import cn.javaquan.common.base.message.BasePage;
import cn.javaquan.app.core.tools.entity.ToolsPO;

/**
 * 工具.
 *
 * @author javaquan
 * @since 1.0.0
 */
public interface ToolsRepository extends IService<ToolsPO> {

    /**
     * 分页查询.
     * <p>
     * 当有排序参数 sort 时，优先根据sort 升序，然后根据创建时间降序
     * @param po 查询参数
     * @param basePage 分页参数
     * @return 查询结果
     */
    PageResult<ToolsPO> page(ToolsPO po, BasePage basePage);

    /**
     * 获取工具列表.
     * @param query 查询参数
     * @return 查询参数
     */
    PageResult<ToolsPO> getTools(ToolsQuery query);

}
