package cn.javaquan.app.mobile.bff.dictionary.feign.fallback;

import cn.javaquan.tools.notify.SystemNotifyException;
import cn.javaquan.app.common.module.dictionary.DictionaryQuery;
import cn.javaquan.app.mobile.bff.dictionary.feign.OpenDictionaryServiceFeign;
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
public class OpenDictionaryServiceFallback implements FallbackFactory<OpenDictionaryServiceFeign> {

    @Override
    public OpenDictionaryServiceFeign create(Throwable throwable) {
        return new OpenDictionaryServiceFeign() {

            @Override
            public Result getValue(DictionaryQuery query) {
                throw new SystemNotifyException(throwable);
            }
        };
    }
}
