package cn.javaquan.app.mobile.bff.news.feign;

import cn.javaquan.app.mobile.bff.news.feign.vo.TouTiao;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * 今日头条热搜
 *
 * @author wangquan
 */
@FeignClient(name = "toutiao-hot-search", url = "https://www.toutiao.com")
public interface TouTiaoHotSearchFeign {

    String SEARCH_URL = "https://www.toutiao.com/trending/";

    /**
     * 获取热搜内容
     *
     * @return
     */
    @GetMapping("/hot-event/hot-board/?origin=toutiao_pc")
    TouTiao getNews();

}
