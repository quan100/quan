package com.quan.app.common.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

/**
 * @author wangquan
 * @date 2021/12/7 11:47
 */
@Component
public class SpringUtils {

    public static ApplicationContext ctx;

    @Autowired
    private void setApplicationContext(ApplicationContext applicationContext) {
        ctx = applicationContext;
    }

    public static ApplicationContext getApplicationContext() {
        return ctx;
    }

    /**
     * 获得bean
     *
     * @param clazz
     * @param <T>
     * @return
     */
    public static <T> T getBean(Class<T> clazz) {
        return ctx.getBean(clazz);
    }

    /**
     * 获得bean
     *
     * @param clazz
     * @param name
     * @param <T>
     * @return
     */
    @SuppressWarnings("unchecked")
    public static <T> T getBean(Class<T> clazz, String name) {
        return ctx.getBean(name, clazz);
    }
}

