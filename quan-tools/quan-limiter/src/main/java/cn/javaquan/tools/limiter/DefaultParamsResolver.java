package cn.javaquan.tools.limiter;

import org.aopalliance.intercept.MethodInvocation;
import org.springframework.context.expression.MethodBasedEvaluationContext;
import org.springframework.core.DefaultParameterNameDiscoverer;
import org.springframework.core.ParameterNameDiscoverer;
import org.springframework.expression.EvaluationContext;
import org.springframework.expression.ExpressionParser;
import org.springframework.expression.spel.standard.SpelExpressionParser;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * 默认的参数解析器，支持 SpEL 表达式. 可以通过实现 {@link LimiterParamsResolver} 接口，并注入到
 * Spring容器中，覆盖默认的参数解析器的实现。
 *
 * @author javaquan
 * @since 2.2.0
 */
public class DefaultParamsResolver implements LimiterParamsResolver {

    private static final ParameterNameDiscoverer NAME_DISCOVERER = new DefaultParameterNameDiscoverer();

    private static final ExpressionParser PARSER = new SpelExpressionParser();

    @Override
    public String resolveParam(MethodInvocation invocation, String[] params) {
        Method method = invocation.getMethod();
        if (params.length > 0) {
            return getSpelParam(params, method, invocation.getArguments());
        }
        return "";
    }

    @Override
    public List<String> resolveArrayParam(MethodInvocation invocation, String[] params) {
        Method method = invocation.getMethod();
        if (params.length > 0) {
            return getSpelArrayParam(params, method, invocation.getArguments());
        }
        return Collections.emptyList();
    }

    /**
     * 获取 SpEL 表达式解析后的值. 解析数组类型的参数.
     * @param params 解析参数的表达式
     * @param method 方法
     * @param parameterValues 调用的参数
     * @return 解析后的值
     */
    protected List<String> getSpelArrayParam(String[] params, Method method, Object[] parameterValues) {
        EvaluationContext context = buildEvaluationContext(method, parameterValues);
        return resolveValue(params, (expressionString) -> {
            List<?> arrayValue = getArrayValue(expressionString, context);
            if (CollectionUtils.isEmpty(arrayValue)) {
                return null;
            }
            return arrayValue.stream().map(String::valueOf).collect(Collectors.toList());
        });
    }

    /**
     * 获取 SpEL 表达式解析后的值.
     * @param params 解析参数的表达式
     * @param method 方法
     * @param parameterValues 调用的参数
     * @return 解析后的值
     */
    protected String getSpelParam(String[] params, Method method, Object[] parameterValues) {
        EvaluationContext context = buildEvaluationContext(method, parameterValues);
        List<String> values = resolveValue(params, (expressionString) -> {
            String value = getValue(expressionString, context);
            if (StringUtils.hasText(value)) {
                return Collections.singletonList(value);
            }
            return null;
        });
        return StringUtils.collectionToDelimitedString(values, "_", "", "");
    }

    /**
     * 解析 SpEL 表达式.
     * @param params 解析参数的表达式
     * @param function 解析后的值
     * @param <T> 解析后的值类型
     * @return 解析后的值
     */
    protected <T> List<T> resolveValue(String[] params, Function<String, List<T>> function) {
        return Arrays.stream(params).flatMap(expressionString -> {
            if (StringUtils.hasText(expressionString)) {
                return function.apply(expressionString).stream();
            }
            return null;
        }).filter(Objects::nonNull).collect(Collectors.toList());
    }

    /**
     * 构建 EvaluationContext.
     * @param method 方法
     * @param parameterValues 调用的参数
     * @return evaluationContext
     */
    private EvaluationContext buildEvaluationContext(Method method, Object[] parameterValues) {
        return new MethodBasedEvaluationContext(null, method, parameterValues, NAME_DISCOVERER);
    }

    /**
     * 获取 SpEL 表达式解析后的值.
     * @param expressionString 解析参数的表达式
     * @param context evaluationContext
     * @return 解析后的值
     */
    private String getValue(String expressionString, EvaluationContext context) {
        return PARSER.parseExpression(expressionString).getValue(context, String.class);
    }

    /**
     * 获取 SpEL 表达式解析后的值.
     * @param expressionString 解析参数的表达式
     * @param context evaluationContext
     * @return 解析后的值
     */
    private List<?> getArrayValue(String expressionString, EvaluationContext context) {
        return PARSER.parseExpression(expressionString).getValue(context, List.class);
    }

}
