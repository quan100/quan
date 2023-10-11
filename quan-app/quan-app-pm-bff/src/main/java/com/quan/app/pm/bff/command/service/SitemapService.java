package com.quan.app.pm.bff.command.service;

import com.quan.app.common.module.article.ArticleDTO;
import com.quan.app.common.module.auth.convert.AuthAssembler;
import com.quan.app.common.module.system.UserPermissionDTO;
import com.quan.app.common.module.system.UserPermissionTreeDTO;
import com.quan.app.common.util.Validate;
import com.quan.common.base.constant.AppTypeEnum;
import com.quan.common.base.message.Result;
import com.quan.app.pm.bff.command.convert.SitemapAssembler;
import com.quan.app.pm.bff.command.feign.ArticleSitemapFeign;
import com.quan.app.pm.bff.command.feign.PermissionFeign;
import com.quan.tools.sitemap.Sitemap;
import com.quan.tools.sitemap.SitemapUtil;
import com.quan.tools.sitemap.XmlUtil;
import lombok.RequiredArgsConstructor;
import org.dom4j.Document;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author wangquan
 */
@RequiredArgsConstructor
@Service
public class SitemapService {

    private final PermissionFeign permissionFeign;
    private final ArticleSitemapFeign articleSitemapFeign;

    private final static String ARTICLE_CONTENT_NAME = "article_content";

    @Value("${quan.site.domain.url:}")
    private String siteDomainUrl;

    public Document refreshSitemap() {
        Sitemap sitemap = new Sitemap();
        List<UserPermissionTreeDTO> filterDtos = new ArrayList<>();

        // Open角色权限菜单路径
        Result<List<UserPermissionTreeDTO>> permissionResult = permissionFeign.getUserPermission(AuthAssembler.INSTANCE.appType(AppTypeEnum.CLIENT_BFF.name()));
        List<Sitemap.Url> urls = SitemapAssembler.INSTANCE.toSitemapList(siteDomainUrl, permissionResult.getData(), filterDtos);

        // 文章路径
        Map<String, UserPermissionTreeDTO> dtoMap = filterDtos.stream().collect(Collectors.toMap(UserPermissionDTO::getName, (p1) -> p1));
        if (dtoMap.containsKey(ARTICLE_CONTENT_NAME)) {
            // 所有的文章路径
            Result<List<ArticleDTO>> result = articleSitemapFeign.getSitemaps();
            UserPermissionDTO dto = dtoMap.get(ARTICLE_CONTENT_NAME);
            String articlePath = dto.getPath();
            if (Validate.isNotEmpty(result.getData())) {
                List<Sitemap.Url> articleUrl = result.getData().stream().map(article -> {
                    return SitemapAssembler.INSTANCE.toSitemap(siteDomainUrl, articlePath, article);
                }).collect(Collectors.toList());
                urls.addAll(articleUrl);
            }
        }

        sitemap.setUrl(urls);
        return SitemapUtil.createSiteMap(sitemap);
    }

    public ResponseEntity<byte[]> sitemap(Document document) {
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        XmlUtil.write(bos, document);

        byte[] content = bos.toByteArray();
        HttpHeaders headers = new HttpHeaders();
        try {
            headers.setContentDispositionFormData("attachment", "sitemap.xml");
            headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ResponseEntity<>(content, headers, HttpStatus.CREATED);
    }
}
