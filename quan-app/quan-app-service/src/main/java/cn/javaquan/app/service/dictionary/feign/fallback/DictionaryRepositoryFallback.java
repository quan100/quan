package cn.javaquan.app.service.dictionary.feign.fallback;

import cn.javaquan.app.service.dictionary.feign.DictionaryRepositoryFeign;
import cn.javaquan.tools.notify.SystemNotifyException;
import cn.javaquan.app.common.module.dictionary.DictionaryAddCommand;
import cn.javaquan.app.common.module.dictionary.DictionaryDTO;
import cn.javaquan.app.common.module.dictionary.DictionaryQuery;
import cn.javaquan.app.common.module.dictionary.DictionaryUpdateCommand;
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
public class DictionaryRepositoryFallback implements FallbackFactory<DictionaryRepositoryFeign> {

    @Override
    public DictionaryRepositoryFeign create(Throwable throwable) {
        return new DictionaryRepositoryFeign() {
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

            @Override
            public Result<DictionaryDTO> getDictionary(DictionaryQuery query) {
                throw new SystemNotifyException(throwable);
            }

            @Override
            public Result<List<DictionaryDTO>> getDictionaries(DictionaryQuery query) {
                throw new SystemNotifyException(throwable);
            }
        };
    }

}
