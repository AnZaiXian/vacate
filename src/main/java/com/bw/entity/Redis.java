package com.bw.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.io.Serializable;

/**
 * Created by lenovo on 2017/7/13.
 * Redis存储对象是使用序列化，spring-data-redis已经将序列化的功能内置，
 * 不需要我们去管，我们只需要调用api就可以使用。SerialVersionUID字段对序列化扩展有用，
 * 为了以后扩展或者缩减字段时不会造成反序列化出错。
 * 注意加上无参的构造方法
 * 在创建接口来继承JpaRepository类,并且在这个类中什么都不用写
 */
@Data
@Entity
@NoArgsConstructor//次注解
public class Redis implements Serializable{

    @Id
    @GeneratedValue
    private int id;
    private String name;

    public Redis(int id, String name) {
        this.id = id;
        this.name = name;
    }


}
