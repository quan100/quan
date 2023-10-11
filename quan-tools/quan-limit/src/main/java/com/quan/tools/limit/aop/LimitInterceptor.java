package com.quan.tools.limit.aop;

import com.quan.tools.limit.LimitInfo;
import com.quan.tools.limit.LimitParamBuilder;
import com.quan.tools.limit.annotation.Limit;
import com.quan.tools.limit.autoconfigure.LimitProperties;
import com.quan.tools.limit.exception.LimitException;
import com.quan.tools.limit.executor.LimitExecutor;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 限流aop处理器
 *
 * @author javaquan
 * @since 2.2.0
 */
@Slf4j
@RequiredArgsConstructor
public class LimitInterceptor implements MethodInterceptor {

    private final LimitParamBuilder limitParamBuilder;
    private final LimitProperties limitProperties;
    private final LimitExecutor limitExecutor;

    @Override
    public Object invoke(MethodInvocation invocation) throws Throwable {
        Limit limit = invocation.getMethod().getAnnotation(Limit.class);
        // 获取限流执行器参数处理
        String tokenParams = limitParamBuilder.buildParam(invocation, limit.params());
        if (!StringUtils.hasText(tokenParams) && limit.existParam()) {
            return invocation.proceed();
        }
        String defaultToken = tokenPrefix(limit.name(), tokenParams, invocation);
        // 获取令牌拆分参数，根据拆分的参数生成多个令牌
        List<String> tokenSplitParams = limitParamBuilder.buildArrayParam(invocation, limit.splitParams());
        return limiter(defaultToken, tokenParams, tokenSplitParams, limit, invocation);
    }

    /**
     * 令牌前缀
     *
     * @param name        生成令牌的名称，默认为全路径
     * @param tokenParams 令牌参数，拼接到令牌后面
     * @param invocation
     * @return
     */
    private String tokenPrefix(String name, String tokenParams, MethodInvocation invocation) {
        StringBuffer prefix = new StringBuffer();
        prefix.append(limitProperties.getTokenPrefix()).append(":");
        if (StringUtils.hasText(name)) {
            prefix.append(name);
        } else {
            prefix.append(invocation.getMethod().getDeclaringClass().getName());
            prefix.append("#");
            prefix.append(invocation.getMethod().getName());
        }
        if (StringUtils.hasText(tokenParams)) {
            prefix.append("_").append(tokenParams);
        }
        return prefix.toString();
    }

    /**
     * 限流执行器
     *
     * @param token
     * @param limit
     * @param tokenParams 令牌参数，用于自定义错误信息时，填充参数信息
     * @return
     */
    private LimitInfo limitHandler(String token, Limit limit, Object tokenParams) {
        // 执行限流
        LimitInfo limitInfo = limitExecutor.limit(token, limit.leaseTime(), limit.waitTime());
        if (null == limitInfo) {
            log.warn("【限流】token：{} 获取令牌失败，请求过于频繁。请等待前置任务完成后，重新获取令牌执行权限！", token);
            String message = limit.message();
            if (null != tokenParams) {
                message = String.format(message, tokenParams);
            }
            throw new LimitException(message);
        }
        return limitInfo;
    }

    /**
     * 默认限流
     *
     * @param defaultToken     生成的默认的令牌
     * @param tokenSplitParams 拆分的参数，根据拆分的参数生成多个令牌
     * @param invocation
     * @return
     * @throws Throwable
     */
    public Object limiter(String defaultToken, String tokenParams, List<String> tokenSplitParams, Limit limit, MethodInvocation invocation) throws Throwable {
        List<LimitInfo> limitInfoList = null;
        // 默认限流
        try {
            if (CollectionUtils.isEmpty(tokenSplitParams)) {
                limitInfoList = Arrays.asList(limitHandler(defaultToken, limit, tokenParams));
            } else {
                limitInfoList = tokenSplitParams.stream().limit(limit.splitThreshold()).map(tokenParam -> {
                    StringBuffer token = new StringBuffer();
                    token.append(defaultToken).append("#");
                    token.append(tokenParam);
                    return limitHandler(token.toString(), limit, tokenParam);
                }).collect(Collectors.toList());
            }
            return invocation.proceed();
        } finally {
            // 当方法执行完毕后，释放限流控制
            if (limit.automaticReleaseLock() && !CollectionUtils.isEmpty(limitInfoList)) {
                limitInfoList.stream().forEach(limitInfo -> {
                    final boolean releaseLimit = limitExecutor.releaseLimit(limitInfo);
                    if (!releaseLimit) {
                        log.warn("解除限流失败，请检查令牌的有效期配置是否过短自动解除！ 令牌有效期：{}", limitInfo.getLeaseTime());
                    }
                });
            }
        }
    }

}
