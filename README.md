# JavaAmmunitionDepot
该存储库为个人创建Java代码弹药库，主要存放日常学习工作中常用的工具类。

## ScheduledTasks库

**作用**：定时任务工具类
### 第一种定时方式[1]:注释方式

**使用方式**:
* 1.使用注释方式启动定时任务，需要在启动类上添加@EnableScheduling注解
* 2.使用注释方式启动定时任务，需要在定时任务类上添加@Component注解
* 3.使用注释方式启动定时任务，需要在定时任务方法上添加@Scheduled注解
* 4.使用注释方式启动定时任务，需要在定时任务方法上添加@Scheduled注解，且需要指定cron表达式
* 5.默认方式只支持单线程，如果想要支持多定时任务并行执行，需要额外添加配置类
* 6.异步任务须加入@Async注解，支持自定义配置

**特点**:
- 1.使用注释方式启动定时任务
- 2.任务无序手动开启，可自动执行

**缺点**:
* 1.定时任务无法停止

### 第二种定时方式[2]:ThreadPoolTaskScheduler方式

**使用方式**:
* 1.使用ThreadPoolTaskScheduler方式启动定时任务,需要使用编程方式而不是注解启动
* 2.定时任务需要重新Runable类的run方法

**特点**:
* 1.定时任务可以动态创建修改
* 2.定时任务可以动态停止

**缺点**:
* 1.定时任务无法自动执行,须借助注解方式
* 2.定时任务不能具体到方法，只能具体到某个类。


