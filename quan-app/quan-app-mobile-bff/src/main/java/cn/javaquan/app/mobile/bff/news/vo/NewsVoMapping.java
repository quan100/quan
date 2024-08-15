package cn.javaquan.app.mobile.bff.news.vo;

import cn.javaquan.app.mobile.bff.news.feign.vo.QQ;
import cn.javaquan.app.mobile.bff.news.feign.vo.TouTiao;
import cn.javaquan.app.mobile.bff.news.feign.vo.WeiBo;
import cn.javaquan.app.common.util.Validate;
import org.mapstruct.Context;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.mapstruct.factory.Mappers;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 新闻数据模型转换器.
 *
 * @author wangquan
 * @since 1.0.0
 */
@Mapper
public interface NewsVoMapping {

    /**
     * 返回给定映射器类型的实例.
     */
    NewsVoMapping INSTANCE = Mappers.getMapper(NewsVoMapping.class);

    // weibo

    /**
     * 微博新闻数据模型转换为通用数据模型.
     * @param searchUrl 搜索的链接配置（url + 参数名=）
     * @param bandList 新闻数据
     * @return 通用数据模型
     */
    @Mapping(target = "url", expression = "java(toUrl(null, searchUrl, realtime.getWord()))")
    @Mapping(target = "title", source = "realtime.note")
    @Mapping(target = "titleIconColor", constant = "#df3e3e")
    @Mapping(target = "actions", expression = "java(toWeiBoAction(realtime))")
    @Mapping(target = "sort", source = "realtime.realpos")
    NewsVo toNewsVo(@Context String searchUrl, WeiBo.BandList bandList);

    /**
     * 微博新闻数据模型转换为通用数据模型.
     * @param baseVo 微博新闻数据
     * @return 通用数据模型
     */
    @Named("toWeiBoAction")
    default List<NewsVo.Action> toWeiBoAction(WeiBo.BaseVo baseVo) {
        List<NewsVo.Action> actions = new ArrayList<>();
        if (null != baseVo.getRaw_hot()) {
            actions.add(new NewsVo.Action(baseVo.getRaw_hot(), "fire"));
        }
        if (null != baseVo.getNum()) {
            actions.add(new NewsVo.Action(baseVo.getNum(), "eye"));
        }
        if (null != baseVo.getIcon_desc()) {
            actions.add(new NewsVo.Action(baseVo.getIcon_desc(), null, baseVo.getIcon_desc_color()));
        }
        return actions;
    }

    /**
     * 微博热搜数据模型转换为通用数据模型.
     * @param searchUrl 搜索的链接配置（url + 参数名=）
     * @param bandList 新闻数据
     * @return 通用数据模型
     */
    List<NewsVo> toNewsVoList(@Context String searchUrl, List<WeiBo.BandList> bandList);

    /**
     * 微博热搜数据模型转换为通用数据模型.
     * @param searchUrl 搜索的链接配置（url + 参数名=）
     * @param hotgov 热搜数据
     * @return 通用数据模型
     */
    @Mapping(target = "url", expression = "java(toUrl(hotgov.getUrl(), searchUrl, hotgov.getWord()))")
    @Mapping(target = "title", source = "hotgov.note")
    @Mapping(target = "titleIcon", constant = "vertical-align-top")
    @Mapping(target = "titleIconColor", source = "hotgov.icon_desc_color")
    @Mapping(target = "actions", expression = "java(toWeiBoAction(hotgov))")
    @Mapping(target = "sort", constant = "0")
    NewsVo toNewsVo(@Context String searchUrl, WeiBo.Hotgov hotgov);

    // 头条

    /**
     * 头条新闻数据模型转换为通用数据模型.
     * @param searchUrl 搜索的链接配置（url + 参数名=）
     * @param data 新闻数据
     * @return 通用数据模型
     */
    @Mapping(target = "url", expression = "java(toUrl(null, searchUrl, data.getClusterIdStr()))")
    @Mapping(target = "title", expression = "java(data.getTitle())")
    @Mapping(target = "actions", expression = "java(toTouTiaoAction(data))")
    NewsVo toNewsVo(@Context String searchUrl, TouTiao.ContentData data);

