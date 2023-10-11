package com.quan.app.mobile.bff.news.controller;

import com.quan.app.mobile.bff.news.service.NewsService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


/**
 * @author wangquan
 * @version 1.0.0
 * @date 2020-02-12 19:50:38
 */
@RequiredArgsConstructor
@RestController
@RequestMapping("/open/news/")
public class OpenNewsController {

    private final NewsService newsService;

    /**
     * 根据
     *
     * @param code
     * @return
     */
    @GetMapping("value")
    public List getNews(@RequestParam String code, @RequestParam Boolean refresh, @RequestParam(required = false, defaultValue = "1") Integer offset) {
        if (refresh) {
            return newsService.refresh(code, offset);
        }
        return newsService.getNews(code, offset);
    }

}
