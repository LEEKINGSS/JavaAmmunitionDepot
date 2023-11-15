package cn.liking;

import cn.liking.mapper.UserMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * @author: Liking
 * @description 数据库测试类
 * @date: 2023/11/15 19:18
 */
@SpringBootTest
public class MyBatisPlusTest {
    @Autowired
    private UserMapper userMapper;

    @Test
    void testGetUserAll(){
        System.out.println(userMapper.selectList(null));
    }
}
