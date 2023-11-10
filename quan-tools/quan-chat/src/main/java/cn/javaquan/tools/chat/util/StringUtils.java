package cn.javaquan.tools.chat.util;

/**
 * 字符串工具类
 *
 * @author javaquan
 */
public class StringUtils {

    /**
     * 首字母大写，其它字母小写
     *
     * @param content 需要转化的字符串
     */
    public static String toContent(String content) {
        char[] contents = content.toCharArray();
        for (int i = 0; i < contents.length; i++) {
            if (i == 0) {
                contents[0] = toUpperCase(contents[0]);
            } else {
                contents[i] = toLowerCase(contents[i]);
            }
        }
        return String.valueOf(contents);
    }

    /**
     * 字符转成大写
     *
     * @param c 需要转化的字符
     */
    public static char toUpperCase(char c) {
        return Character.toUpperCase(c);
    }

    /**
     * 字符转小写
     *
     * @param c
     * @return
     */
    public static char toLowerCase(char c) {
        return Character.toLowerCase(c);
    }

}
