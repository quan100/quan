package cn.javaquan.app.pm.bff.command.convert;

import cn.javaquan.app.common.module.article.ArticleDTO;
import cn.javaquan.app.common.module.system.UserPermissionDTO;
import cn.javaquan.app.common.module.system.UserPermissionTreeDTO;
import cn.javaquan.app.common.util.LocalDateUtils;
import cn.javaquan.app.common.util.Validate;
import cn.javaquan.tools.sitemap.Sitemap;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import org.springframework.util.CollectionUtils;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * 站点地图数据转换器.
 *
 * @author javaquan
 * @since 1.0.0
 */
@Mapper
public interface SitemapAssembler {

    /**
     * 返回给定映射器类型的实例.
     */
    SitemapAssembler INSTANCE = Mappers.getMapper(SitemapAssembler.class);

    /**
     * 权限分隔标识符.
     */
    String TAG = ":";

    /**
     * 转换为站点地图数据.
     * @param siteDomainUrl 站点域名
     * @param dtos 用户权限列表
     * @param filterDtos 过滤的权限列表，仅用于文章链接的数据
     * @return 站点地图配置数据
     */
    default List<Sitemap.Url> toSitemapList(String siteDomainUrl, List<UserPermissionTreeDTO> dtos,
            List<UserPermissionTreeDTO> filterDtos) {
        if (CollectionUtils.isEmpty(dtos)) {
            return Collections.emptyList();
        }
        Map<String, UserPermissionDTO> dtoParams = dtos.stream().filter(dto -> {
            String path = dto.getPath();
            if (Validate.isBlank(path)) {
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

    /**
     * 转换为站点地图配置.
     * @param siteDomainUrl 站点域名
     * @param articlePath 文章路径
     * @param dto 文章数据
     * @return 站点地图配置数据
     */
    default Sitemap.Url toSitemap(String siteDomainUrl, String articlePath, ArticleDTO dto) {
        StringBuffer sb = new StringBuffer();
        sb.append(siteDomainUrl).append(articlePath.split(TAG)[0]).append(dto.getArticleId());
        return Sitemap.createUrl(sb.toString(), LocalDateUtils.getCurDateTime(LocalDateUtils.STANDARD_DATE_FORMAT));
    }

}
