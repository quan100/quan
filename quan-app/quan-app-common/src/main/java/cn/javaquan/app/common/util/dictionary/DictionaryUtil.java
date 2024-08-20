package cn.javaquan.app.common.util.dictionary;

import cn.javaquan.app.common.module.dictionary.DictionaryQuery;
import cn.javaquan.app.common.util.dictionary.feign.DictionaryUtilFeign;
import cn.javaquan.common.base.message.Result;
import com.alibaba.fastjson.JSON;
import org.springframework.stereotype.Component;

/**
 * 系统字典工具类.
 *
 * @author javaquan
 * @since 1.0.0
 */
@Component
public class DictionaryUtil {

    private static DictionaryUtilFeign dictionaryUtilFeign;

    /**
     * 注入feign.
     * @param dictionaryUtilFeign 注入feign
     */
    public DictionaryUtil(DictionaryUtilFeign dictionaryUtilFeign) {
        DictionaryUtil.dictionaryUtilFeign = dictionaryUtilFeign;
    }

    /**
     * 根据字典编码获取字典值.
     * @param code 字典编码
     * @return json格式的字典值
     */
    public static String value(String code) {
        DictionaryQuery query = new DictionaryQuery();
        query.setCode(code);
        Result<Object> result = dictionaryUtilFeign.getValue(query);
        if (result.isData()) {
            return JSON.toJSONString(result.getData());
        }
        return null;
    }

}
