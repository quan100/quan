package cn.javaquan.app.mobile.bff.news.service;

import cn.javaquan.app.mobile.bff.news.feign.vo.TouTiao;
import cn.javaquan.app.common.util.Validate;
import cn.javaquan.app.mobile.bff.news.feign.AbstractHotSearchService;
import cn.javaquan.app.mobile.bff.news.feign.IHotSearchService;
import cn.javaquan.app.mobile.bff.news.feign.TouTiaoHotSearchFeign;
import cn.javaquan.app.mobile.bff.news.vo.NewsVo;
import cn.javaquan.app.mobile.bff.news.vo.NewsVoMapping;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * 获取微博热搜
 *
 * @author wangquan
 */
@RequiredArgsConstructor
@Service(IHotSearchService.KEY + "toutiao")
public class TouTiaoHotSearchServiceImpl extends AbstractHotSearchService implements IHotSearchService {
    private final TouTiaoHotSearchFeign hotSearchFeign;

    @Override
    public List getNews(String newsType) {
        return refresh(newsType, 1);
    }

    @Override
    public List refresh(String newsType, Integer offset) {
        TouTiao touTiao = hotSearchFeign.getNews();
        if (!touTiao.success()) {
            return Collections.emptyList();
        }
        List<NewsVo> topData = NewsVoMapping.INSTANCE.toTouTiaoTopNewsVoList(TouTiaoHotSearchFeign.SEARCH_URL, touTiao.getFixed_top_data());
        List<NewsVo> contentData = NewsVoMapping.INSTANCE.toTouTiaoNewsVoList(TouTiaoHotSearchFeign.SEARCH_URL, touTiao.getData());
        List<NewsVo> newsVoList = new ArrayList<>();
        if (Validate.isNotEmpty(topData)) {
            newsVoList.addAll(topData);
        }
        if (Validate.isNotEmpty(contentData)) {
            newsVoList.addAll(contentData);
        }
        cache(newsType, offset, newsVoList, true);
        return newsVoList;
    }
}
