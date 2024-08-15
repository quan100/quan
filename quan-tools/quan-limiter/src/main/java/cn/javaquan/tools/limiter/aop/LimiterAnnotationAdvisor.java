package cn.javaquan.tools.limiter.aop;

import cn.javaquan.tools.limiter.annotation.Limiter;
import lombok.NonNull;
import org.aopalliance.aop.Advice;
import org.springframework.aop.Pointcut;
import org.springframework.aop.support.AbstractPointcutAdvisor;
import org.springframework.aop.support.annotation.AnnotationMatchingPointcut;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.BeanFactoryAware;

/**
 * 限制器注解 AOP 切面处理.
 *
 * @author javaquan
 * @since 2.2.0
 */
public class LimiterAnnotationAdvisor extends AbstractPointcutAdvisor implements BeanFactoryAware {

    /**
     * advice.
     */
    private final Advice advice;

    /**
     * pointcut.
     */
    private final Pointcut pointcut = AnnotationMatchingPointcut.forMethodAnnotation(Limiter.class);

    /**
     * 构造方法注入参数.
     * @param limiterInterceptor 限制器拦截器
     * @param order order
     */
    public LimiterAnnotationAdvisor(@NonNull LimiterInterceptor limiterInterceptor, int order) {
        this.advice = limiterInterceptor;
        setOrder(order);
    }

    @Override
    public Pointcut getPointcut() {
        return this.pointcut;
    }

    @Override
    public Advice getAdvice() {
        return this.advice;
    }

    @Override
    public void setBeanFactory(BeanFactory beanFactory) throws BeansException {
        if (this.advice instanceof BeanFactoryAware) {
            ((BeanFactoryAware) this.advice).setBeanFactory(beanFactory);
        }
    }

}
