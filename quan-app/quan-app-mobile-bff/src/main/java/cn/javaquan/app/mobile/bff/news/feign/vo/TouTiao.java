package cn.javaquan.app.mobile.bff.news.feign.vo;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
public class TouTiao implements Serializable {

    private static final long serialVersionUID = 4148223317303367956L;

    public final static String SUCCESS = "success";

    private List<ContentData> data;
    private List<FixedTopData> fixed_top_data;
    private String fixed_top_style;
    private String impr_id;
    private String status;

    @Data
    public static class ContentData {

        @JsonProperty("ClusterId")
        private Long ClusterId;

        @JsonProperty("Title")
        private String Title;

        @JsonProperty("Label")
        private String Label;

        /**
         * 热度值
         */
        @JsonProperty("HotValue")
        private String HotValue;
        @JsonProperty("ClusterIdStr")
        private String ClusterIdStr;
        @JsonProperty("ClusterType")
        private Integer ClusterType;
        @JsonProperty("QueryWord")
        private String QueryWord;
        @JsonProperty("LabelDesc")
        private String LabelDesc;
    }

    @Data
    public static class FixedTopData {
        @JsonProperty("Id")
        private Long Id;
        @JsonProperty("Title")
        private String Title;
        @JsonProperty("Url")
        private String Url;
    }

    public boolean success() {
        return SUCCESS.equals(status);
    }
}
