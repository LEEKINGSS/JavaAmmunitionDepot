package cn.liking.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.*;

//lombok

/**
 * @author liking
 * 用户实体类
 */


@Data
@TableName("user")
public class User {

    /**
     * AUTO(1):使用数据库id自增策略控制id生成
     * NONE(2):不使用id生成策略
     * INPUT(3):用户输入id
     * ASSIGN_ID(4):使用雪花算法生成id
     * ASSIGN_UUID(5):使用UUID生成id
     */
    @TableId(type = IdType.AUTO)
    private Long id;

    private String name;
    /**
     * 采用默认查询开放了更多的字段查看权限
     */
    @TableField(select = false)
    private String password;

    private Integer age;
    /**
     * 表字段与编码属性设计不对应时，使用@TableField注解
     */
    @TableField("tel")
    private String telephone;
    /**
     * 编码中添加了数据库中未定义的属性
     */
    @TableField(exist = false)
    private String address;

}
