package cn.javaquan.app.aggregate.beans;

import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.context.annotation.AnnotationBeanNameGenerator;

/**
 * 自定义bean名称生成器.
 *
 * @author javaquan
 * @since 1.0.0
 */
public class QuanBeanNameGenerator extends AnnotationBeanNameGenerator {

    private static final char NON_COMPATIBLE_CLASS_SEPARATOR = '#';

    @Override
    public String generateBeanName(BeanDefinition beanDefinition, BeanDefinitionRegistry beanDefinitionRegistry) {
        String beanClassName = super.generateBeanName(beanDefinition, beanDefinitionRegistry);
        return generateBeanName(0, beanClassName, beanClassName, beanDefinitionRegistry);
    }

    /**
     * 生成bean名称.
     * @param index 当前bean索引
     * @param originalBeanClassName 原始Bean类名
     * @param beanClassName 类名
     * @param beanDefinitionRegistry 用于保存bean定义的注册中心的接口
     * @return bean名称
     */
    private String generateBeanName(int index, String originalBeanClassName, String beanClassName,
            BeanDefinitionRegistry beanDefinitionRegistry) {
        if (contains(beanClassName, beanDefinitionRegistry.getBeanDefinitionNames())) {
            int suffix = index + 1;
            StringBuffer sb = new StringBuffer();
            sb.append(originalBeanClassName).append(NON_COMPATIBLE_CLASS_SEPARATOR).append(suffix);
            return generateBeanName(suffix, originalBeanClassName, sb.toString(), beanDefinitionRegistry);
        }
        return beanClassName;
    }

    /**
     * 判断bean名称是否在注册中心中.
     * @param beanClassName 类名
     * @param beanDefinitionNames 注册中心中定义的所有bean的名称。
     * @return true：在注册中心中存在，false：在注册中心中不存在
     */
    public boolean contains(String beanClassName, String[] beanDefinitionNames) {
        return indexOf(beanClassName, beanDefinitionNames) >= 0;
    }

    /**
     * 查找bean名称.
     * @param beanClassName 类名
     * @param beanDefinitionNames 注册中心中定义的所有bean的名称。
     * @return 返回Bean类名在注册中心所有的bean列表的索引
     */
    public int indexOf(String beanClassName, String[] beanDefinitionNames) {
        if (beanDefinitionNames == null || beanDefinitionNames.length == 0) {
            return 0;
        }
        else {
            for (int i = 0; i < beanDefinitionNames.length; i++) {
                if (beanClassName.equals(beanDefinitionNames[i])) {
                    return i;
                }
            }
        }
        return -1;
    }

}
