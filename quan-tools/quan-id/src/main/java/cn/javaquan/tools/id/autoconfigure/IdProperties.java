package cn.javaquan.tools.id.autoconfigure;

import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * id生成器配置.
 *
 * @author javaquan
 * @since 2.2.0
 */
@ConfigurationProperties(prefix = "quan.id")
public class IdProperties {

    /**
     * 默认的工作组编号.
     */
    private static final Long DEFAULT_WORKER_ID = 0L;

    /**
     * 默认的数据中心编号.
     */
    private static final Long DEFAULT_DATACENTER_ID = 1L;

    /**
     * 工作组编号.
     */
    private Long workerId;

    /**
     * 数据中心编号.
     */
    private Long datacenterId;

    /**
     * 获取工作组编号.
     * @return 工作组编号.
     */
    public Long getWorkerId() {
        return this.workerId;
    }

    /**
     * 设置工作组编号.
     * @param workerId 工作组编号.
     */
    public void setWorkerId(Long workerId) {
        this.workerId = workerId;
    }

    /**
     * 确定默认工作组编号.
     * @return 默认工作组编号.
     */
    public Long determineDefaultWorkerId() {
        if (null != this.workerId) {
            return this.workerId;
        }
        return DEFAULT_WORKER_ID;
    }

    /**
     * 获取数据中心编号.
     * @return 数据中心编号.
     */
    public Long getDatacenterId() {
        return this.datacenterId;
    }

    /**
     * 设置数据中心编号.
     * @param datacenterId 数据中心编号.
     */
    public void setDatacenterId(Long datacenterId) {
        this.datacenterId = datacenterId;
    }

    /**
     * 确定默认数据中心编号.
     * @return 默认数据中心编号.
     */
    public Long determineDefaultDatacenterId() {
        if (null != this.datacenterId) {
            return this.datacenterId;
        }
        return DEFAULT_DATACENTER_ID;
    }

}
