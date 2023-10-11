package com.quan.app.mobile.bff.news.feign;

import com.quan.app.common.util.Validate;
import com.quan.app.mobile.bff.news.vo.NewsVo;
import com.quan.tools.redis.service.CacheUtil;

import java.util.List;

public abstract class AbstractHotSearchService implements IHotSearchService {

    @Override
    public List getNews(String newsType) {
        return refresh(newsType, 1);
    }

    @Override
    public List getNews(String newsType, Integer offset) {
        return refresh(newsType, offset);
    }

    abstract public List refresh(String newsType, Integer offset);

    protected void cache(String newsType, Integer offset, List<NewsVo> newsVoList) {
        cache(newsType, offset, newsVoList, false, 0);
    }

    protected void cache(String newsType, Integer offset, List<NewsVo> newsVoList, boolean sortConfig) {
        cache(newsType, offset, newsVoList, sortConfig, 0);
    }

    /**
     * @param newsType
     * @param offset
     * @param newsVoList
     * @param sortConfig
     * @param index      下标计算增量
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

    public static String getNewsCacheKey(String newsType, Integer offset) {
        StringBuffer sb = new StringBuffer("news_").append(newsType).append("_").append(offset);
        return sb.toString();
    }
}
