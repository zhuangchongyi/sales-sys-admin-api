package com.dc.framework.async;

import lombok.extern.slf4j.Slf4j;

import java.util.TimerTask;

/**
 * 异步工厂（产生任务用）
 */
@Slf4j
public class AsyncFactory {
    /**
     * 测试异步任务
     */
    public static TimerTask testTimerTask(String msg) {
        return new TimerTask() {
            @Override
            public void run() {
                log.info("异步消息 == " + msg);
            }
        };
    }
}
