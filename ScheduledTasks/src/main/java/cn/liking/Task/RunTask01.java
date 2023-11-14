package cn.liking.Task;

import lombok.extern.slf4j.Slf4j;

import java.util.Date;

/**
 * @author liking
 * [2]实现定时任务类
 */
@Slf4j
public class RunTask01 implements Runnable {
        @Override
        public void run() {
            log.info(Thread.currentThread().getName() + "|schedule task01" + "|" + new Date().toLocaleString());
        }
    }