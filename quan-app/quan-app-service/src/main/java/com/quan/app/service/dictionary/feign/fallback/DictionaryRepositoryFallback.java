package com.quan.app.service.dictionary.feign.fallback;

import com.quan.tools.notice.SystemNoticeException;
import com.quan.app.common.module.dictionary.DictionaryAddCommand;
import com.quan.app.common.module.dictionary.DictionaryDTO;
import com.quan.app.common.module.dictionary.DictionaryQuery;
import com.quan.app.common.module.dictionary.DictionaryUpdateCommand;
import com.quan.app.service.dictionary.feign.DictionaryRepositoryFeign;
import com.quan.common.base.message.Result;
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
public class DictionaryRepositoryFallback implements FallbackFactory<DictionaryRepositoryFeign> {

    @Override
    public DictionaryRepositoryFeign create(Throwable throwable) {
        return new DictionaryRepositoryFeign() {
            @Override
            public Result page(DictionaryQuery query) {
                throw new SystemNoticeException(throwable);
            }

            @Override
            public Result details(Long id) {
                throw new SystemNoticeException(throwable);
            }

            @Override
            public Result update(DictionaryUpdateCommand cmd) {
                throw new SystemNoticeException(throwable);
            }

            @Override
            public Result save(DictionaryAddCommand cmd) {
                throw new SystemNoticeException(throwable);
            }

            @Override
            public Result saveBatch(List<DictionaryAddCommand> cmds) {
                throw new SystemNoticeException(throwable);
            }

            @Override
            public Result deleteByIds(List<Long> ids) {
                throw new SystemNoticeException(throwable);
            }

            @Override
            public Result getValue(DictionaryQuery query) {
                throw new SystemNoticeException(throwable);
            }

            @Override
            public Result<DictionaryDTO> getDictionary(DictionaryQuery query) {
                throw new SystemNoticeException(throwable);
            }

            @Override
            public Result<List<DictionaryDTO>> getDictionaries(DictionaryQuery query) {
                throw new SystemNoticeException(throwable);
            }
        };
    }
}
