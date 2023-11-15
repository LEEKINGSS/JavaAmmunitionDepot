package cn.liking;

import cn.liking.entity.User;
import cn.liking.mapper.UserMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * @author: Liking
 * @description 数据库测试类
 * @date: 2023/11/15 19:18
 */
@SpringBootTest(classes = MyBatisPlusOperationApplication.class)
public class MyBatisPlusTest {
    @Autowired
    private UserMapper userMapper;



    /**
     * 保存数据
     */
    @Test
    void testSave(){
        User user = new User();
        user.setName("liking");
        user.setPassword("123.com");
        user.setAge(12);
        user.setTel("4006184000");
        userMapper.insert(user);
    }

    /**
     * 删除数据
     */
    @Test
    void testDelete(){
        userMapper.deleteById(1724766644099633154L);
    }

    /**
     * 修改数据
     */
    @Test
    void testUpdate(){
        User user = new User();
        user.setId(1L);
        user.setName("liking");
        user.setPassword("123.com");
        user.setAge(12);
        user.setTel("4006184000");
        userMapper.updateById(user);
    }

    /**
     * 查询数据
     */
    @Test
    void testSelect(){
        System.out.println(userMapper.selectById(1L));
    }

    /**
     * 查询所有数据
     */
    @Test
    void testGetUserAll(){
        System.out.println(userMapper.selectList(null));
    }

    /**
     * 分页查询[1]
     */
    @Test
    void testSelectPage(){
        //1 创建IPage分页对象,设置分页参数
        IPage<User> page=new Page<>(1,3);
        //2 执行分页查询
        userMapper.selectPage(page,null);
        //3 获取分页结果
        System.out.println("当前页码值："+page.getCurrent());
        System.out.println("每页显示数："+page.getSize());
        System.out.println("总页数："+page.getPages());
        System.out.println("总条数："+page.getTotal());
        System.out.println("当前页数据："+page.getRecords());
    }
}
