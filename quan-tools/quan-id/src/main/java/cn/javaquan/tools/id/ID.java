package cn.javaquan.tools.id;

import cn.javaquan.tools.id.service.IdService;

/**
 * ID生成工具类
 *
 * @author javaquan
 * @since 2.2.0
 */
public class ID {

    private static IdService idService;

    public ID(IdService idService) {
        ID.idService = idService;
    }

    /**
     * 生成唯一ID
     *
     * @return
     */
    public static long id() {
        return idService.id();
    }

    /**
     * 生成唯一ID
     *
     * @param type 业务领域
     * @return
     */
    public static long id(long type) {
        return idService.id(type);
    }

    public static String genId() {
        return String.valueOf(id());
    }

    public static String genId(String type) {
        StringBuffer sb = new StringBuffer();
        sb.append(type);
        sb.append(id());
        return sb.toString();
    }
}
