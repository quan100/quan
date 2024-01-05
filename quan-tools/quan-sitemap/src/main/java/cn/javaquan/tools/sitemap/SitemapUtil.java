package cn.javaquan.tools.sitemap;

import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;

import java.util.List;

/**
 * @author quan.icu
 */
public class SitemapUtil {

    /**
     * 创建一个站点地图
     *
     * @param sitemap
     * @return
     */
    public static Document createSiteMap(Sitemap sitemap) {
        Document document = DocumentHelper.createDocument();
        Element root = document.addElement(Sitemap.URLSET);

        List<Sitemap.Url> urls = sitemap.getUrl();

        urls.stream().forEach(url -> {
            Element element = root.addElement(Sitemap.URL);
            addAttribute(Sitemap.LOC, url.getLoc(), element);
            addAttribute(Sitemap.LASTMOD, url.getLastmod(), element);
            addAttribute(Sitemap.CHANGEFREQ, url.getChangefreq(), element);
            addAttribute(Sitemap.PRIORITY, url.getPriority(), element);
        });
        return document;
    }

    private static void addAttribute(String name, String value, Element element) {
        if (null != value) {
            element.addElement(name).addText(value);
        }
    }
}
