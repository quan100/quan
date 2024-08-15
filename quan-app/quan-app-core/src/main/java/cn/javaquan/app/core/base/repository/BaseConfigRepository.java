package cn.javaquan.app.core.base.repository;

import cn.javaquan.common.base.message.PageResult;
import com.baomidou.mybatisplus.extension.service.IService;
import cn.javaquan.common.base.message.BasePage;
import cn.javaquan.app.core.base.entity.BaseConfigPO;

/**
 * 系统通用配置.
 *
 * @author javaquan
 * @since 1.0.0
 */
public interface BaseConfigRepository extends IService<BaseConfigPO> {

    /**
     * 分页查询.
     * <p>
     * 当有排序参数 sort 时，优先根据sort 升序，然后根据创建时间降序
     * @param po 查询参数
     * @param basePage 分页参数
     * @return 查询结果
     */
    PageResult<BaseConfigPO> page(BaseConfigPO po, BasePage basePage);

}
