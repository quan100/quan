package cn.javaquan.app.mobile.bff.news.service;

import cn.javaquan.app.common.util.Validate;
import cn.javaquan.app.mobile.bff.news.feign.AbstractHotSearchService;
import cn.javaquan.app.mobile.bff.news.feign.HotSearchFeignFactory;
import cn.javaquan.tools.redis.service.IRedisService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author wangquan
 */
@RequiredArgsConstructor
@Service
public class NewsService {
    private final IRedisService redisService;
    private final HotSearchFeignFactory hotSearchFeignFactory;

    public List getNews(String newsType, Integer offset) {
        String key = AbstractHotSearchService.getNewsCacheKey(newsType, offset);
        List news = redisService.get(key, List.class);
        if (Validate.isNotEmpty(news)) {
            return news;
        }
        return refresh(newsType, offset);
    }


    public List refresh(String newsType, Integer offset) {
        return hotSearchFeignFactory.getNews(newsType, offset);
    }

}
