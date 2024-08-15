package cn.javaquan.app.common.util.dictionary.feign;

import cn.javaquan.app.common.module.dictionary.DictionaryQuery;
import cn.javaquan.app.common.util.dictionary.feign.fallback.DictionaryUtilFallback;
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
@FeignClient(value = "${quan.app.feign.core.name}", url = "${quan.app.feign.core.url:}",
        fallbackFactory = DictionaryUtilFallback.class)
public interface DictionaryUtilFeign {

    /**
     * 根据字典编码获取字典值.
     * @param query 查询条件
     * @return 字典值
     */
    @GetMapping("/core/dictionary/value")
    Result<Object> getValue(@SpringQueryMap DictionaryQuery query);

}
