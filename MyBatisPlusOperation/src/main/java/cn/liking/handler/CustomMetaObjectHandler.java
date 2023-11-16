package cn.liking.handler;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import org.apache.ibatis.reflection.MetaObject;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * 自定义 元对象处理器
 * 用于处理新增和修改时，自动填充数据
 *
 * @author liking
 * @date 2023/9/23 15:15
 */
@Component
public class CustomMetaObjectHandler implements MetaObjectHandler {
    /**
     * 插入时填充
     *
     * @param metaObject 元数据
     */
    @Override
    public void insertFill(MetaObject metaObject) {
        Date date = new Date();
        // 创建时间字段自动填充
        this.fillStrategy(metaObject, "createTime", date);
        // 修改时间字段自动填充
        this.fillStrategy(metaObject, "updateTime", date);
    }

    /**
     * 修改时填充
     *
     * @param metaObject 元数据
     */
    @Override
    public void updateFill(MetaObject metaObject) {
        Date date = new Date();
        // 修改时间字段自动填充
        this.fillStrategy(metaObject, "updateTime", date);
    }
}
