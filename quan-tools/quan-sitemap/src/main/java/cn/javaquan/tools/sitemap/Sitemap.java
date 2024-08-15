package cn.javaquan.tools.sitemap;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * 站点地图配置.
 *
 * @author quan.icu
 * @since 1.0.0
 */
@Data
public class Sitemap implements Serializable {

    private static final long serialVersionUID = 2362938635695104142L;

    /**
     * urlset用来标记整个文档的开头. 必选
     */
    public static final String URLSET = "urlset";

    /**
     * url地址的开始和结束. 最多出现无限次.
     */
    public static final String URL = "url";

    /**
     * 标示数据更新频率. 有效值为：always、hourly、daily、weekly、monthly、yearly、never 可选
     */
    public static final String CHANGEFREQ = "changefreq";

    /**
     * 标示数据最新一次更新时间. 日期 时间格式为yyyy-mm-dd 可选
     */
    public static final String LASTMOD = "lastmod";

    /**
     * 标示该条数据的存放地址 url 最小长度1个字符， 最大长度256个字符，以"http://"开头. 必选
     */
    public static final String LOC = "loc";

    /**
     * 标示优先值 小数 [0.0 1.0]（大于等于0小于等于1，保留一位小数）. 可选
     */
    public static final String PRIORITY = "priority";

    /**
     * 默认数据更新频率.
     */
    public static final String DEFAULT_CHANGEFREQ = "daily";

    /**
     * 默认优先值 小数 [0.0 1.0]（大于等于0小于等于1，保留一位小数）.
     */
    public static final String DEFAULT_PRIORITY = "0.8";

    /**
     * url地址的开始和结束. 最多出现无限次. 必选
     */
    private List<Url> url;

    /**
     * 创建url地址.
     * @param loc 标示该条数据的存放地址 url
     * @return 创建的Url实例
     */
    public static Url createUrl(String loc) {
        return createUrl(loc, null);
    }

    /**
     * 创建url地址.
     * @param loc 标示该条数据的存放地址 url
     * @param lastmod 标示数据最新一次更新时间
     * @return 创建的Url实例
     */
    public static Url createUrl(String loc, String lastmod) {
        return createUrl(loc, lastmod, DEFAULT_PRIORITY);
    }

    /**
     * 创建url地址.
     * @param loc 标示该条数据的存放地址 url
     * @param lastmod 标示数据最新一次更新时间
     * @param priority 标示优先值
     * @return 创建的Url实例
     */
    public static Url createUrl(String loc, String lastmod, String priority) {
        return createUrl(loc, lastmod, DEFAULT_CHANGEFREQ, priority);
    }

    /**
     * 创建url地址.
     * @param loc 标示该条数据的存放地址 url
     * @param lastmod 标示数据最新一次更新时间
     * @param changefreq 标示数据更新频率
     * @param priority 标示优先值
     * @return 创建的Url实例
     */
    public static Url createUrl(String loc, String lastmod, String changefreq, String priority) {
        return new Url(loc, lastmod, changefreq, priority);
    }

    /**
     * url地址对象.
     */
    @Data
    public static class Url {

        /**
         * 标示该条数据的存放地址 url 最小长度1个字符， 最大长度256个字符，以"http://"开头. 必选
         */
        private String loc;

        /**
         * 标示数据最新一次更新时间. 时间格式为yyyy-mm-dd 可选
         */
        private String lastmod;

        /**
         * 标示数据更新频率. 有效值为：always、hourly、daily、weekly、monthly、yearly、never 可选
         */
        private String changefreq;

        /**
         * 标示优先值 小数 [0.0 1.0]（大于等于0小于等于1，保留一位小数）. 可选
         */
        private String priority;

        /**
         * 创建url地址.
         * @param loc 标示该条数据的存放地址 url
         */
        public Url(String loc) {
            this(loc, null);
        }

        /**
         * 创建url地址.
         * @param loc 标示该条数据的存放地址 url
         * @param lastmod 标示数据最新一次更新时间
         */
        public Url(String loc, String lastmod) {
            this(loc, lastmod, null);
        }

        /**
         * 创建url地址.
         * @param loc 标示该条数据的存放地址 url
         * @param lastmod 标示数据最新一次更新时间
         * @param priority 标示优先值
         */
        public Url(String loc, String lastmod, String priority) {
            this(loc, lastmod, DEFAULT_CHANGEFREQ, priority);
        }

        /**
         * 创建url地址.
         * @param loc 标示该条数据的存放地址 url
         * @param lastmod 标示数据最新一次更新时间
         * @param changefreq 标示数据更新频率
         * @param priority 标示优先值
         */
        public Url(String loc, String lastmod, String changefreq, String priority) {
            this.loc = loc;
            this.lastmod = lastmod;
            this.changefreq = changefreq;
            this.priority = priority;
        }

    }

}
