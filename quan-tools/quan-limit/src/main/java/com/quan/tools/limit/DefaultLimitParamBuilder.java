package com.quan.tools.limit;

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

/**
 * 处理注解参数
 *
 * @author javaquan
 * @since 2.2.0
 */
public class DefaultLimitParamBuilder implements LimitParamBuilder {

    private static final ParameterNameDiscoverer NAME_DISCOVERER = new DefaultParameterNameDiscoverer();
    private static final ExpressionParser PARSER = new SpelExpressionParser();

    @Override
    public String buildParam(MethodInvocation invocation, String[] params) {
        Method method = invocation.getMethod();
        if (params.length > 0) {
            return getSpelParam(params, method, invocation.getArguments());
        }
        return "";
    }

    @Override
    public String buildParam(MethodInvocation invocation, String param) {
        if (StringUtils.hasText(param)) {
            EvaluationContext context = buildEvaluationContext(invocation.getMethod(), invocation.getArguments());
            return getValue(param, context);
        }
        return null;
    }

    @Override
    public List buildArrayParam(MethodInvocation invocation, String[] params) {
        Method method = invocation.getMethod();
        if (params.length > 0) {
            return getSpelArrayParam(params, method, invocation.getArguments());
        }
        return Collections.emptyList();
    }

    protected List getSpelArrayParam(String[] params, Method method, Object[] parameterValues) {
        EvaluationContext context = buildEvaluationContext(method, parameterValues);
        List<String> paramsList = new ArrayList<>();
        for (String param : params) {
            if (StringUtils.hasText(param)) {
                List paramVal = PARSER.parseExpression(param).getValue(context, List.class);
                if (!CollectionUtils.isEmpty(paramVal)) {
                    paramsList.addAll(paramVal);
                }
            }
        }
        return paramsList;
    }

    protected String getSpelParam(String[] params, Method method, Object[] parameterValues) {
        EvaluationContext context = buildEvaluationContext(method, parameterValues);
        List<String> paramsList = new ArrayList<>(params.length);
        for (String param : params) {
            if (null != param && !param.isEmpty()) {
                String value = getValue(param, context);
                if (StringUtils.hasText(value)) {
                    paramsList.add(value);
                }
            }
        }
        return StringUtils.collectionToDelimitedString(paramsList, "_", "", "");
    }

    private EvaluationContext buildEvaluationContext(Method method, Object[] parameterValues) {
        EvaluationContext context = new MethodBasedEvaluationContext(null, method, parameterValues, NAME_DISCOVERER);
        return context;
    }

    private String getValue(String param, EvaluationContext context) {
        return PARSER.parseExpression(param).getValue(context, String.class);
    }

}
