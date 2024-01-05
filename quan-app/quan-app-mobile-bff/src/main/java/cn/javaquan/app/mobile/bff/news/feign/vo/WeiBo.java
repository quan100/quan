package cn.javaquan.app.mobile.bff.news.feign.vo;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.util.List;

@Data
public class WeiBo implements Serializable {

    private static final long serialVersionUID = 391539402789524013L;

    private Hotgov hotgov;
    //    private List<Realtime> realtime;
    private List<BandList> band_list;

    @EqualsAndHashCode(callSuper=false)
    @Data
    public static class Hotgov extends BaseVo {
//        private String small_icon_desc_color;
        private Integer is_hot;
        private Integer is_gov;
//        private String note;
        private String name;
//        private String icon_desc_color;
//        private String icon_desc;
//        private Integer topic_flag;
        private String word;
        private String url;
//        private String small_icon_desc;
    }

    @Data
    public static class BaseVo {
        private String small_icon_desc_color;
        private String note;
        private String icon_desc_color;
        private String icon_desc;
        private Integer topic_flag;
        private String small_icon_desc;

        private Long raw_hot;
        private Long num;
    }

    @EqualsAndHashCode(callSuper=false)
    @Data
    public static class BandList extends BaseVo {
        private String label_name;
//        private String icon_desc_color;
        private Integer is_new;
        private Integer fun_word;
        private String word_scheme;
//        private String small_icon_desc_color;
        private String category;
//        private Integer topic_flag;
        private Integer expand;
        private String emoticon;
        private Integer discuss_roomid;
        private Integer dynamic_fei;
        private Integer realpos;
        private String subject_querys;
        private String channel_type;
        private Integer flag;
//        private String small_icon_desc;
        private String word;
//        private Long raw_hot;
        private Long onboard_time;
//        private Long num;
//        private String note;
//        private String icon_desc;
        private Integer rank;
    }

//    @Data
//    public static class Realtime {
//        private String small_icon_desc_color;
//        private String category;
//        private Integer expand;
//        private Integer realpos;
//        private Long raw_hot;
//        private String word_scheme;
//        private Long num;
//        private String emoticon;
//        private String word;
//        private String note;
//        private String icon_desc_color;
//        private String channel_type;
//        private String icon_desc;
//        private String small_icon_desc;
//        private String rank;
//    }
}
