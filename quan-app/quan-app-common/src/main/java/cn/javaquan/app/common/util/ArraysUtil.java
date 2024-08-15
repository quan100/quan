package cn.javaquan.app.common.util;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * 集合工具类.
 *
 * @author javaquan
 * @since 1.0.0
 */
public final class ArraysUtil {

    /**
     * 私有构造器.
     */
    private ArraysUtil() {
    }

    /**
     * 合并2个集合.
     * @param args1 待合并的集合
     * @param args2 待合并的集合
     * @param <T> 约定合并参数的数据类型
     * @return 合并后的集合
     */
    public static <T> List<T> merge(List<T> args1, List<T> args2) {
        if (Validate.isEmpty(args1)) {
            return args2;
        }
        if (Validate.isEmpty(args2)) {
            return args1;
        }
        return Stream.concat(args1.stream(), args2.stream()).collect(Collectors.toList());
    }

}
