package com.quan.app.mobile.bff.dictionary.feign;

import com.quan.app.common.module.dictionary.DictionaryQuery;
import com.quan.common.base.message.Result;
import com.quan.app.mobile.bff.dictionary.feign.fallback.OpenDictionaryServiceFallback;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.cloud.openfeign.SpringQueryMap;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * 字典
 *
 * @author JavaQuan
 * @version 1.0.0
 * @date 2023-04-14 16:43:32
 */
@FeignClient(value = "${quan.app.feign.service.name}", url = "${quan.app.feign.service.url:}", fallbackFactory = OpenDictionaryServiceFallback.class)
public interface OpenDictionaryServiceFeign {

    /**
     * 根据字典编码获取字典值
     *
     * @param query
     * @return
     */
    @GetMapping("/service/dictionary/value")
    Result getValue(@SpringQueryMap DictionaryQuery query);
}
