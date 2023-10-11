package com.quan.app.pm.bff.command.convert;

import com.quan.app.common.module.article.ArticleDTO;
import com.quan.app.common.module.system.UserPermissionDTO;
import com.quan.app.common.module.system.UserPermissionTreeDTO;
import com.quan.app.common.util.LocalDateUtils;
import com.quan.tools.sitemap.Sitemap;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Mapper
public interface SitemapAssembler {
    SitemapAssembler INSTANCE = Mappers.getMapper(SitemapAssembler.class);

    String TAG = ":";

    default List<Sitemap.Url> toSitemapList(String siteDomainUrl, List<UserPermissionTreeDTO> dtos, List<UserPermissionTreeDTO> filterDtos) {
        if (CollectionUtils.isEmpty(dtos)) {
            return Collections.emptyList();
        }
        Map<String, UserPermissionDTO> dtoParams = dtos.stream().filter(dto -> {
            String path = dto.getPath();
            if (StringUtils.isEmpty(path)) {
                return false;
            }
            if (path.contains(TAG)) {
                filterDtos.add(dto);
                return false;
            }
            return true;
        }).collect(Collectors.toMap(UserPermissionDTO::getPath, (p1) -> p1, (p1, p2) -> p2));
        List<Sitemap.Url> urls = dtoParams.entrySet().stream().map(entry -> {
            StringBuffer sb = new StringBuffer();
            sb.append(siteDomainUrl).append(entry.getKey());
            return Sitemap.createUrl(sb.toString(), LocalDateUtils.getCurDateTime(LocalDateUtils.STANDARD_DATE_FORMAT));
        }).collect(Collectors.toList());
        return urls;
    }

    default Sitemap.Url toSitemap(String siteDomainUrl, String articlePath, ArticleDTO dto) {
        StringBuffer sb = new StringBuffer();
        sb.append(siteDomainUrl).append(articlePath.split(TAG)[0]).append(dto.getArticleId());
        return Sitemap.createUrl(sb.toString(), LocalDateUtils.getCurDateTime(LocalDateUtils.STANDARD_DATE_FORMAT));
    }
}
