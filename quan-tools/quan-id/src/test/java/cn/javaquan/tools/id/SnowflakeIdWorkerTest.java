package cn.javaquan.tools.id;

import cn.javaquan.tools.id.util.SnowflakeIdWorker;
import cn.javaquan.tools.id.util.SnowflakeIdWorker;

public class SnowflakeIdWorkerTest {

    public static void main(String[] args) throws InterruptedException {

        SnowflakeIdWorker snowflakeIdWorker = SnowflakeIdWorker.getSnowflakeIdWorkerInstance(0L, 1L);
        System.out.println(snowflakeIdWorker.nextId());
        SnowflakeIdWorker snowflakeIdWorker1 = SnowflakeIdWorker.getSnowflakeIdWorkerInstance(0L, 1L);
        System.out.println(snowflakeIdWorker1.nextId());
    }
}
