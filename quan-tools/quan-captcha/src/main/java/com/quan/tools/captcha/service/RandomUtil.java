package com.quan.tools.captcha.service;

import java.util.SplittableRandom;

/**
 * 随机数生成工具类
 *
 * @author javaquan
 */
public class RandomUtil {

    /**
     * 根据给定的字符串随机
     *
     * @param characters
     * @param randomNumberBound
     * @return
     */
    public static String generate(String characters, int randomNumberBound) {
        SplittableRandom random = new SplittableRandom();
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < randomNumberBound; i++) {
            char character = characters.charAt(random.nextInt(0, characters.length()));
            sb.append(character);
        }
        return sb.toString();
    }
}
