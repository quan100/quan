package cn.javaquan.app.mobile.bff.dictionary.feign;

import cn.javaquan.app.mobile.bff.dictionary.feign.fallback.OpenDictionaryServiceFallback;
import cn.javaquan.app.common.module.dictionary.DictionaryQuery;
import cn.javaquan.common.base.message.Result;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.cloud.openfeign.SpringQueryMap;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * 字典.
 *
 * @author javaquan
 * @since 1.0.0
 */
@FeignClient(value = "${quan.app.feign.service.name}", url = "${quan.app.feign.service.url:}",
        fallbackFactory = OpenDictionaryServiceFallback.class)
public interface OpenDictionaryServiceFeign {

    /**
     * 根据字典编码获取字典值.
     * @param query 查询参数
     * @return 字典值
     */
    @GetMapping("/service/dictionary/value")
    Result getValue(@SpringQueryMap DictionaryQuery query);

}
