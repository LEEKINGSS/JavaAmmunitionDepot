package cn.liking;

import cn.liking.entity.User;
import cn.liking.mapper.UserMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Arrays;

/**
 * @author: Liking
 * @description 批量操作类
 * @date: 2023/11/16 13:09
 */
@SpringBootTest(classes = MyBatisPlusOperationApplication.class)
public class BatchOperationsTest {

    @Autowired
    private UserMapper userMapper;

    /**
     * mybatis-plus批量操作
     */
    @Test
    public void batchOperations() {
        //批量删除
        userMapper.deleteBatchIds(Arrays.asList(1L, 2L, 3L, 4L));
        //批量查询
        userMapper.selectBatchIds(Arrays.asList(1L, 2L, 3L, 4L)).forEach(System.out::println);
    }

}
