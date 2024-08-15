package cn.javaquan.app.core.system.repository;

import cn.javaquan.common.base.message.PageResult;
import com.baomidou.mybatisplus.extension.service.IService;
import cn.javaquan.common.base.message.BasePage;
import cn.javaquan.app.core.system.entity.SysUserInfoPO;

/**
 * 用户信息.
 *
 * @author javaquan
 * @since 1.0.0
 */
public interface SysUserInfoRepository extends IService<SysUserInfoPO> {

    /**
     * 分页查询.
     * <p>
     * 当有排序参数 sort 时，优先根据sort 升序，然后根据创建时间降序
     * @param po 查询参数
     * @param basePage 分页参数
     * @return 查询结果
     */
    PageResult<SysUserInfoPO> page(SysUserInfoPO po, BasePage basePage);

    /**
     * 根据用户ID查询用户信息.
     * @param userId 用户ID
     * @return 用户信息
     */
    SysUserInfoPO getUserInfo(String userId);

}
