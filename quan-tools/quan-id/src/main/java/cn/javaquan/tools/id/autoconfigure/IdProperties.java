package cn.javaquan.tools.id.autoconfigure;

import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * id生成器配置
 *
 * @author javaquan
 * @since 2.2.0
 */
@ConfigurationProperties(prefix = "quan.id")
public class IdProperties {

    /**
     * 默认的工作组编号
     */
    private final static Long DEFAULT_WORKER_ID = 0L;

    /**
     * 默认的数据中心编号
     */
    private final static Long DEFAULT_DATACENTER_ID = 1L;

    /**
     * 工作组编号
     */
    private Long workerId;

    /**
     * 数据中心编号
     */
    private Long datacenterId;

    public Long getWorkerId() {
        return workerId;
    }

    public void setWorkerId(Long workerId) {
        this.workerId = workerId;
    }

    public Long determineDefaultWorkerId() {
        if (null != this.workerId) {
            return this.workerId;
        }
        return DEFAULT_WORKER_ID;
    }

    public Long getDatacenterId() {
        return datacenterId;
    }

    public void setDatacenterId(Long datacenterId) {
        this.datacenterId = datacenterId;
    }

    public Long determineDefaultDatacenterId() {
        if (null != this.datacenterId) {
            return this.datacenterId;
        }
        return DEFAULT_DATACENTER_ID;
    }
}
