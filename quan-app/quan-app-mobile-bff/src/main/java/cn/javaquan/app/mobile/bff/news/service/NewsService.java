package cn.javaquan.app.mobile.bff.news.service;

import cn.javaquan.app.common.util.Validate;
import cn.javaquan.app.mobile.bff.news.feign.AbstractHotSearchService;
import cn.javaquan.app.mobile.bff.news.feign.HotSearchFeignFactory;
import cn.javaquan.app.mobile.bff.news.vo.NewsVo;
import cn.javaquan.tools.redis.service.IRedisService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 新闻服务业务实现.
 *
 * @author wangquan
 * @since 1.0.0
 */
@RequiredArgsConstructor
@Service
public class NewsService {

    private final IRedisService redisService;

    private final HotSearchFeignFactory hotSearchFeignFactory;

    /**
     * 获取新闻数据.
     * @param newsType 新闻类型
     * @param offset 偏移量
     * @return 新闻数据
     */
    public List<?> getNews(String newsType, Integer offset) {
        String key = AbstractHotSearchService.getNewsCacheKey(newsType, offset);
        List<?> news = redisService.get(key, List.class);
        if (Validate.isNotEmpty(news)) {
            return news;
        }
        return refresh(newsType, offset);
    }

    /**
     * 刷新新闻数据.
     * @param newsType 新闻类型
     * @param offset 偏移量
     * @return 新闻数据
     */
    public List<NewsVo> refresh(String newsType, Integer offset) {
        return hotSearchFeignFactory.getNews(newsType, offset);
    }

}
