package cn.javaquan.app.mobile.bff.news.controller;

import cn.javaquan.app.mobile.bff.news.service.NewsService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * 新闻查询接口.
 *
 * @author wangquan
 * @since 1.0.0
 */
@RequiredArgsConstructor
@RestController
@RequestMapping("/open/news/")
public class OpenNewsController {

    private final NewsService newsService;

    /**
     * 根据新闻类型编码查询.
     * @param code 新闻编码
     * @param refresh 是否刷新
     * @param offset 偏移量
     * @return 新闻数据
     */
    @GetMapping("value")
    public List<?> getNews(@RequestParam String code, @RequestParam Boolean refresh,
            @RequestParam(required = false, defaultValue = "1") Integer offset) {
        if (refresh) {
            return newsService.refresh(code, offset);
        }
        return newsService.getNews(code, offset);
    }

}
