package cn.javaquan.app.mobile.bff.news.service;

import cn.javaquan.app.common.util.Validate;
import cn.javaquan.app.mobile.bff.news.feign.AbstractHotSearchService;
import cn.javaquan.app.mobile.bff.news.feign.IHotSearchService;
import cn.javaquan.app.mobile.bff.news.feign.QQHotSearchFeign;
import cn.javaquan.app.mobile.bff.news.feign.vo.QQ;
import cn.javaquan.app.mobile.bff.news.vo.NewsVo;
import cn.javaquan.app.mobile.bff.news.vo.NewsVoMapping;
import cn.javaquan.tools.redis.service.CacheUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

/**
 * 获取微博热搜.
 *
 * @author wangquan
 * @since 1.0.0
 */
@RequiredArgsConstructor
@Service(IHotSearchService.KEY + "qq")
public class QQHotSearchServiceImpl extends AbstractHotSearchService implements IHotSearchService {

    private final QQHotSearchFeign hotSearchFeign;

    private static final int MAX_OFFSET = 5;

    private static final String IDS_HASH = "news_qq:ids_hash";

    @Override
    public List<NewsVo> getNews(String newsType, Integer offset) {
        if (offset > MAX_OFFSET) {
            offset = 1;
        }
        offset = (offset - 1) * 10;
        String key = getNewsCacheKey(newsType, offset);
        List<NewsVo> news = CacheUtil.get(key, List.class);
        if (Validate.isNotEmpty(news)) {
            return news;
        }
        return refresh(newsType, offset);
    }

    @Override
    public List<NewsVo> refresh(String newsType, Integer offset) {
        String idsHash = CacheUtil.get(IDS_HASH);
        QQ qq = hotSearchFeign.getNews(hotSearchFeign.buildParams(offset, 10, idsHash));
        if (null == qq) {
            return Collections.emptyList();
        }
        List<QQ.Idlist> idlists = qq.getIdlist();
        if (Validate.isEmpty(idlists)) {
            return Collections.emptyList();
        }
        QQ.Idlist idlist = idlists.get(0);
        List<QQ.Content> newslist = idlist.getNewslist();
        List<NewsVo> newsVoList = NewsVoMapping.INSTANCE.toQQNewsVoList(newslist);
        cache(newsType, offset, newsVoList);
        if (Validate.isNotBlank(idlist.getIds_hash())) {
            CacheUtil.set(IDS_HASH, idlist.getIds_hash());
        }
        return newsVoList;
    }

}
