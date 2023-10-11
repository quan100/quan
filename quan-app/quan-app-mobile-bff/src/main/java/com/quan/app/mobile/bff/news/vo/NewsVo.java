package com.quan.app.mobile.bff.news.vo;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * @author wangquan
 */
@Data
public class NewsVo implements Serializable {

    private static final long serialVersionUID = 1642131414014323848L;

    /**
     * 跳转地址
     */
    private String url;

    /**
     * 标题
     */
    private String title;

    /**
     * 标题前缀图标
     */
    private String titleIcon;

    /**
     * 标题前缀图标颜色
     */
    private String titleIconColor;

    /**
     * 排序
     */
    private Integer sort;

    /**
     * 标题后缀显示
     */
    private List<Action> actions;

    @Data
    public static class Action {

        /**
         * 数值
         */
        private Object text;

        /**
         * 图标
         */
        private String icon;

        /**
         * 颜色
         */
        private String color;

        public Action() {
        }

        public Action(Object text, String icon) {
            this.text = text;
            this.icon = icon;
        }

        public Action(Object text, String icon, String color) {
            this.text = text;
            this.icon = icon;
            this.color = color;
        }
    }
}
