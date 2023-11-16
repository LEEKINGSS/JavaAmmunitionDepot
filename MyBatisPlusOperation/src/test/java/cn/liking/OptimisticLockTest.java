package cn.liking;

import cn.liking.entity.User;
import cn.liking.mapper.UserMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * @author: Liking
 * @description 乐观锁测试方法
 * @date: 2023/11/16 13:28
 */
@SpringBootTest(classes = MyBatisPlusOperationApplication.class)
public class OptimisticLockTest {

    @Autowired
    private UserMapper userMapper;

    /**
     * 乐观锁测试
     */
    @Test
    public void optimisticLock() {
        /*
        User user = new User();
        user.setId(3L);
        user.setName("Jock666");
        user.setVersion(1);
        userMapper.updateById(user);
        */
        //1.先通过要修改的数据id将当前数据查询出来
        // User user = userDao.selectById(3L);
        //2.将要修改的属性逐一设置进去
        //user.setName("Jock888");
        //userDao.updateById(user);

        //1.先通过要修改的数据id将当前数据查询出来
        User user = userMapper.selectById(3L);     //version=3
        System.out.println(user);
        User user2 = userMapper.selectById(3L);    //version=3
        System.out.println(user2);
        user2.setName("Jock aaa");
        userMapper.updateById(user2);              //version=>4
        System.out.println(userMapper.selectById(3L));
        user.setName("Jock bbb");
        userMapper.updateById(user);               //verion=3条件不成立，无法进行修改
    }
}
