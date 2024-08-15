package cn.javaquan.app.pm.bff.command.service;

import cn.javaquan.app.pm.bff.command.convert.SitemapAssembler;
import cn.javaquan.app.common.module.article.ArticleDTO;
import cn.javaquan.app.common.module.auth.convert.AuthAssembler;
import cn.javaquan.app.common.module.system.UserPermissionDTO;
import cn.javaquan.app.common.module.system.UserPermissionTreeDTO;
import cn.javaquan.app.common.util.Validate;
import cn.javaquan.common.base.constant.AppTypeEnum;
import cn.javaquan.common.base.message.Result;
import cn.javaquan.app.pm.bff.command.feign.ArticleSitemapFeign;
import cn.javaquan.app.pm.bff.command.feign.PermissionFeign;
import cn.javaquan.tools.sitemap.Sitemap;
import cn.javaquan.tools.sitemap.SitemapUtil;
import cn.javaquan.tools.sitemap.XmlUtil;
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
 * 站点地图业务实现.
 *
 * @author wangquan
 * @since 1.0.0
 */
@RequiredArgsConstructor
@Service
public class SitemapService {

    private final PermissionFeign permissionFeign;

    private final ArticleSitemapFeign articleSitemapFeign;

    private static final String ARTICLE_CONTENT_NAME = "article_content";

    @Value("${quan.site.domain.url:}")
    private String siteDomainUrl;

    /**
     * 刷新站点地图.
     * @return 站点地图数据
     */
    public Document refreshSitemap() {
        Sitemap sitemap = new Sitemap();
        List<UserPermissionTreeDTO> filterDtos = new ArrayList<>();

        // Open角色权限菜单路径
        Result<List<UserPermissionTreeDTO>> permissionResult = permissionFeign
            .getUserPermission(AuthAssembler.INSTANCE.appType(AppTypeEnum.CLIENT_BFF.name()));
        List<Sitemap.Url> urls = SitemapAssembler.INSTANCE.toSitemapList(siteDomainUrl, permissionResult.getData(),
                filterDtos);

        // 文章路径
        Map<String, UserPermissionTreeDTO> dtoMap = filterDtos.stream()
            .collect(Collectors.toMap(UserPermissionDTO::getName, (p1) -> p1));
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

    /**
     * 生成站点地图.
     * @param document 站点地图数据
     * @return 站点地图文件数据流
     */
    public ResponseEntity<byte[]> sitemap(Document document) {
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        XmlUtil.write(bos, document);

        byte[] content = bos.toByteArray();
        HttpHeaders headers = new HttpHeaders();
        try {
            headers.setContentDispositionFormData("attachment", "sitemap.xml");
            headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
        return new ResponseEntity<>(content, headers, HttpStatus.CREATED);
    }

}
