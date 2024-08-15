package cn.javaquan.cloud.gateway.auth.consumer;

import cn.javaquan.common.base.constant.TopicEnum;
import cn.javaquan.cloud.gateway.auth.filter.AuthFilterFactory;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

/**
 * 角色授权消息监听.
 *
 * @author wangquan
 * @since 1.0.0
 */
@Slf4j
@RequiredArgsConstructor
@Component
public class RoleConsumer {

    private final AuthFilterFactory authFilterFactory;

    /**
     * 角色授权消息监听.
     * <p>
     * 当角色发生变化时，刷新网关权限
     * @param updateRole 角色是否更新
     */
    @JmsListener(destination = TopicEnum.ROLE_AUTHORIZATION)
    public void roleAuthorizationListener(Boolean updateRole) {
        log.info("****** 接收刷新网关权限指令 ******");
        if (updateRole) {
            authFilterFactory.setFilterChainMap(true);
            log.info("****** 刷新网关权限完成 ******");
        }
    }

}
