package cn.javaquan.tools.limiter;

import org.aopalliance.intercept.MethodInvocation;

import java.util.List;

/**
 * 限制器令牌参数解析器接口
 *
 * @author javaquan
 * @since 2.2.0
 */
public interface LimiterParamsResolver {

    /**
     * 解析参数
     *
     * @param invocation
     * @param params     令牌参数的SpEL表达式。
     * @return 解析后的参数值，多个模版解析的参数将按照顺序拼接
     */
    String resolveParam(MethodInvocation invocation, String[] params);

    /**
     * 解析数组类型的参数
     *
     * @param invocation
     * @param params     令牌参数的SpEL表达式
     * @return
     */
    List resolveArrayParam(MethodInvocation invocation, String[] params);
}
