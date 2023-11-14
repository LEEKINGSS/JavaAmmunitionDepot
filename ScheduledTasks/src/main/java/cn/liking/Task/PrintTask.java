package cn.liking.Task;

import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * [1]实现定时任务类
 * @author liking
 * @version 1.0.0
 */

@Component
public class PrintTask {

    /**
     * 标注 @Async 的由自定义异步调用线程调度，没有标注 @Async 的由自定义任务调度线程调度。
     */
    private static final SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    @Scheduled(fixedRate = 3000)
    public void reportCurrentTime1() {
        System.out.println("线程1NOW：" + sdf.format(new Date()));
    }

    @Scheduled(fixedRate = 1000)
    public void reportCurrentTime2() {
        System.out.println("线程2NOW：" + sdf.format(new Date()));
    }

    /**
     * 开启异步并行定时任务
     */
    @Async
    @Scheduled(fixedRate = 2000)
    public void reportCurrentTime3() {
        System.out.println("线程3NOW：" + sdf.format(new Date()));
    }

}