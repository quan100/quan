package cn.javaquan.app.mobile.bff.news.feign;

import cn.javaquan.app.mobile.bff.news.feign.vo.TouTiao;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * 今日头条热搜.
 *
 * @author wangquan
 * @since 1.0.0
 */
@FeignClient(name = "toutiao-hot-search", url = "https://www.toutiao.com")
public interface TouTiaoHotSearchFeign {

    /**
     * 头条新闻链接.
     */
    String SEARCH_URL = "https://www.toutiao.com/trending/";

    /**
     * 获取热搜内容.
     * @return 头条新闻
     */
    @GetMapping("/hot-event/hot-board/?origin=toutiao_pc")
    TouTiao getNews();

}
