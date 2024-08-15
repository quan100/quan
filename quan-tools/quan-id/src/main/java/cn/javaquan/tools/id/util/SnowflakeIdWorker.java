package cn.javaquan.tools.id.util;

/**
 * 雪花算法.
 *
 * @author javaquan
 * @since 2.2.0
 */
public class SnowflakeIdWorker {

    private final long twepoch = 1420041600000L;

    private final long workerIdBits = 5L;

    private final long datacenterIdBits = 5L;

    private final long maxWorkerId = 31L;

    private final long maxDatacenterId = 31L;

    private final long sequenceBits = 12L;

    private final long workerIdShift = 12L;

    private final long datacenterIdShift = 17L;

    private final long timestampLeftShift = 22L;

    private final long sequenceMask = 4095L;

    private long workerId;

    private long datacenterId;

    private long sequence = 0L;

    private long lastTimestamp = -1L;

    private static volatile SnowflakeIdWorker snowflakeIdWorkerInstance;

    /**
     * 获取算法实例.
     * @param workerId 机器id
     * @param datacenterId 数据id
     * @return 实例
     */
    public static SnowflakeIdWorker getSnowflakeIdWorkerInstance(long workerId, long datacenterId) {
        if (snowflakeIdWorkerInstance == null) {
            synchronized (SnowflakeIdWorker.class) {
                if (null == snowflakeIdWorkerInstance) {
                    snowflakeIdWorkerInstance = new SnowflakeIdWorker(workerId, datacenterId);
                }
            }
        }

        return snowflakeIdWorkerInstance;
    }

    /**
     * 构造雪花算法生成id实例.
     * @param workerId 机器id
     * @param datacenterId 数据id
     */
    public SnowflakeIdWorker(long workerId, long datacenterId) {
        if (workerId <= this.maxWorkerId && workerId >= 0L) {
            if (datacenterId <= this.maxDatacenterId && datacenterId >= 0L) {
                this.workerId = workerId;
                this.datacenterId = datacenterId;
            }
            else {
                throw new IllegalArgumentException(
                        String.format("datacenter Id can't be greater than %d or less than 0", this.maxDatacenterId));
            }
        }
        else {
            throw new IllegalArgumentException(
                    String.format("worker Id can't be greater than %d or less than 0", this.maxWorkerId));
        }
    }

    /**
     * 生成id.
     * @return id
     */
    public synchronized long nextId() {
        long timestamp = this.timeGen();
        if (timestamp < this.lastTimestamp) {
            throw new RuntimeException(
                    String.format("Clock moved backwards.  Refusing to generate id for %d milliseconds",
                            this.lastTimestamp - timestamp));
        }
        else {
            if (this.lastTimestamp == timestamp) {
                this.sequence = this.sequence + 1L & this.sequenceMask;
                if (this.sequence == 0L) {
                    timestamp = this.tilNextMillis(this.lastTimestamp);
                }
            }
            else {
                this.sequence = 0L;
            }

            this.lastTimestamp = timestamp;
            return timestamp - this.twepoch << this.timestampLeftShift | this.datacenterId << this.datacenterIdShift
                    | this.workerId << this.workerIdShift | this.sequence;
        }
    }

    /**
     * 阻塞到下一个毫秒，直到获得新的时间戳.
     * @param lastTimestamp 上次生成ID的时间截
     * @return 当前时间戳
     */
    @SuppressWarnings("all")
    protected long tilNextMillis(long lastTimestamp) {
        long timestamp;
        for (timestamp = this.timeGen(); timestamp <= lastTimestamp; timestamp = this.timeGen()) {
        }

        return timestamp;
    }

    /**
     * 返回以毫秒为单位的当前时间.
     * @return 当前时间(毫秒)
     */
    protected long timeGen() {
        return System.currentTimeMillis();
    }

}
