package cn.javaquan.cloud.gateway.auth.consumer;

import cn.javaquan.common.base.constant.TopicEnum;
import cn.javaquan.cloud.gateway.auth.filter.AuthFilterFactory;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

/**
 * 角色授权消息监听
 *
 * @author wangquan
 */
@Slf4j
@RequiredArgsConstructor
@Component
public class RoleConsumer {

    private final AuthFilterFactory authFilterFactory;

    @JmsListener(destination = TopicEnum.ROLE_AUTHORIZATION)
    public void roleAuthorizationListener(Boolean updateRole) {
        log.info("****** 接收刷新网关权限指令 ******");
        if (updateRole) {
            authFilterFactory.setFilterChainMap(true);
            log.info("****** 刷新网关权限完成 ******");
        }
    }
}
