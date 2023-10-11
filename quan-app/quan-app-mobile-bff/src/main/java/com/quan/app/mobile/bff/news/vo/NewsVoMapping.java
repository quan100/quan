package com.quan.app.mobile.bff.news.vo;

import com.quan.app.common.util.Validate;
import com.quan.app.mobile.bff.news.feign.vo.QQ;
import com.quan.app.mobile.bff.news.feign.vo.TouTiao;
import com.quan.app.mobile.bff.news.feign.vo.WeiBo;
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
 * @author wangquan
 */
@Mapper
public interface NewsVoMapping {

    NewsVoMapping INSTANCE = Mappers.getMapper(NewsVoMapping.class);

    // weibo

    @Mapping(target = "url", expression = "java(toUrl(null, searchUrl, realtime.getWord()))")
    @Mapping(target = "title", source = "realtime.note")
    @Mapping(target = "titleIconColor", constant = "#df3e3e")
    @Mapping(target = "actions", expression = "java(toWeiBoAction(realtime))")
    @Mapping(target = "sort", source = "realtime.realpos")
    NewsVo toNewsVo(@Context String searchUrl, WeiBo.BandList realtime);

    @Named("toWeiBoAction")
    default List<NewsVo.Action> toWeiBoAction(WeiBo.BaseVo realtime) {
        List<NewsVo.Action> actions = new ArrayList<>();
        if (null != realtime.getRaw_hot()) {
            actions.add(new NewsVo.Action(realtime.getRaw_hot(), "fire"));
        }
        if (null != realtime.getNum()) {
            actions.add(new NewsVo.Action(realtime.getNum(), "eye"));
        }
        if (null != realtime.getIcon_desc()) {
            actions.add(new NewsVo.Action(realtime.getIcon_desc(), null, realtime.getIcon_desc_color()));
        }
        return actions;
    }

    List<NewsVo> toNewsVoList(@Context String searchUrl, List<WeiBo.BandList> realtimes);

    @Mapping(target = "url", expression = "java(toUrl(realtime.getUrl(), searchUrl, realtime.getWord()))")
    @Mapping(target = "title", source = "realtime.note")
    @Mapping(target = "titleIcon", constant = "vertical-align-top")
    @Mapping(target = "titleIconColor", source = "realtime.icon_desc_color")
    @Mapping(target = "actions", expression = "java(toWeiBoAction(realtime))")
    @Mapping(target = "sort", constant = "0")
    NewsVo toNewsVo(@Context String searchUrl, WeiBo.Hotgov realtime);

    // 头条

    @Mapping(target = "url", expression = "java(toUrl(null, searchUrl, data.getClusterIdStr()))")
    @Mapping(target = "title", expression = "java(data.getTitle())")
    @Mapping(target = "actions", expression = "java(toTouTiaoAction(data))")
    NewsVo toNewsVo(@Context String searchUrl, TouTiao.ContentData data);

    @Named("toTouTiaoAction")
    default List<NewsVo.Action> toTouTiaoAction(TouTiao.ContentData data) {
        NewsVo.Action action = new NewsVo.Action(data.getHotValue(), "fire");
        return Arrays.asList(action);
    }

    List<NewsVo> toTouTiaoNewsVoList(@Context String searchUrl, List<TouTiao.ContentData> dataList);

    @Mapping(target = "url", expression = "java(toUrl(data.getUrl(), searchUrl, String.valueOf(data.getId())))")
    @Mapping(target = "title", expression = "java(data.getTitle())")
    @Mapping(target = "titleIcon", constant = "vertical-align-top")
    @Mapping(target = "titleIconColor", constant = "#f04142")
    @Mapping(target = "sort", constant = "0")
    NewsVo toNewsVo(@Context String searchUrl, TouTiao.FixedTopData data);

    List<NewsVo> toTouTiaoTopNewsVoList(@Context String searchUrl, List<TouTiao.FixedTopData> data);

    // QQ
    @Mapping(target = "sort", source = "ranking")
    @Mapping(target = "actions", expression = "java(toQQAction(data))")
    NewsVo toNewsVo(QQ.Content data);

    List<NewsVo> toQQNewsVoList(List<QQ.Content> data);

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
     * @param url
     * @param searchUrl  搜索的地址配置（url + 参数名=）
     * @param wordScheme
     * @return
     */
    @Named("toUrl")
    default String toUrl(String url, String searchUrl, String wordScheme) {
        if (Validate.isBlank(url)) {
            String searchParam;
            try {
                searchParam = URLEncoder.encode(wordScheme, "UTF-8");
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
                searchParam = wordScheme;
            }
            StringBuffer sb = new StringBuffer(searchUrl).append(searchParam);
            return sb.toString();
        }
        return url;
    }

}
