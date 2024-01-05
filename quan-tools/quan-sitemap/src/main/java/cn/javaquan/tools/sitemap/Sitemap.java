package cn.javaquan.tools.sitemap;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * @author quan.icu
 */
@Data
public class Sitemap implements Serializable {

    private static final long serialVersionUID = 2362938635695104142L;

    /**
     * urlset用来标记整个文档的开头
     * 必选
     **/
    public final static String URLSET = "urlset";

    /**
     * url地址的开始和结束 最多出现无限次
     */
    public final static String URL = "url";

    /**
     * 标示数据更新频率
     * 有效值为：always、hourly、daily、weekly、monthly、yearly、never
     * 可选
     **/
    public final static String CHANGEFREQ = "changefreq";

    /**
     * 标示数据最新一次更新时间
     * 日期
     * 时间格式为yyyy-mm-dd
     * 可选
     **/
    public final static String LASTMOD = "lastmod";

    /**
     * 标示该条数据的存放地址
     * url 	最小长度1个字符，
     * 最大长度256个字符，以"http://"开头
     * 必选
     **/
    public final static String LOC = "loc";

    /**
     * 标示优先值
     * 小数 	[0.0 1.0]（大于等于0小于等于1，保留一位小数）
     * 例如：< priority >0.8< priority >
     * 可选
     **/
    public final static String PRIORITY = "priority";

    public final static String DEFAULT_CHANGEFREQ = "daily";
    public final static String DEFAULT_PRIORITY = "0.8";

    /**
     * url地址的开始和结束
     * 最多出现无限次
     * 必选
     **/
    private List<Url> url;

    public static Url createUrl(String loc) {
        return createUrl(loc, null);
    }

    public static Url createUrl(String loc, String lastmod) {
        return createUrl(loc, lastmod, DEFAULT_PRIORITY);
    }

    public static Url createUrl(String loc, String lastmod, String priority) {
        return createUrl(loc, lastmod, DEFAULT_CHANGEFREQ, priority);
    }

    public static Url createUrl(String loc, String lastmod, String changefreq, String priority) {
        return new Url(loc, lastmod, changefreq, priority);
    }

    @Data
    public static class Url {

        /**
         * 标示该条数据的存放地址
         * url 	最小长度1个字符，
         * 最大长度256个字符，以"http://"开头
         * 必选
         **/
        private String loc;

        /**
         * 标示数据最新一次更新时间
         * 日期
         * 时间格式为yyyy-mm-dd
         * 可选
         **/
        private String lastmod;

        /**
         * 标示数据更新频率
         * 有效值为：always、hourly、daily、weekly、monthly、yearly、never
         * 可选
         **/
        private String changefreq;

        /**
         * 标示优先值
         * 小数 	[0.0 1.0]（大于等于0小于等于1，保留一位小数）
         * 例如：< priority >0.8< priority >
         * 可选
         **/
        private String priority;

        public Url(String loc) {
            this(loc, null);
        }

        public Url(String loc, String lastmod) {
            this(loc, lastmod, null);
        }

        public Url(String loc, String lastmod, String priority) {
            this(loc, lastmod, DEFAULT_CHANGEFREQ, priority);
        }

        public Url(String loc, String lastmod, String changefreq, String priority) {
            this.loc = loc;
            this.lastmod = lastmod;
            this.changefreq = changefreq;
            this.priority = priority;
        }
    }

}
