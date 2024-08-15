package cn.javaquan.app.mobile.bff.news.vo;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * 新闻数据展示模型.
 *
 * @author wangquan
 * @since 1.0.0
 */
@Data
public class NewsVo implements Serializable {

    private static final long serialVersionUID = 1642131414014323848L;

    /**
     * 跳转地址.
     */
    private String url;

    /**
     * 标题.
     */
    private String title;

    /**
     * 标题前缀图标.
     */
    private String titleIcon;

    /**
     * 标题前缀图标颜色.
     */
    private String titleIconColor;

    /**
     * 排序.
     */
    private Integer sort;

    /**
     * 标题后缀显示.
     */
    private List<Action> actions;

    /**
     * 展示的样式.
     */
    @Data
    public static class Action {

        /**
         * 数值.
         */
        private Object text;

        /**
         * 图标.
         */
        private String icon;

        /**
         * 颜色.
         */
        private String color;

        /**
         * 默认构造方法.
         */
        public Action() {
        }

        /**
         * 根据数值和图标构造方法.
         * @param text 数值
         * @param icon 图标
         */
        public Action(Object text, String icon) {
            this.text = text;
            this.icon = icon;
        }

        /**
         * 根据数值、图标和颜色构造方法.
         * @param text 数值
         * @param icon 图标
         * @param color 颜色
         */
        public Action(Object text, String icon, String color) {
            this.text = text;
            this.icon = icon;
            this.color = color;
        }

    }

}
