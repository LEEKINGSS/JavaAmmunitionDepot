package cn.liking;

import cn.liking.RedisOperationApplication;
import cn.liking.common.RedisUtil;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;

/**
 * Redis基础操作测试类
 * @author liking
 */

@SpringBootTest(classes = RedisOperationApplication.class)
public class RedisBaseTest {
    @Autowired
    private RedisUtil redisUtil;

    /**
     * 操作String类型数据
     */
    @Test
    public void testString() {
        // 设置值
        redisUtil.set("name", "liking");
        // 获取值
        System.out.println(redisUtil.get("name"));
        // 设置值并设置过期时间
        redisUtil.set("age", 18, 10);
        // 更多Redis操作见RedisUtil类
    }
}
