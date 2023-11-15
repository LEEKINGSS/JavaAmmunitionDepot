package cn.liking;

import cn.liking.entity.User;
import cn.liking.mapper.UserMapper;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

/**
 * @author: Liking
 * @description 条件查询进阶
 * @date: 2023/11/15 20:47
 */
@SpringBootTest(classes = MyBatisPlusOperationApplication.class)
public class SelectPlusTest {
    @Autowired
    private UserMapper userMapper;

    /**
     * lambda格式按条件查询
     */
    @Test
    void testSelectByLambda() {
        //查询年龄小于18岁的用户
        List<User> userList = userMapper.selectList(new LambdaQueryWrapper<User>().lt(User::getAge, 18));
        //并且关系：10到30岁之间
        List<User> userList1 = userMapper.selectList(new LambdaQueryWrapper<User>().between(User::getAge, 10, 30));
        //或者关系：小于10岁或者大于30岁
        List<User> userList2 = userMapper.selectList(new LambdaQueryWrapper<User>().lt(User::getAge, 10).or().gt(User::getAge, 30));
        //查询名字中包含liking的用户
        List<User> userList3 = userMapper.selectList(new LambdaQueryWrapper<User>().like(User::getName, "liking"));
        //NULL值处理
        Integer minAge = 10;  //将来有用户传递进来,此处简化成直接定义变量了
        Integer maxAge = null;  //将来有用户传递进来,此处简化成直接定义变量了
        List<User> userList4 = userMapper.selectList(new LambdaQueryWrapper<User>()
                //参数1：如果表达式为true，那么查询才使用该条件
                .gt(minAge != null, User::getAge, minAge)
                .lt(maxAge != null, User::getAge, maxAge));
        //用户登录(eq匹配)
        String name = "liking";
        String password = "123.com";
        List<User> userList5 = userMapper.selectList(new LambdaQueryWrapper<User>()
                .eq(User::getName, name)
                .eq(User::getPassword, password));
    }



}
