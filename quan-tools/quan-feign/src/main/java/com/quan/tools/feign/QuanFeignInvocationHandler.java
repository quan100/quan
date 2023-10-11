package com.quan.tools.feign;

import feign.InvocationHandlerFactory;
import feign.Target;
import feign.Util;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.Map;

/**
 * 参考源码：{@link feign.SynchronousMethodHandler}
 * 修改 {@link feign.ReflectiveFeign.FeignInvocationHandler} 实现，捕获invoke异常信息。
 */
public class QuanFeignInvocationHandler implements InvocationHandler {
    private final Target target;
    private final Map<Method, InvocationHandlerFactory.MethodHandler> dispatch;
    private final NoticeExecutor noticeExecutor;

    public QuanFeignInvocationHandler(Target target, Map<Method, InvocationHandlerFactory.MethodHandler> dispatch, NoticeExecutor noticeExecutor) {
        this.target = Util.checkNotNull(target, "target");
        this.dispatch = Util.checkNotNull(dispatch, "dispatch for %s", target);
        this.noticeExecutor = noticeExecutor;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        if ("equals".equals(method.getName())) {
            try {
                Object otherHandler = args.length > 0 && args[0] != null ? Proxy.getInvocationHandler(args[0]) : null;
                return equals(otherHandler);
            } catch (IllegalArgumentException e) {
                return false;
            }
        } else if ("hashCode".equals(method.getName())) {
            return hashCode();
        } else if ("toString".equals(method.getName())) {
            return toString();
        }
        try {
            return dispatch.get(method).invoke(args);
        } catch (Throwable e) {
            noticeExecutor.push(e);
            throw e;
        }
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof QuanFeignInvocationHandler) {
            QuanFeignInvocationHandler other = (QuanFeignInvocationHandler) obj;
            return target.equals(other.target);
        }
        return false;
    }

    @Override
    public int hashCode() {
        return target.hashCode();
    }

    @Override
    public String toString() {
        return target.toString();
    }
}
