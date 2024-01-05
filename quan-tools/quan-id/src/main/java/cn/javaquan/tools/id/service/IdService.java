package cn.javaquan.tools.id.service;

/**
 * 生成ID的接口
 *
 * @author javaquan
 * @since 2.2.0
 */
public interface IdService {

    /**
     * 生成唯一ID
     *
     * @return
     */
    long id();

    /**
     * 生成唯一ID
     *
     * @param type 业务领域
     * @return
     */
    long id(long type);

}
