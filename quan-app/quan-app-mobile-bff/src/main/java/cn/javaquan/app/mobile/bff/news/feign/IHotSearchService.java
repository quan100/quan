package cn.javaquan.app.mobile.bff.news.feign;

import java.util.List;

import cn.javaquan.app.mobile.bff.news.vo.NewsVo;

/**
 * 提供获取热搜内容的接口.
 * <p>
 * 该接口主要用于扩展第三方新闻内容，依赖第三方新闻类型及内容更新
 *
 * @author javaquan
 * @since 1.0.0
 */
public interface IHotSearchService {

    /**
     * 提供统一的业务服务名称前缀.
     */
    String KEY = "IHotSearchService_";

    /**
     * 获取热搜内容.
     * @param newsType 新闻类型
     * @return 热搜内容
     */
    List<NewsVo> getNews(String newsType);

    /**
     * 获取热搜内容.
     * @param newsType 新闻类型
     * @param offset 分页
     * @return 热搜内容
     */
    List<NewsVo> getNews(String newsType, Integer offset);

}
