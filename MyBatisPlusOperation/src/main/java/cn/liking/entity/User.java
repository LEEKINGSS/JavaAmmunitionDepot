package cn.liking.entity;

import lombok.*;

//lombok

/**
 * @author liking
 * 用户实体类
 */
@Data
public class User {
    private Long id;
    private String name;
    private String password;
    private Integer age;
    private String tel;
}
