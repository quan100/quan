package com.quan.app.pm.bff.command;

import com.quan.common.base.constant.TopicEnum;
import com.quan.app.pm.bff.command.service.SitemapService;
import com.quan.tools.jms.JmsUtil;
import lombok.RequiredArgsConstructor;
import org.dom4j.Document;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("/system/command/")
public class CommandController {

    private final JmsUtil jmsUtil;
    private final SitemapService sitemapService;

    @GetMapping("refresh/gateway/cache")
    public void refreshGatewayCache() {
        jmsUtil.send(TopicEnum.ROLE_AUTHORIZATION, true);
    }

    @GetMapping("refresh/sitemap")
    public ResponseEntity<byte[]> refreshSitemap() {
        Document document = sitemapService.refreshSitemap();
        return sitemapService.sitemap(document);
    }
}
