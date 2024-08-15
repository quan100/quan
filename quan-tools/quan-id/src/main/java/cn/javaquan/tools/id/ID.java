package cn.javaquan.tools.id;

import cn.javaquan.tools.id.service.IdService;

/**
 * ID生成工具类.
 *
 * @author javaquan
 * @since 2.2.0
 */
public class ID {

    private static IdService idService;

    /**
     * 注入静态的id生成业务服务.
     * @param idService id生成器
     */
    public ID(IdService idService) {
        ID.idService = idService;
    }

    /**
     * 生成唯一ID.
     * @return id
     */
    public static long id() {
        return idService.id();
    }

    /**
     * 生成唯一ID.
     * @param type 业务领域
     * @return id
     */
    public static long id(long type) {
        return idService.id(type);
    }

    /**
     * 生成唯一ID.
     * @param workerId 机器id
     * @param datacenterId 数据id
     * @return id
     */
    public static long id(long workerId, long datacenterId) {
        return idService.id(workerId, datacenterId);
    }

    /**
     * 生成字符串格式ID.
     * @return id
     */
    public static String genId() {
        return String.valueOf(id());
    }

    /**
     * 生成字符串格式ID.
     * @param type 业务领域
     * @return id
     */
    public static String genId(String type) {
        StringBuffer sb = new StringBuffer();
        sb.append(type);
        sb.append(id());
        return sb.toString();
    }

}
