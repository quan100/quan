package cn.javaquan.tools.id.service.impl;

import cn.javaquan.tools.id.autoconfigure.IdProperties;
import cn.javaquan.tools.id.service.IdService;
import cn.javaquan.tools.id.util.SnowflakeIdWorker;
import cn.javaquan.tools.id.autoconfigure.IdProperties;
import cn.javaquan.tools.id.service.IdService;
import cn.javaquan.tools.id.util.SnowflakeIdWorker;

/**
 * 生成ID的接口实现
 *
 * @author javaquan
 * @since 2.2.0
 */
public class IdServiceImpl implements IdService {

    private final IdProperties properties;

    public IdServiceImpl(IdProperties properties) {
        this.properties = properties;
    }

    /**
     * 生成唯一ID
     *
     * @return
     */
    @Override
    public long id() {
        return id(properties.determineDefaultWorkerId(), properties.determineDefaultDatacenterId());
    }

    /**
     * 生成业务编码
     *
     * @param type 业务领域
     * @return
     */
    @Override
    public long id(long type) {
        return id(type, properties.determineDefaultDatacenterId());
    }

    /**
     * 生成ID
     * 基于雪花算法
     *
     * @param workerId
     * @param datacenterId
     * @return
     */
    private long id(long workerId, long datacenterId) {
        SnowflakeIdWorker snowflakeIdWorker = SnowflakeIdWorker.getSnowflakeIdWorkerInstance(workerId, datacenterId);
        return snowflakeIdWorker.nextId();
    }

}
