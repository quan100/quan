package com.quan.app.mobile.bff.news.feign.vo;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

/*

{
    "id": "20221228A08SHX00",
    "a_ver": "00",
    "articletype": "0",
    "title": "再过三天，每个月房贷或能省1700！你能省多少，帮你算好了",
    "chlid": "5206106",
    "commentid": "8112763643",
    "longtitle": "再过三天，每个月房贷或能省1700！你能省多少，帮你算好了",
    "surl": "https://view.inews.qq.com/a/20221228A08SHX00?#",
    "short_url": "https://view.inews.qq.com/a/20221228A08SHX00?#",
    "url": "https://view.inews.qq.com/a/20221228A08SHX00?#",
    "time": "2022-12-28 21:43:13",
    "timestamp": 1672234993,
    "abstract": "随着12月20日最新一期LPR数据出炉，今年的5年期以上LPR最终定格为4.3%。    我们为何要关注这个数字？因为自2020年改革之后，我们的房贷利率不再锚定基准利率，而是与LPR（贷款市场报价利率）直接挂钩，确切地说是与5年期以上LPR直接挂钩，因为绝大多数房贷年限都在5年以上。  至于如何挂钩，其实也不复杂，那就是参照上一...",
    "disableDeclare": 1,
    "comments": 26,
    "alg_version": 1,
    "card": {
        "chlid": "5206106",
        "chlname": "极目新闻",
        "desc": "楚天都市报极目新闻由全国报业十强楚天都市报融合发展而来，立足“全球眼、中国心、新闻塔、思想家”，专注推送全球热点要闻、国内焦点事件，以及本地原创重磅报道、精彩观点，服务全球网友。",
        "icon": "http://inews.gtimg.com/newsapp_ls/0/13313835343_200200/0",
        "sicon": "http://inews.gtimg.com/newsapp_ls/0/13313835343_200200/0",
        "uin": "ec304f4513bb7aec09f9921be4e9fdbe12",
        "update_frequency": "1672240634",
        "vip_desc": "楚天都市报官方账号",
        "vip_icon_night": "http://inews.gtimg.com/newsapp_ls/0/14876049528/0",
        "vip_place": "left",
        "vip_type": "30013",
        "vip_icon": "http://inews.gtimg.com/newsapp_ls/0/14876049251/0",
        "medal_info": {
            "type_id": 31,
            "medal_id": 45,
            "medal_level": 1,
            "medal_name": "别具慧眼",
            "medal_desc": "成为荐评手，一起建设腾讯新闻社区！",
            "night_url": "https://new.inews.gtimg.com/tnews/8c3d00f9/7f36/8c3d00f9-7f36-44a9-82c4-be357b54fe9b.png",
            "daytime_url": "https://new.inews.gtimg.com/tnews/45af77fe/a73d/45af77fe-a73d-42d2-ab80-feaa383611de.png"
        },
        "vip_type_new": "30013",
        "suid": "8QMd23pY5IUbvz7Q",
        "liveInfo": {},
        "cpLevel": 1
    },
    "chlmrk": "楚天都市报极目新闻由全国报业十强楚天都市报融合发展而来，立足“全球眼、中国心、新闻塔、思想家”，专注推送全球热点要闻、国内焦点事件，以及本地原创重磅报道、精彩观点，服务全球网友。",
    "chlsicon": "http://inews.gtimg.com/newsapp_ls/0/13313835343_200200/0",
    "media_id": "5206106",
    "chlname": "极目新闻",
    "source": "极目新闻",
    "chlicon": "http://inews.gtimg.com/newsapp_ls/0/13313835343_200200/0",
    "qualityScore": "3",
    "uinnick": "极目新闻",
    "uinname": "5206106",
    "tag": [
        ""
    ],
    "commentGifSwitch": 1,
    "forbidCommentUpDown": 1,
    "forbidExpr": 1,
    "emojiRelatedSwitch": 1,
    "emojiSwitch": 1,
    "show_expr": 1,
    "showType_video": "normal",
    "picShowType": 126,
    "thumbnails": [
        "http://inews.gtimg.com/newsapp_ls/0/15581535965_150120/0"
    ],
    "thumbnails_big": [
        "http://inews.gtimg.com/newsapp_ls/0/15581535965_640330/0"
    ],
    "bigImage": [
        "http://inews.gtimg.com/newsapp_ls/0/15581535965_640330/0"
    ],
    "thumbnails_qqnews_photo": [
        "http://inews.gtimg.com/newsapp_ls/0/15581535965_870492/0"
    ],
    "thumbnails_qqnews": [
        "http://inews.gtimg.com/newsapp_ls/0/15581535965_294195/0"
    ],
    "newsModule": {},
    "labelList": [
        {
            "color": "#999999",
            "nightColor": "#696969",
            "textColor": "#999999",
            "textNightColor": "#696969",
            "word": "极目新闻",
            "type": 1,
            "typeName": "source"
        }
    ],
    "readCount": 31944,
    "shareUrl": "https://view.inews.qq.com/a/20221228A08SHX00?#",
    "likeInfo": 55,
    "commentNum": 26,
    "gifRelatedSwitch": 1,
    "commentSyncWeibo": 1,
    "tmp3pic": [
        "http://inews.gtimg.com/newsapp_ls/0/15581535965_870492/0"
    ],
    "miniProShareImage": "http://inews.gtimg.com/newsapp_ls/0/15581535965_870492/0",
    "textShareType": "1",
    "enableDiffusion": 1,
    "NewsSource": "1:",
    "hotEvent": {
        "id": "20221228A08SHX00",
        "ranking": 2,
        "title": "再过三天，每个月房贷或能省1700",
        "hotScore": 1637895
    },
    "ranking": 2,
    "fimgUrl": {
        "ExplicitImageUrl": "http://inews.gtimg.com/newsapp_ls/0/15581540116_1035582/0"
    },
    "nlpAbstract": "随着12月20日最新一期LPR数据出炉，今年的5年期以上LPR最终定格为4.3%。幸运的是，今年LPR降了，而且降幅还不小，那么从下个月开始房贷利率也会跟着降。为便于计算，选择的房贷样本今年利率5.35%，明年利率5%。",
    "nlpContentAbstract": "随着12月20日最新一期LPR数据出炉，今年的5年期以上LPR最终定格为4.3%。我们为何要关注这个数字？",
    "extra_property": {},
    "userAddress": "湖北"
}

 */
