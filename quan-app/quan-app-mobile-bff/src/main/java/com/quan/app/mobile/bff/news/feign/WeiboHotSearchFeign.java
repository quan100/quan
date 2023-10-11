package com.quan.app.mobile.bff.news.feign;

import com.quan.common.base.message.Result;
import com.quan.app.mobile.bff.news.feign.vo.WeiBo;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * 微博热搜
 *
 * @author wangquan
 */
@FeignClient(name = "weibo-hot-search", url = "https://weibo.com")
public interface WeiboHotSearchFeign {

    String SEARCH_URL = "https://s.weibo.com/weibo?q=";

//    /**
//     * 获取热搜内容
//     *
//     * @return
//     */
//    @GetMapping("/ajax/side/hotSearch")
//    Message<WeiBo> getNews();

    /**
     * 获取热搜内容
     *
     * @return
     */
    @GetMapping("/ajax/statuses/hot_band")
    Result<WeiBo> hot_band();

}
