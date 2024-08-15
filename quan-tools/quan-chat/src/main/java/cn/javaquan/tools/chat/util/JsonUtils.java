package cn.javaquan.tools.chat.util;

import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.AnnotationIntrospector;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.introspect.JacksonAnnotationIntrospector;
import com.fasterxml.jackson.databind.type.TypeFactory;
import com.fasterxml.jackson.module.jaxb.JaxbAnnotationIntrospector;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

/**
 * JSON 工具类.
 *
 * @author javaquan
 * @since 1.0.0
 */
public abstract class JsonUtils {

    /**
     * 默认的日期格式.
     */
    public static final String DATE_TIME_FORMAT = "yyyy-MM-dd HH:mm:ss";

    /**
     * JSON格式化时间的默认格式.
     */
    private static final DateFormat DEFAULT_DATETIME_FORMAT = new SimpleDateFormat(DATE_TIME_FORMAT);

    private static final ObjectMapper OBJECT_MAPPER = new ObjectMapper();

    static {
        // 设置输出格式
        OBJECT_MAPPER.configure(SerializationFeature.WRAP_ROOT_VALUE, false)
            .configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        // .configure(Feature.WRITE_NUMBERS_AS_STRINGS, true);
        // 设置输出格式
        OBJECT_MAPPER.setSerializationInclusion(Include.NON_NULL);
        // 设置日期格式
        OBJECT_MAPPER.setDateFormat(DEFAULT_DATETIME_FORMAT);
        // 设置支持的注解类型
        AnnotationIntrospector introspector = AnnotationIntrospector.pair(new JacksonAnnotationIntrospector(),
                new JaxbAnnotationIntrospector(TypeFactory.defaultInstance()));
        OBJECT_MAPPER.setAnnotationIntrospector(introspector);
        // 处理字符类型空值为空字符串
        OBJECT_MAPPER.getSerializerProvider().setNullValueSerializer(new JsonSerializer<Object>() {
            @Override
            public void serialize(Object value, JsonGenerator jgen, SerializerProvider provider)
                    throws IOException, JsonProcessingException {
                jgen.writeString("");
            }
        });

    }

    /**
     * 将对象转换为JSON字符串.
     * @param object 转换的JAVABEAN对象
     * @return 返回转换后的JSON格式字符串
     */
    public static String toJSONString(Object object) {
        try {
            return OBJECT_MAPPER.writeValueAsString(object);
        }
        catch (JsonProcessingException ex) {
            throw new RuntimeException(ex);
        }
    }

    /**
     * 将字符串转换为对象.
     * @param input json字符串
     * @param clazz 类型的bean必须匹配;可以是接口或超类。
     * @param <T> 由这个class对象建模的类的类型。
     * @return 与所需类型匹配的对象实例
     */
    public static <T> T parseObject(String input, Class<T> clazz) {
        try {
            return OBJECT_MAPPER.readValue(input, clazz);
        }
        catch (IOException ex) {
            throw new RuntimeException(ex);
        }
    }

}
