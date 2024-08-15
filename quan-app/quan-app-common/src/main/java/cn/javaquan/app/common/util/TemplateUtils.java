package cn.javaquan.app.common.util;

import com.alibaba.fastjson.JSON;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.CollectionUtils;
import org.springframework.util.PropertyPlaceholderHelper;

import java.util.Map;
import java.util.Properties;

/**
 * 字符串格式化工具.
 *
 * @author javaquan
 * @since 1.0.0
 */
@Slf4j
public final class TemplateUtils {

    /**
     * 私有构造方法.
     */
    private TemplateUtils() {
    }

    private static final PropertyPlaceholderHelper helper = new PropertyPlaceholderHelper("${", "}", ":", true);

    /**
     * 替换模板中参数，返回最终的内容.
     * @param template 模版
     * @param params 待替换的参数，将模版中的所有占位符名称，替换为参数中对应属性的值。仅支持字符串格式（参数值必须为字符串）。
     * @return 通过模版参数替换后的内容
     */
    public static String getText(String template, Object params) {
        Properties properties = JSON.parseObject(JSON.toJSONString(params), Properties.class);
        return getText(template, properties);
    }

    /**
     * 替换模板中参数，返回最终的内容.
     * @param template 模版
     * @param properties 待替换的参数，将模版中的所有占位符名称，替换为参数中对应属性的值。仅支持字符串格式（参数值必须为字符串）。
     * @return 通过模版参数替换后的内容
     */
    public static String getText(String template, Properties properties) {
        if (CollectionUtils.isEmpty(properties)) {
            return template;
        }
        return helper.replacePlaceholders(template, properties);
    }

    /**
     * 替换模板中参数，返回最终的内容.
     * @param template 模版
     * @param paramsMap 待替换的参数，将模版中的所有占位符名称，替换为参数中对应属性的值。若值为Object类型，则将值转换为JSON字符串
     * @return 通过模版参数替换后的内容
     */
    public static String getText(String template, Map<String, Object> paramsMap) {
        if (paramsMap == null || paramsMap.isEmpty()) {
            return template;
        }
        return helper.replacePlaceholders(template, new MapTemplatePropertyPlaceholderResolver(paramsMap));
    }

    /**
     * PlaceholderResolver实现，针对模版属性进行解析.
     * <p>
     * 转换JSON格式参数
     */
    private static class MapTemplatePropertyPlaceholderResolver
            implements PropertyPlaceholderHelper.PlaceholderResolver {

        private final Map<String, Object> paramsMap;

        MapTemplatePropertyPlaceholderResolver(Map<String, Object> paramsMap) {
            this.paramsMap = paramsMap;
        }

        @Override
        public String resolvePlaceholder(String placeholderName) {
            try {
                Object propVal = this.paramsMap.getOrDefault(placeholderName, null);
                if (null == propVal) {
                    return null;
                }
                return JSON.toJSONString(propVal);
            }
            catch (Throwable ex) {
                log.error("无法解析占位符 '" + placeholderName + "' 在 [" + this.paramsMap + "] 参数中: " + ex.getMessage(), ex);
                return null;
            }
        }

    }

}
