package cn.javaquan.app.pm.bff.dictionary.feign.fallback;

import cn.javaquan.tools.notify.SystemNotifyException;
import cn.javaquan.app.common.module.dictionary.DictionaryAddCommand;
import cn.javaquan.app.common.module.dictionary.DictionaryQuery;
import cn.javaquan.app.common.module.dictionary.DictionaryUpdateCommand;
import cn.javaquan.app.pm.bff.dictionary.feign.DictionaryServiceFeign;
import cn.javaquan.common.base.message.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.openfeign.FallbackFactory;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * 字典.
 *
 * @author javaquan
 * @since 1.0.0
 */
@Slf4j
@Component
public class DictionaryServiceFallback implements FallbackFactory<DictionaryServiceFeign> {

    @Override
    public DictionaryServiceFeign create(Throwable throwable) {
        return new DictionaryServiceFeign() {
            @Override
            public Result page(DictionaryQuery query) {
                throw new SystemNotifyException(throwable);
            }

            @Override
            public Result details(Long id) {
                throw new SystemNotifyException(throwable);
            }

            @Override
            public Result update(DictionaryUpdateCommand cmd) {
                throw new SystemNotifyException(throwable);
            }

            @Override
            public Result save(DictionaryAddCommand cmd) {
                throw new SystemNotifyException(throwable);
            }

            @Override
            public Result saveBatch(List<DictionaryAddCommand> cmds) {
                throw new SystemNotifyException(throwable);
            }

            @Override
            public Result deleteByIds(List<Long> ids) {
                throw new SystemNotifyException(throwable);
            }

            @Override
            public Result getValue(DictionaryQuery query) {
                throw new SystemNotifyException(throwable);
            }
        };
    }

}
