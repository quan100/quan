package com.quan.app.aggregate.beans;

import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.context.annotation.AnnotationBeanNameGenerator;

/**
 * @author javaquan
 */
public class QuanBeanNameGenerator extends AnnotationBeanNameGenerator {

    private static final char NON_COMPATIBLE_CLASS_SEPARATOR = '#';

    @Override
    public String generateBeanName(BeanDefinition beanDefinition, BeanDefinitionRegistry beanDefinitionRegistry) {
        String beanClassName = super.generateBeanName(beanDefinition, beanDefinitionRegistry);
        return generateBeanName(0, beanClassName, beanClassName, beanDefinitionRegistry);
    }

    private String generateBeanName(int index, String originalBeanClassName, String beanClassName, BeanDefinitionRegistry beanDefinitionRegistry) {
        if (contains(beanClassName, beanDefinitionRegistry.getBeanDefinitionNames())) {
            int suffix = index + 1;
            StringBuffer sb = new StringBuffer();
            sb.append(originalBeanClassName).append(NON_COMPATIBLE_CLASS_SEPARATOR).append(suffix);
            return generateBeanName(suffix, originalBeanClassName, sb.toString(), beanDefinitionRegistry);
        }
        return beanClassName;
    }

    public boolean contains(String beanClassName, String[] beanDefinitionNames) {
        return indexOf(beanClassName, beanDefinitionNames) >= 0;
    }

    public int indexOf(String beanClassName, String[] beanDefinitionNames) {
        if (beanDefinitionNames == null || beanDefinitionNames.length == 0) {
            return 0;
        } else {
            for (int i = 0; i < beanDefinitionNames.length; i++) {
                if (beanClassName.equals(beanDefinitionNames[i])) {
                    return i;
                }
            }
        }
        return -1;
    }
}
