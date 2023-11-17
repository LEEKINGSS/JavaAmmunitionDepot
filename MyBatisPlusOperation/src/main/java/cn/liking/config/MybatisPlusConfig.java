package cn.liking.config;

import com.baomidou.mybatisplus.annotation.DbType;
import com.baomidou.mybatisplus.extension.plugins.MybatisPlusInterceptor;
import com.baomidou.mybatisplus.extension.plugins.inner.OptimisticLockerInnerInterceptor;
import com.baomidou.mybatisplus.extension.plugins.inner.PaginationInnerInterceptor;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * MybatisPlus配置类
 *
 * @author liking
 */
@Configuration
@MapperScan("cn.liking.mapper")
public class MybatisPlusConfig {

    /**
     * 注册MybatisPlus分页插件[1]
     */
    @Bean
    public MybatisPlusInterceptor mybatisPlusInterceptor() {
        //1 创建MybatisPlusInterceptor拦截器对象
        MybatisPlusInterceptor mpInterceptor = new MybatisPlusInterceptor();
        //2 添加分页拦截器
        PaginationInnerInterceptor paginationInnerInterceptor = new PaginationInnerInterceptor();
        //设置分页拦截器的数据库类型为 MySQL，这样分页拦截器会根据 MySQL 的语法来生成分页 SQL。
        paginationInnerInterceptor.setDbType(DbType.MYSQL);
        // 设置分页拦截器的溢出处理策略为 true，表示当请求的页码超出总页数时，会自动返回最后一页的数据。
        paginationInnerInterceptor.setOverflow(true);
        mpInterceptor.addInnerInterceptor(paginationInnerInterceptor);
        return mpInterceptor;
    }

    /**
     * 配置乐观锁拦截器实现锁机制对应的动态SQL语句拼装
     */
    @Bean
    public MybatisPlusInterceptor mpInterceptor() {
        //1.定义Mp拦截器
        MybatisPlusInterceptor mpInterceptor = new MybatisPlusInterceptor();

        //2.添加乐观锁拦截器
        mpInterceptor.addInnerInterceptor(new OptimisticLockerInnerInterceptor());

        return mpInterceptor;
    }
}