    /**
     * 头条新闻数据模型转换为通用数据模型.
     * @param data 头条数据
     * @return 通用数据模型
     */
    @Named("toTouTiaoAction")
    default List<NewsVo.Action> toTouTiaoAction(TouTiao.ContentData data) {
        NewsVo.Action action = new NewsVo.Action(data.getHotValue(), "fire");
        return Arrays.asList(action);
    }

    /**
     * 头条新闻数据模型转换为通用数据模型.
     * @param searchUrl 搜索的链接配置（url + 参数名=）
     * @param dataList 头条数据
     * @return 通用数据模型
     */
    List<NewsVo> toTouTiaoNewsVoList(@Context String searchUrl, List<TouTiao.ContentData> dataList);

    /**
     * 头条新闻数据模型转换为通用数据模型.
     * @param searchUrl 搜索的链接配置（url + 参数名=）
     * @param data 头条数据
     * @return 通用数据模型
     */
    @Mapping(target = "url", expression = "java(toUrl(data.getUrl(), searchUrl, String.valueOf(data.getId())))")
    @Mapping(target = "title", expression = "java(data.getTitle())")
    @Mapping(target = "titleIcon", constant = "vertical-align-top")
    @Mapping(target = "titleIconColor", constant = "#f04142")
    @Mapping(target = "sort", constant = "0")
    NewsVo toNewsVo(@Context String searchUrl, TouTiao.FixedTopData data);

    /**
     * 头条新闻数据模型转换为通用数据模型.
     * @param searchUrl 搜索的链接配置（url + 参数名=）
     * @param data 头条数据
     * @return 通用数据模型
     */
    List<NewsVo> toTouTiaoTopNewsVoList(@Context String searchUrl, List<TouTiao.FixedTopData> data);

    // QQ

    /**
     * 腾讯新闻数据模型转换为通用数据模型.
     * @param data 腾讯新闻数据
     * @return 通用数据模型
     */
    @Mapping(target = "sort", source = "ranking")
    @Mapping(target = "actions", expression = "java(toQQAction(data))")
    NewsVo toNewsVo(QQ.Content data);

    /**
     * 腾讯新闻数据模型转换为通用数据模型.
     * @param data 腾讯新闻数据
     * @return 通用数据模型
     */
    List<NewsVo> toQQNewsVoList(List<QQ.Content> data);

    /**
     * 腾讯新闻数据模型转换为通用数据模型.
     * @param data 腾讯新闻数据
     * @return 通用数据模型
     */
    @Named("toQQAction")
    default List<NewsVo.Action> toQQAction(QQ.Content data) {
        List<NewsVo.Action> actions = new ArrayList<>();
        QQ.HotEvent hotEvent = data.getHotEvent();
        if (null != hotEvent) {
            actions.add(new NewsVo.Action(hotEvent.getHotScore(), "fire"));
        }
        if (null != data.getReadCount()) {
            actions.add(new NewsVo.Action(data.getReadCount(), "eye"));
        }
        return actions;
    }

    /**
     * 新闻跳转链接转换.
     * @param url 原始链接
     * @param searchUrl 搜索的链接配置（url + 参数名=）
     * @param wordScheme url参数
     * @return 新闻链接
     */
    @Named("toUrl")
    default String toUrl(String url, String searchUrl, String wordScheme) {
        if (Validate.isBlank(url)) {
            String searchParam;
            try {
                searchParam = URLEncoder.encode(wordScheme, "UTF-8");
            }
            catch (UnsupportedEncodingException ex) {
                ex.printStackTrace();
                searchParam = wordScheme;
            }
            StringBuffer sb = new StringBuffer(searchUrl).append(searchParam);
            return sb.toString();
        }
        return url;
    }

}
