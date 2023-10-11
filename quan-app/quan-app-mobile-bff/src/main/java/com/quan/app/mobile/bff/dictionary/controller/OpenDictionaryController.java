package com.quan.app.mobile.bff.dictionary.controller;

import com.quan.app.common.module.dictionary.DictionaryQuery;
import com.quan.common.base.message.Result;
import com.quan.app.mobile.bff.dictionary.feign.OpenDictionaryServiceFeign;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


/**
 * @author wangquan
 * @version 1.0.0
 * @date 2020-02-12 19:50:38
 */
@RequiredArgsConstructor
@RestController
@RequestMapping("/open/dictionary/")
public class OpenDictionaryController {

    private final OpenDictionaryServiceFeign dictionaryServiceFeign;

    /**
     * 根据字典编码获取字典值
     *
     * @param code
     * @return
     */
    @GetMapping("value")
    public Result<Object> queryValue(@RequestParam String code) {
        DictionaryQuery query = new DictionaryQuery();
        query.setCode(code);
        query.setOpen(1);
        return dictionaryServiceFeign.getValue(query);
    }

}
