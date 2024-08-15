package cn.javaquan.tools.captcha.service;

import java.util.SplittableRandom;

/**
 * 随机数生成工具类.
 *
 * @author javaquan
 * @since 1.0.0
 */
public final class RandomUtil {

    /**
     * 私有构造方法.
     */
    private RandomUtil() {
    }

    /**
     * 根据给定的字符串随机.
     * @param characters 字符串
     * @param randomNumberBound 随机数范围
     * @return 随机字符串
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
