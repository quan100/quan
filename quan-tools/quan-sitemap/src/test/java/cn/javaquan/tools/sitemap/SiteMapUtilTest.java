package cn.javaquan.tools.sitemap;

import org.dom4j.Document;

import java.util.Arrays;

public class SiteMapUtilTest {

    public static void main(String[] args) {
        Sitemap siteMap = new Sitemap();
        siteMap.setUrl(
                Arrays.asList(
                        Sitemap.createUrl("http://www.baidu.com/", "2022-03-04"),
                        Sitemap.createUrl("http://www.baidu.com/1234")
                )
        );
        Document document = SitemapUtil.createSiteMap(siteMap);
        XmlUtil.prettyPrint(document);
    }
}
