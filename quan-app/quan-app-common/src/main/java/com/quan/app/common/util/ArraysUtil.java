package com.quan.app.common.util;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class ArraysUtil {

    /**
     * 合并2个集合
     *
     * @param args1
     * @param args2
     * @param <T>
     * @return
     */
    public static <T> List merge(List<T> args1, List<T> args2) {
        if (Validate.isEmpty(args1)) {
            return args2;
        }
        if (Validate.isEmpty(args2)) {
            return args1;
        }
        return Stream.concat(args1.stream(), args2.stream()).collect(Collectors.toList());
    }
}
