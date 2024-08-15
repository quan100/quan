package cn.javaquan.app.mobile.bff.news.feign.vo;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * 腾讯新闻.
 *
 * @author javaquan
 * @since 1.0.0
 */
@Data
public class QQ implements Serializable {

    private static final long serialVersionUID = -825473983614811954L;

    /**
     * 腾讯新闻数据.
     */
    private List<Idlist> idlist;

    /**
     * 腾讯新闻数据模型.
     */
    @Data
    public static class Idlist {

        private String ids_hash;

        private Integer has_more;

        private List<Content> newslist;

    }

    /**
     * 腾讯新闻数据模型.
     */
    @Data
    public static class Content {

        private String cms_id;

        private String title;

        private String subtitle;

        private String url;

        private String category_id;

        private String category_name;

        private String category_cn;

        private String media_id;

        private String media_name;

        private String point;

        private String article_id;

        private String comment_id;

        private String comment_num;

        private String id; // ": "20221228A08SHX00",

        private String a_ver; // ": "00",

        private String articletype; // ": "0",

        private String chlid; // ": "5206106",

        private String commentid; // ": "8112763643",

        private String longtitle; // ": "再过三天，每个月房贷或能省1700！你能省多少，帮你算好了",

        private String surl; // ": "https://view.inews.qq.com/a/20221228A08SHX00?#",

        private String short_url; // ": "https://view.inews.qq.com/a/20221228A08SHX00?#",

        private String time; // ": "2022-12-28 21:43:13",

        private Long timestamp; // ": 1672234993,

        // private String abstract ; // ": "随着12月20日最新一期LPR数据出炉，今年的5年期以上LPR最终定格为4.3%。
        // 我们为何要关注这个数字？因为自2020年改革之后，我们的房贷利率不再锚定基准利率，而是与LPR（贷款市场报价利率）直接挂钩，确切地说是与5年期以上LPR直接挂钩，因为绝大多数房贷年限都在5年以上。
        // 至于如何挂钩，其实也不复杂，那就是参照上一...",
        private Long comments; // ": 26,

        private Long readCount;

        private String shareUrl;

        private Integer likeInfo;

        private Integer commentNum;

        private HotEvent hotEvent;

        private Integer ranking;

    }

    /**
     * 热搜事件.
     */
    @Data
    public static class HotEvent {

        private String id;

        private Integer ranking;

        private Long hotScore;

    }

}
