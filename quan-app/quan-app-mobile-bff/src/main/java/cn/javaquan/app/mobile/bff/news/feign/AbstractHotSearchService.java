package cn.javaquan.app.mobile.bff.news.feign;

import cn.javaquan.app.common.util.Validate;
import cn.javaquan.app.mobile.bff.news.vo.NewsVo;
import cn.javaquan.tools.redis.service.CacheUtil;

import java.util.List;

/**
 * 获取热点文章.
 *
 * @author javaquan
 * @since 1.0.0
 */
public abstract class AbstractHotSearchService implements IHotSearchService {

    @Override
    public List<NewsVo> getNews(String newsType) {
        return refresh(newsType, 1);
    }

    @Override
    public List<NewsVo> getNews(String newsType, Integer offset) {
        return refresh(newsType, offset);
    }

    /**
     * 刷新热点文章.
     * @param newsType 新闻类型
     * @param offset 偏移量
     * @return 刷新后的数据
     */
    public abstract List<NewsVo> refresh(String newsType, Integer offset);

    /**
     * 缓存文章数据.
     * @param newsType 新闻类型
     * @param offset 偏移量
     * @param newsVoList 新闻数据
     */
    protected void cache(String newsType, Integer offset, List<NewsVo> newsVoList) {
        cache(newsType, offset, newsVoList, false, 0);
    }

    /**
     * 缓存文章数据.
     * @param newsType 新闻类型
     * @param offset 偏移量
     * @param newsVoList 新闻数据
     * @param sortConfig 是否设置排序
     */
    protected void cache(String newsType, Integer offset, List<NewsVo> newsVoList, boolean sortConfig) {
        cache(newsType, offset, newsVoList, sortConfig, 0);
    }

    /**
     * 缓存新闻数据.
     * @param newsType 新闻类型
     * @param offset 偏移量
     * @param newsVoList 新闻数据
     * @param sortConfig 是否设置排序
     * @param index 下标计算增量
     */
    public void cache(String newsType, Integer offset, List<NewsVo> newsVoList, boolean sortConfig, int index) {
        if (Validate.isEmpty(newsVoList)) {
            return;
        }
        if (sortConfig) {
            for (int i = 0; i < newsVoList.size(); i++) {
                newsVoList.get(i).setSort(i + index);
            }
        }
        String key = getNewsCacheKey(newsType, offset);
        CacheUtil.set(key, newsVoList, 1800L);
    }

    /**
     * 获取缓存的新闻数据.
     * @param newsType 新闻类型
     * @param offset 偏移量
     * @return 缓存数据
     */
    public static String getNewsCacheKey(String newsType, Integer offset) {
        StringBuffer sb = new StringBuffer("news_").append(newsType).append("_").append(offset);
        return sb.toString();
    }

}
