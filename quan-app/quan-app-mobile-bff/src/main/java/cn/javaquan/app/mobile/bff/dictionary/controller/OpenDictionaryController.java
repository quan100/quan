package cn.javaquan.app.mobile.bff.dictionary.controller;

import cn.javaquan.app.common.module.dictionary.DictionaryQuery;
import cn.javaquan.common.base.message.Result;
import cn.javaquan.app.mobile.bff.dictionary.feign.OpenDictionaryServiceFeign;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * 字典接口.
 *
 * @author wangquan
 * @since 1.0.0
 */
@RequiredArgsConstructor
@RestController
@RequestMapping("/open/dictionary/")
public class OpenDictionaryController {

    private final OpenDictionaryServiceFeign dictionaryServiceFeign;

    /**
     * 根据字典编码获取字典值.
     * @param code 字典编码
     * @return 字典值
     */
    @GetMapping("value")
    public Result<Object> queryValue(@RequestParam String code) {
        DictionaryQuery query = new DictionaryQuery();
        query.setCode(code);
        query.setOpen(1);
        return dictionaryServiceFeign.getValue(query);
    }

}