@Data
public class QQ implements Serializable {

    private static final long serialVersionUID = -825473983614811954L;

    private List<Idlist> idlist;

    @Data
    public static class Idlist {
        private String ids_hash;
        private Integer has_more;
        private List<Content> newslist;
    }


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


        private String id;// ": "20221228A08SHX00",
        private String a_ver;// ": "00",
        private String articletype;// ": "0",
        private String chlid;// ": "5206106",
        private String commentid;// ": "8112763643",
        private String longtitle;// ": "再过三天，每个月房贷或能省1700！你能省多少，帮你算好了",
        private String surl;// ": "https://view.inews.qq.com/a/20221228A08SHX00?#",
        private String short_url;// ": "https://view.inews.qq.com/a/20221228A08SHX00?#",
        private String time;// ": "2022-12-28 21:43:13",
        private Long timestamp;// ": 1672234993,
        //        private String abstract        ;// ": "随着12月20日最新一期LPR数据出炉，今年的5年期以上LPR最终定格为4.3%。    我们为何要关注这个数字？因为自2020年改革之后，我们的房贷利率不再锚定基准利率，而是与LPR（贷款市场报价利率）直接挂钩，确切地说是与5年期以上LPR直接挂钩，因为绝大多数房贷年限都在5年以上。  至于如何挂钩，其实也不复杂，那就是参照上一...",
        private Long comments;// ": 26,
        private Long readCount;
        private String shareUrl;
        private Integer likeInfo;
        private Integer commentNum;
        private HotEvent hotEvent;
        private Integer ranking;
    }

    @Data
    public static class HotEvent {
        private String id;
        private Integer ranking;
        private Long hotScore;
    }
}
