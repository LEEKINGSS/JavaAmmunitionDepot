package cn.liking.entity;

import com.baomidou.mybatisplus.annotation.TableField;
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
