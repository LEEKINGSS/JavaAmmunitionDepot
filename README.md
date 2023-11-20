# JavaAmmunitionDepot
该存储库为个人创建Java代码弹药库，主要存放日常学习工作中常用的工具类。

## ScheduledTasks库

**作用**：定时任务工具类

**基础**: 该库基于SpringBoot2.4.2.RELEASE版本

### 定时任务依赖：无

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

## MybatisPlusOperation库

**作用**：MybatisPlus操作数据库工具类

**基础**: 该库基于SpringBoot2.4.2.RELEASE版本

### 数据库连接池：
* 配置：application.yml文件中配置数据库连接池

### MybatisPlus依赖：
* 1.mysql连接依赖:mysql-connector-java
* 2.数据库连接池依赖:druid-spring-boot-starter
* 3.mybatisPlus依赖:mybatis-plus-boot-starter

### 1. MybatisPlus基本操作:
* 1.1. 增删改查-见测试类MyBatisPlusTest
* 1.2. 批量查询-见测试类MyBatisPlusTest
* 1.3. 条件查询-见测试类SelectPlusTest

### 2. MybatisPlus分页查询：
* 2.1. 配置类:
  * 2.1.1. 分页方式一：MybatisPlusConfig——mybatisPlusInterceptor()方法
  * 2.1.2. 分页方式二：PageQuery()返回类
* 2.2. 测试类：
  * 2.2.1 分页方式一：SelectPlusTest
  * 2.2.2 分页方式二：见Controller类中的分页查询

### 3. MybatisPlus乐观锁：
* 3.1. 配置类:MybatisPlusConfig——mpInterceptor()方法
* 3.2. 测试类：OptimisticLockerTest
* 3.3. 使用方式：需要在实体类中添加@Version注解。数据库中需要添加version字段，类型为int或者Integer

### 4. MybatisPlus逻辑删除：
* 4.1. 配置:在application.yml中配置逻辑删除的值
* 4.2. 使用方式:yml文件中的配置的逻辑字段需要与数据库中逻辑删除字段相同

### 5. MybatisPlus自动填充：
* 5.1. 案例：本库中使用的案例为创建时间和更新时间的自动填充
* 5.2. 配置: handler包下的CustomMetaObjectHandler类
* 5.3. 使用方式：需要在实体类中添加@TableField注解，且需要指定填充字段

### 6. MybatisPlus代码自动生成：
* 6.1. 依赖:
  * 6.1.1. 代码生成：mybatis-plus-generator
  * 6.1.2. 代码模板:velocity-engine-core
  * 6.1.3. 胡图工具:hutool-all
* 6.2. 配置:
  * 6.2.1. 控制器父类:BaseController
  * 6.2.2. 分页配置类：PageQuery
  * 6.2.3. 公共返回类:Response
  * 6.2.4. 代码模板类:controller.java.vm
* 6.3. 使用方式:
  * 生成类：CodeGenerator

### 7. Swagger调试接口：
* 7.1. 依赖:swagger-spring-boot-starter
* 7.2. 配置:SwaggerConfig类
* 7.3. 使用方式:启动项目后，访问http://localhost:8080/doc.html

### 8. MybatisPlus联合查询
* 8.1. 使用:
  * 8.1.1. 编写实体类的扩展VO类
  * 8.1.2. 重写page方法
  * 8.1.3. 使用Stream流将实体类转换为VO对象

