package cn.liking.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * @Description: Person实体类
 * @Author: liking
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
@Document(value="person")
public class Person {

    private ObjectId id;
    private String name;
    private int age;
    private String address;
    
}