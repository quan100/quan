package cn.javaquan.tools.limiter.aop;

import cn.javaquan.tools.limiter.LimiterParamsResolver;
import cn.javaquan.tools.limiter.LimiterPostProcessor;
import cn.javaquan.tools.limiter.annotation.Limiter;
import cn.javaquan.tools.limiter.autoconfigure.LimiterProperties;
import cn.javaquan.tools.limiter.exception.LimiterException;
import cn.javaquan.tools.limiter.executor.LimiterExecutorInvocation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;
import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * 限制器注解方法拦截器
 *
 * @author javaquan
 * @since 2.2.0
 */
@Slf4j
@RequiredArgsConstructor
public class LimiterInterceptor implements MethodInterceptor {

    private final LimiterParamsResolver limiterParamsResolver;
    private final LimiterProperties properties;
    private final LimiterExecutorInvocation executorInvocation;

    @Override
    public Object invoke(MethodInvocation invocation) throws Throwable {
        Limiter limiter = invocation.getMethod().getAnnotation(Limiter.class);
        // 获取令牌参数值
        String tokenParam = limiterParamsResolver.resolveParam(invocation, limiter.params());
        if (!StringUtils.hasText(tokenParam) && !limiter.nullable()) {
            return invocation.proceed();
        }
        String defaultToken = createToken(limiter.name(), tokenParam, invocation);
        // 若配置了数组类型的表达式，则获取参数集合
        List<String> tokenParams = limiterParamsResolver.resolveArrayParam(invocation, limiter.arrayParams());
        return limiterInvoke(defaultToken, tokenParam, tokenParams, limiter, invocation);
    }

    /**
     * 创建令牌
     *
     * @param name       令牌名称
     * @param tokenParam 令牌参数值，拼接到令牌后面
     * @param invocation
     * @return 令牌
     */
    private String createToken(String name, String tokenParam, MethodInvocation invocation) {
        StringBuilder prefix = new StringBuilder();
        if (!StringUtils.hasText(name)) {
            name = invocation.getMethod().getDeclaringClass().getName();
        }
        prefix.append(name);
        prefix.append("#");
        prefix.append(invocation.getMethod().getName());

        if (StringUtils.hasText(tokenParam)) {
            prefix.append("_").append(tokenParam);
        }

        return createToken(properties.getTokenPrefix(), prefix.toString());
    }

    /**
     * 创建令牌
     *
     * @param prefix     默认生成令牌的前缀
     * @param tokenParam 令牌参数值，拼接到令牌后面
     * @return 令牌
     */
    private String createToken(String prefix, String tokenParam) {
        return prefix + ":" + DigestUtils.md5Hex(tokenParam);
    }

    /**
     * 调用限制器执行方法
     *
     * @param defaultToken 生成的默认的令牌
     * @param tokenParam   令牌参数值
     * @param tokenParams  数组类型的令牌参数值，根据数组元素数量生成多个令牌
     * @param limiter      限制器注解
     * @param invocation
     * @return
     * @throws Throwable
     */
    public Object limiterInvoke(String defaultToken, String tokenParam, List<String> tokenParams, Limiter limiter, MethodInvocation invocation) throws Throwable {
        List<LimiterPostProcessor> limiterPostProcessors = null;
        try {
            if (CollectionUtils.isEmpty(tokenParams)) {
                limiterPostProcessors = Collections.singletonList(invoke(defaultToken, limiter, tokenParam));
            } else {
                limiterPostProcessors = limiterProcessor(defaultToken, limiter, tokenParams);
            }
            return invocation.proceed();
        } finally {
            if (limiter.automaticReleaseLock() && !CollectionUtils.isEmpty(limiterPostProcessors)) {
                limiterPostProcessors.forEach(LimiterPostProcessor::release);
            }
        }
    }

    /**
     * 限制器处理程序
     * <p>
     * 批量的令牌执行权限，当令牌数量超出 {@link Limiter#arrayParamsThreshold()} 时，超出部分将忽略。
     *
     * @param defaultToken 默认生成的令牌
     * @param limiter      限制器注解
     * @param tokenParams  一组令牌参数
     * @return
     */
    private List<LimiterPostProcessor> limiterProcessor(String defaultToken, Limiter limiter, List<String> tokenParams) {
        Stream<String> stream = tokenParams.stream();
        int threshold = limiter.arrayParamsThreshold();
        if (threshold >= 0) {
            if (threshold == 0) {
                return Collections.emptyList();
            }
            stream = stream.limit(threshold);
        }
        return stream.map(tokenParam ->
                invoke(createToken(defaultToken, tokenParam), limiter, tokenParams)
        ).filter(Objects::nonNull).collect(Collectors.toList());
    }

    /**
     * 限制器处理程序
     *
     * @param token   申请执行权限的令牌
     * @param limiter 限制器注解
     * @return
     */
    private LimiterPostProcessor invoke(String token, Limiter limiter, Object tokenParams) {
        LimiterPostProcessor postProcessor;
        try {
            postProcessor = executorInvocation.invoke(token, limiter.leaseTime(), limiter.waitTime(), limiter.executor());
        } catch (Exception e) {
            return null;
        }
        if (!postProcessor.getExecute()) {
            log.warn("[Limiter] 令牌：{} 执行失败，请求过于频繁。请等待前置任务完成后，重新获取令牌执行权限！", token);
            String message = limiter.message();
            if (null != tokenParams) {
                message = String.format(message, tokenParams);
            }
            throw new LimiterException(message);
        }
        return postProcessor;
    }

}
