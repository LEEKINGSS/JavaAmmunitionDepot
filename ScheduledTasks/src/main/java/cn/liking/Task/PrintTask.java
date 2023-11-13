package cn.liking.Task;

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
    private static final SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    @Scheduled(fixedRate = 3000)
    public void reportCurrentTime() {
        System.out.println("NOW：" + sdf.format(new Date()));
    }
}