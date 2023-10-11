package com.quan.tools.limit;

import org.aopalliance.intercept.MethodInvocation;

import java.util.List;

/**
 * 注解自定义参数构建
 *
 * @author javaquan
 * @since 2.2.0
 */
public interface LimitParamBuilder {

    /**
     * 处理自定义参数
     *
     * @param invocation
     * @param params     自定义参数
     * @return
     */
    String buildParam(MethodInvocation invocation, String[] params);

    /**
     * 处理自定义参数
     *
     * @param invocation
     * @param param
     * @return
     */
    String buildParam(MethodInvocation invocation, String param);

    /**
     * 处理自定义参数
     *
     * @param invocation
     * @param params     自定义参数
     * @return
     */
    List buildArrayParam(MethodInvocation invocation, String[] params);
}
