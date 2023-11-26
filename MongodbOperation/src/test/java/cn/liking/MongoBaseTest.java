package cn.liking;

import cn.liking.entity.Person;
import org.bson.types.ObjectId;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;

import java.util.List;

/**
 * @author: Liking
 * @description mongodb基础CRUD操作
 * @date: 2023/11/26 15:19
 */
@SpringBootTest(classes = MongodbOperationApplication.class)
public class MongoBaseTest {
    /**
     * SpringData-mongodb操作
     *    1、配置实体类
     *    2、实体类上配置注解（配置集合和对象间的映射关系）
     *    3、注入MongoTemplate对象
     *    4、调用对象方法，完成数据库操作
     */
    @Autowired
    private MongoTemplate mongoTemplate;

    /**
     * 保存对象
     */
    @Test
    public void testSave() {
        for (int i = 0; i < 10; i++) {
            Person person = new Person();
            person.setId(ObjectId.get()); //ObjectId.get()：获取一个唯一主键字符串
            person.setName("张三"+i);
            person.setAddress("北京顺义"+i);
            person.setAge(18+i);
            mongoTemplate.save(person);
        }
    }

    /**
     * 查询所有操作
     */
    @Test
    public void testFindAll() {
        List<Person> list = mongoTemplate.findAll(Person.class);
        for (Person person : list) {
            System.out.println(person);
        }
    }

    /**
     * 根据条件查询操作
     */
    @Test
    public void testFind() {
        //查询年龄小于20的所有人
        Query query = new Query(Criteria.where("age").lt(20)); //查询条件对象
        //查询
        List<Person> list = mongoTemplate.find(query, Person.class);

        for (Person person : list) {
            System.out.println(person);
        }
    }

    /**
     * 分页查询操作
     */
    @Test
    public void testPage() {
        Criteria criteria = Criteria.where("age").lt(30);
        //1、查询总数
        Query queryCount = new Query(criteria);
        long count = mongoTemplate.count(queryCount, Person.class);
        System.out.println(count);
        //2、查询当前页的数据列表, 查询第二页，每页查询2条
        Query queryLimit = new Query(criteria)
                .limit(2)//设置每页查询条数
                .skip(2) ; //开启查询的条数 （page-1）*size
        List<Person> list = mongoTemplate.find(queryLimit, Person.class);
        for (Person person : list) {
            System.out.println(person);
        }
    }

    /**
     * 根据id更新年龄操作
     */
    @Test
    public void testUpdate() {
        //1、条件
        Query query = Query.query(Criteria.where("id").is("5fe404c26a787e3b50d8d5ad"));
        //2、更新的数据
        Update update = new Update();
        update.set("age", 20);
        mongoTemplate.updateFirst(query, update, Person.class);
    }

    /**
     * 删除操作
     */
    @Test
    public void testRemove() {
        Query query = Query.query(Criteria.where("id").is("5fe404c26a787e3b50d8d5ad"));
        mongoTemplate.remove(query, Person.class);
    }

}
