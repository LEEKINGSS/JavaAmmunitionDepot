package cn.liking.controller;

import cn.liking.Task.RunTask01;
import cn.liking.Task.RunTask02;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.Trigger;
import org.springframework.scheduling.TriggerContext;
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;
import org.springframework.scheduling.support.CronTrigger;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.Date;
import java.util.concurrent.ScheduledFuture;


/**
 * @author liking
 * 动态创建定时任务
 */
@Slf4j
@RestController
@RequestMapping("/task")
public class DynamicTaskController {
    @Resource
    private ThreadPoolTaskScheduler taskScheduler;

    private ScheduledFuture<?> scheduledFuture1;

    private ScheduledFuture<?> scheduledFuture2;

    @Value("${timing-task.cron1}")
    private String cronStr1;

    @Value("${timing-task.cron2}")
    private String cronStr2;

    /**
     * 开启定时任务1
     */
    @RequestMapping("/start1")
    public void startTask1() {
        scheduledFuture1 = taskScheduler.schedule(new RunTask01(), triggerContext -> new CronTrigger(cronStr1).nextExecutionTime(triggerContext));
        log.info("start timed task1 success ..");
    }

    /**
     * 开启定时任务2
     */
    @RequestMapping("/start2")
    public void startTask2() {
        scheduledFuture2 = taskScheduler.schedule(new RunTask02(), triggerContext -> new CronTrigger(cronStr2).nextExecutionTime(triggerContext));
        log.info("start timed task2 success ..");
    }

    /**
     * 停止定时任务1
     */
    @RequestMapping("/stop1")
    public void stopTask1() {
        Boolean result = null;
        if (scheduledFuture1 != null) {
            result = scheduledFuture1.cancel(true);
        }
        log.info("stop timed task1 result: " + result);
    }

    /**
     * 停止定时任务2
     * @return
     */
    @RequestMapping("/stop2")
    public void stopTask2() {
        Boolean result = null;
        if (scheduledFuture2 != null) {
            result = scheduledFuture2.cancel(true);
        }
        log.info("stop timed task2 result: " + result);
    }


    /**
     * 修改定时任务
     */
    @RequestMapping("/modify")
    public void modifyTask() {
        Boolean stopResult = null;
        // 停止定时任务
        if (scheduledFuture1 != null) {
            stopResult = scheduledFuture1.cancel(true);
        } else {
            log.info("modify task error -> scheduledFuture is null");
        }
        // 更换cron重新开启定时任务
        if (Boolean.TRUE.equals(stopResult)) {
            scheduledFuture1 = taskScheduler.schedule(new RunTask01(), triggerContext -> new CronTrigger(cronStr2).nextExecutionTime(triggerContext));
            log.info("modify task success ..");
        }
        log.info("modify task failed ..");
    }
}