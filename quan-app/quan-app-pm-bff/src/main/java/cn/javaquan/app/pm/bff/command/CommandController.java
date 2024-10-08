package cn.javaquan.app.pm.bff.command;

import cn.javaquan.app.pm.bff.command.service.SitemapService;
import cn.javaquan.common.base.constant.TopicEnum;
import cn.javaquan.tools.jms.JmsUtil;
import lombok.RequiredArgsConstructor;
import org.dom4j.Document;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 系统操作指令接口.
 *
 * @author javaquan
 * @since 1.0.0
 */
@RequiredArgsConstructor
@RestController
@RequestMapping("/system/command/")
public class CommandController {

    private final JmsUtil jmsUtil;

    private final SitemapService sitemapService;

    /**
     * 刷新网关权限缓存.
     */
    @GetMapping("refresh/gateway/cache")
    public void refreshGatewayCache() {
        jmsUtil.send(TopicEnum.ROLE_AUTHORIZATION, true);
    }

    /**
     * 刷新站点地图配置.
     * @return 站点地图配置文件数据流
     */
    @GetMapping("refresh/sitemap")
    public ResponseEntity<byte[]> refreshSitemap() {
        Document document = sitemapService.refreshSitemap();
        return sitemapService.sitemap(document);
    }

}
