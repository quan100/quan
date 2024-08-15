package cn.javaquan.app.mobile.bff.news.feign;

import cn.javaquan.app.mobile.bff.news.vo.NewsVo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.List;
import java.util.Map;

/**
 * 新闻查询.
 *
 * @author wangquan
 * @since 1.0.0
 */
@RequiredArgsConstructor
@Component
public class HotSearchFeignFactory {

    private final Map<String, IHotSearchService> serviceMap;

    /**
     * 获取热搜服务.
     * @param newsType 热搜类型
     * @return 热搜服务
     */
    private IHotSearchService getService(String newsType) {
        IHotSearchService hotSearchFeign = this.serviceMap.get(IHotSearchService.KEY + newsType);
        if (null == hotSearchFeign) {
            return null;
        }
        return hotSearchFeign;
    }

    /**
     * 获取热搜内容.
     * @param newsType 热搜类型
     * @param offset 偏移量
     * @return 热搜内容
     */
    public List<NewsVo> getNews(String newsType, Integer offset) {
        IHotSearchService abstractHotSearchFeign = this.getService(newsType);
        if (null == abstractHotSearchFeign) {
            return Collections.emptyList();
        }
        return abstractHotSearchFeign.getNews(newsType, offset);
    }

}
