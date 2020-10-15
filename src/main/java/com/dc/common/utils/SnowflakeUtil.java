package com.dc.common.utils;

import com.dc.common.exception.UtilsException;
import lombok.extern.slf4j.Slf4j;

/**
 * @Author zhuangcy
 * @Description
 * @Date 2020/9/15 12:37
 */
@Slf4j
public class SnowflakeUtil {
    /**
     * 起始时间毫秒值
     */
    private final static long startTimestamp = 1600145089231L;

    /**
     * 每一部分的占用位数
     */
    private final static long workerIdBits = 10;
    private final static long sequenceBits = 12;
    /**
     * 每一部分的最大值
     */
    private final static long maxWorkerId = -1L ^ (-1L << workerIdBits);// 2^(10-1)
    private final static long maxSequence = -1L ^ (-1L << sequenceBits);//2^(12-1)
    /**
     * 每一部分的位移值
     */
    private final static long workerIdShift = sequenceBits;
    private final static long timestampShift = (sequenceBits + workerIdBits);

    /**
     * 工作进程id
     */
    private volatile long workerId;// 轻量级同步
    /**
     * 序列号
     */
    private long sequence = 0;
    /**
     * 上一次时间戳
     */
    private long lastTimestamp = 0;

    /**
     * 创建实体
     *
     * @param workerId 工作进程id
     */
    public SnowflakeUtil(long workerId) {
        // 校验
        if (workerId > maxWorkerId || workerId < 0) {
            throw new UtilsException(String.format("worker Id can't be greater than %s or less than 0", maxWorkerId));
        }
        this.workerId = workerId;
    }

    private SnowflakeUtil() {
        long workerId = Thread.currentThread().getId();
        log.info("workerId=" + workerId);
        // 校验
        if (workerId > maxWorkerId || workerId < 0) {
            throw new UtilsException(String.format("worker Id can't be greater than %s or less than 0", maxWorkerId));
        }
        this.workerId = workerId;
    }

    public static SnowflakeUtil getInstance() {
        return new SnowflakeUtil();
    }

    /**
     * 获取Id
     *
     * @return
     */
    public long nextId() {
        long timestamp = getNowTimestamp();
        // 检验时间戳
        if (timestamp < lastTimestamp) {
            throw new UtilsException(String.format("Clock moved backwards. Refusing to generate id for %s milliseconds", lastTimestamp - timestamp));
        }
        if (timestamp == lastTimestamp) {
            sequence = (sequence + 1) & maxSequence;
            if (sequence == 0L) {
                timestamp = getNextTimestamp();
            }
        } else {
            sequence = 0L;
        }
        lastTimestamp = timestamp;
        return (timestamp - startTimestamp) << timestampShift   //时间戳部分
                | (workerId) << workerIdShift  // 工作进程id部分
                | (sequence); //序列号
    }

    /**
     * 校验时间戳
     *
     * @return
     */
    private long getNextTimestamp() {
        long timestamp = getNowTimestamp();
        while (timestamp < lastTimestamp) {
            timestamp = getNowTimestamp();
        }
        return timestamp;
    }

    /**
     * 获取当前时间戳
     *
     * @return
     */
    private long getNowTimestamp() {
        return System.currentTimeMillis();
    }

//    public static void main(String[] args) {
//        HashMap<String, Object> map = new HashMap<>();
//        SnowflakeUtil instance = getInstance();
//        for (int i = 0; i < 10000; i++) {
//            String id = ""+ instance.nextId();
//            if (map.containsKey(id)){
//                System.out.println(id);
//            }
//            map.put(id,id);
//        }
//        System.out.println(map.size());
//    }
}
