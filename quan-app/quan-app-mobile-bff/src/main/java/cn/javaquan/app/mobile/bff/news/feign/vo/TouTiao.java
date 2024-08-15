package cn.javaquan.app.mobile.bff.news.feign.vo;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * 头条新闻.
 *
 * @author javaquan
 * @since 1.0.0
 */
@Data
public class TouTiao implements Serializable {

    private static final long serialVersionUID = 4148223317303367956L;

    /**
     * 成功状态.
     */
    public static final String SUCCESS = "success";

    /**
     * 数据.
     */
    private List<ContentData> data;

    /**
     * 置顶数据.
     */
    private List<FixedTopData> fixed_top_data;

    /**
     * 置顶样式.
     */
    private String fixed_top_style;

    /**
     * impr_id.
     */
    private String impr_id;

    /**
     * 状态.
     */
    private String status;

    /**
     * 成功状态.
     * @return true 成功
     */
    public boolean success() {
        return SUCCESS.equals(this.status);
    }

    /**
     * 内容数据.
     */
    @Data
    public static class ContentData {

        /**
         * 集群id.
         */
        @JsonProperty("ClusterId")
        private Long ClusterId;

        /**
         * 标题.
         */
        @JsonProperty("Title")
        private String Title;

        /**
         * 标签.
         */
        @JsonProperty("Label")
        private String Label;

        /**
         * 热度值.
         */
        @JsonProperty("HotValue")
        private String HotValue;

        /**
         * 集群id 字符串.
         */
        @JsonProperty("ClusterIdStr")
        private String ClusterIdStr;

        /**
         * 集群类型.
         */
        @JsonProperty("ClusterType")
        private Integer ClusterType;

        /**
         * 查询关键词.
         */
        @JsonProperty("QueryWord")
        private String QueryWord;

        /**
         * 标签描述.
         */
        @JsonProperty("LabelDesc")
        private String LabelDesc;

    }

    /**
     * 置顶数据.
     */
    @Data
    public static class FixedTopData {

        /**
         * id.
         */
        @JsonProperty("Id")
        private Long Id;

        /**
         * 标题.
         */
        @JsonProperty("Title")
        private String Title;

        /**
         * 链接.
         */
        @JsonProperty("Url")
        private String Url;

    }

}
