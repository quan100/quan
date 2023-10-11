package com.quan.app.pm.bff.dictionary.feign.fallback;

import com.quan.app.common.module.dictionary.DictionaryAddCommand;
import com.quan.app.common.module.dictionary.DictionaryQuery;
import com.quan.app.common.module.dictionary.DictionaryUpdateCommand;
import com.quan.common.base.message.Result;
import com.quan.app.pm.bff.dictionary.feign.DictionaryServiceFeign;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.openfeign.FallbackFactory;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * 字典
 *
 * @author JavaQuan
 * @version 1.0.0
 * @date 2023-04-14 16:43:32
 */
@Slf4j
@Component
public class DictionaryServiceFallback implements FallbackFactory<DictionaryServiceFeign> {

    @Override
    public DictionaryServiceFeign create(Throwable throwable) {
        return new DictionaryServiceFeign() {
            @Override
            public Result page(DictionaryQuery query) {
                return Result.fail(throwable.getMessage());
            }

            @Override
            public Result details(Long id) {
                return Result.fail(throwable.getMessage());
            }

            @Override
            public Result update(DictionaryUpdateCommand cmd) {
                return Result.fail(throwable.getMessage());
            }

            @Override
            public Result save(DictionaryAddCommand cmd) {
                return Result.fail(throwable.getMessage());
            }

            @Override
            public Result saveBatch(List<DictionaryAddCommand> cmds) {
                return Result.fail(throwable.getMessage());
            }

            @Override
            public Result deleteByIds(List<Long> ids) {
                return Result.fail(throwable.getMessage());
            }

            @Override
            public Result getValue(DictionaryQuery query) {
                return Result.fail(throwable.getMessage());
            }
        };
    }
}
