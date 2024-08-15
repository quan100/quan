package cn.javaquan.tools.chat.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

/**
 * Spring 工具类.
 *
 * @author wangquan
 * @since 1.0.0
 */
@Component("quanChatSpringUtils")
public class SpringUtils {

    /**
     * Spring上下文.
     */
    public static ApplicationContext ctx;

    /**
     * 注入上下文.
     * @param applicationContext 上下文
     */
    @Autowired
    private void setApplicationContext(ApplicationContext applicationContext) {
        ctx = applicationContext;
    }

    /**
     * 获得bean.
     * @param clazz 类型的bean必须匹配;可以是接口或超类。
     * @param <T> 由这个class对象建模的类的类型。
     * @return 与所需类型匹配的单个bean的实例
     */
    public static <T> T getBean(Class<T> clazz) {
        return ctx.getBean(clazz);
    }

}
