package cn.javaquan.app.common.util.dictionary.feign.fallback;

import cn.javaquan.app.common.module.dictionary.DictionaryQuery;
import cn.javaquan.app.common.util.dictionary.feign.DictionaryUtilFeign;
import cn.javaquan.common.base.message.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.openfeign.FallbackFactory;
import org.springframework.stereotype.Component;

/**
 * 字典
 *
 * @author JavaQuan
 * @version 1.0.0
 * @date 2023-04-14 16:43:32
 */
@Slf4j
@Component
public class DictionaryUtilFallback implements FallbackFactory<DictionaryUtilFeign> {

    @Override
    public DictionaryUtilFeign create(Throwable throwable) {
        return new DictionaryUtilFeign() {
            @Override
            public Result<Object> getValue(DictionaryQuery query) {
                return Result.fail(throwable.getMessage());
            }
        };
    }
}
