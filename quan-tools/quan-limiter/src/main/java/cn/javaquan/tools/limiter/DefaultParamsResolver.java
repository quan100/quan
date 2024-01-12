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
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.function.Consumer;

/**
 * 默认的参数解析器，支持 SpEL 表达式。
 * 可以通过实现 {@link LimiterParamsResolver} 接口，并注入到 Spring 容器中，覆盖默认的参数解析器的实现。
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
    public List resolveArrayParam(MethodInvocation invocation, String[] params) {
        Method method = invocation.getMethod();
        if (params.length > 0) {
            return getSpelArrayParam(params, method, invocation.getArguments());
        }
        return Collections.emptyList();
    }

    protected List getSpelArrayParam(String[] params, Method method, Object[] parameterValues) {
        EvaluationContext context = buildEvaluationContext(method, parameterValues);
        List values = new ArrayList<>();
        resolveValue(params, (expressionString) -> {
            List arrayValue = getArrayValue(expressionString, context);
            if (!CollectionUtils.isEmpty(arrayValue)) {
                values.addAll(arrayValue);
            }
        });
        return values;
    }

    protected String getSpelParam(String[] params, Method method, Object[] parameterValues) {
        EvaluationContext context = buildEvaluationContext(method, parameterValues);
        List<String> values = new ArrayList<>(params.length);
        resolveValue(params, (expressionString) -> {
            String value = getValue(expressionString, context);
            if (StringUtils.hasText(value)) {
                values.add(value);
            }
        });
        return StringUtils.collectionToDelimitedString(values, "_", "", "");
    }

    protected void resolveValue(String[] params, Consumer<String> function) {
        for (String expressionString : params) {
            if (StringUtils.hasText(expressionString)) {
                function.accept(expressionString);
            }
        }
    }

    private EvaluationContext buildEvaluationContext(Method method, Object[] parameterValues) {
        return new MethodBasedEvaluationContext(null, method, parameterValues, NAME_DISCOVERER);
    }

    private String getValue(String expressionString, EvaluationContext context) {
        return PARSER.parseExpression(expressionString).getValue(context, String.class);
    }

    private List getArrayValue(String expressionString, EvaluationContext context) {
        return PARSER.parseExpression(expressionString).getValue(context, List.class);
    }

}
