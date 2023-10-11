package com.quan.app.mobile.bff.news.feign;

import java.util.List;

public interface IHotSearchService {

    String KEY = "IHotSearchService_";

    /**
     * 获取热搜内容
     *
     * @return
     */
    List getNews(String newsType);

    /**
     * 获取热搜内容
     *
     * @param newsType 新闻类型
     * @param offset   分页
     * @return
     */
    List getNews(String newsType, Integer offset);
}
