package cn.javaquan.tools.id.service.impl;

import cn.javaquan.tools.id.autoconfigure.IdProperties;
import cn.javaquan.tools.id.service.IdService;
import cn.javaquan.tools.id.util.SnowflakeIdWorker;

/**
 * 生成ID的接口实现.
 *
 * @author javaquan
 * @since 2.2.0
 */
public class IdServiceImpl implements IdService {

    private final IdProperties properties;

    /**
     * 构造方法注入实例.
     * @param properties 配置
     */
    public IdServiceImpl(IdProperties properties) {
        this.properties = properties;
    }

    @Override
    public long id() {
        return id(properties.determineDefaultWorkerId(), properties.determineDefaultDatacenterId());
    }

    @Override
    public long id(long type) {
        return id(properties.determineDefaultWorkerId(), type);
    }

    @Override
    public long id(long workerId, long datacenterId) {
        SnowflakeIdWorker snowflakeIdWorker = SnowflakeIdWorker.getSnowflakeIdWorkerInstance(workerId, datacenterId);
        return snowflakeIdWorker.nextId();
    }

}
