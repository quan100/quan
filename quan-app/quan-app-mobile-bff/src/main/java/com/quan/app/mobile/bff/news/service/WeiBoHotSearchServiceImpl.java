package com.quan.app.mobile.bff.news.service;

import com.quan.app.common.util.Validate;
import com.quan.common.base.message.Result;
import com.quan.app.mobile.bff.news.feign.AbstractHotSearchService;
import com.quan.app.mobile.bff.news.feign.IHotSearchService;
import com.quan.app.mobile.bff.news.feign.WeiboHotSearchFeign;
import com.quan.app.mobile.bff.news.feign.vo.WeiBo;
import com.quan.app.mobile.bff.news.vo.NewsVo;
import com.quan.app.mobile.bff.news.vo.NewsVoMapping;
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
@Service(IHotSearchService.KEY + "weibo")
public class WeiBoHotSearchServiceImpl extends AbstractHotSearchService implements IHotSearchService {
    private final WeiboHotSearchFeign hotSearchFeign;

    @Override
    public List getNews(String newsType) {
        return refresh(newsType, 1);
    }

    @Override
    public List refresh(String newsType, Integer offset) {
        Result<WeiBo> result = hotSearchFeign.hot_band();
        WeiBo weiBo = result.getData();
        if (null == weiBo) {
            return Collections.emptyList();
        }
        NewsVo topData = NewsVoMapping.INSTANCE.toNewsVo(WeiboHotSearchFeign.SEARCH_URL, weiBo.getHotgov());
        List<NewsVo> contentData = NewsVoMapping.INSTANCE.toNewsVoList(WeiboHotSearchFeign.SEARCH_URL, weiBo.getBand_list());
        List<NewsVo> newsVoList = new ArrayList<>();
        if (null != topData) {
            newsVoList.add(topData);
        }
        if (Validate.isNotEmpty(contentData)) {
            newsVoList.addAll(contentData);
        }
        cache(newsType, offset, newsVoList);
        return newsVoList;
    }
}
