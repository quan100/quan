package cn.javaquan.tools.id.service;

/**
 * 生成ID的接口.
 *
 * @author javaquan
 * @since 2.2.0
 */
public interface IdService {

    /**
     * 生成唯一ID.
     * @return id
     */
    long id();

    /**
     * 生成唯一ID.
     * @param type 业务领域
     * @return id
     */
    long id(long type);

    /**
     * 生成唯一ID.
     * @param workerId 机器id
     * @param datacenterId 数据id
     * @return id
     */
    long id(long workerId, long datacenterId);

}
