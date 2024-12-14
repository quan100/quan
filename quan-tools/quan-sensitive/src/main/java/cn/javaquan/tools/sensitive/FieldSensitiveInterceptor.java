package cn.javaquan.tools.sensitive;

import com.baomidou.mybatisplus.core.metadata.TableInfoHelper;
import org.apache.ibatis.cache.CacheKey;
import org.apache.ibatis.executor.Executor;
import org.apache.ibatis.mapping.BoundSql;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.plugin.Interceptor;
import org.apache.ibatis.plugin.Intercepts;
import org.apache.ibatis.plugin.Invocation;
import org.apache.ibatis.plugin.Signature;
import org.apache.ibatis.session.ResultHandler;
import org.apache.ibatis.session.RowBounds;

import java.lang.reflect.Field;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 敏感字段拦截器.
 *
 * @author javaquan
 * @since 2.3.2
 */
@Intercepts({
        @Signature(type = Executor.class, method = "query",
                args = { MappedStatement.class, Object.class, RowBounds.class, ResultHandler.class }),
        @Signature(type = Executor.class, method = "query", args = { MappedStatement.class, Object.class,
                RowBounds.class, ResultHandler.class, CacheKey.class, BoundSql.class }) })
public class FieldSensitiveInterceptor implements Interceptor {

    @Override
    public Object intercept(Invocation invocation) throws Throwable {
        Object proceed = invocation.proceed();
        postProcessor(proceed);
        return proceed;
    }

    /**
     * 后置处理器，处理返回结果.
     * @param proceed 返回结果
     */
    private void postProcessor(Object proceed) {
        if (proceed instanceof List) {
            ((List<?>) proceed).forEach(this::annotationProcessor);
        }
        else {
            annotationProcessor(proceed);
        }
    }

    /**
     * 脱敏注解处理.
     * @param proceed 返回结果
     */
    private void annotationProcessor(Object proceed) {
        List<Field> fields = TableInfoHelper.getAllFields(proceed.getClass());
        try {
            for (Field field : fields) {
                fieldDataDesensitization(field, proceed);
            }
        }
        catch (IllegalAccessException ex) {
            throw new RuntimeException(ex);
        }
    }

    /**
     * 字段数据脱敏.
     * @param field 字段
     * @param proceed 返回结果
     */
    private void fieldDataDesensitization(Field field, Object proceed) throws IllegalAccessException {
        if (!field.isAnnotationPresent(FieldSensitive.class)) {
            return;
        }
        FieldSensitive fieldSensitive = field.getAnnotation(FieldSensitive.class);
        SensitiveRuleEnum[] ruleEnums = fieldSensitive.rule();
        Object fieldValue = field.get(proceed);
        if (!field.getType().equals(String.class) || fieldValue == null) {
            return;
        }
        String fieldValueString = String.valueOf(fieldValue);
        for (SensitiveRuleEnum ruleEnum : ruleEnums) {
            Matcher matcher = Pattern.compile(ruleEnum.getRegex()).matcher(fieldValueString);
            if (matcher.find()) {
                field.set(proceed, matcher.replaceAll(ruleEnum.getReplacement()));
                break;
            }
        }
    }

}
