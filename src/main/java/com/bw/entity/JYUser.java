package com.bw.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;


/**
 * Created by lenovo on 2017/7/12.
 * 自动生成表用户表,用MD5进行注册加密,然后在登录时进行解密
 * 解密需要一个插件,所以在登录时只能用MD5,在将用户出来的密码进行再次加密
 * 将2次加密的密码与数据库查询出来的密码进行比价,因为MD5加密技术,他的加密的
 * 128位字节码是一样的
 */
@NoArgsConstructor
@Data
@Entity
public class JYUser {

    @Id
    @GeneratedValue
    private Integer id;
    private String name;
    private String pwd;
    private String sid;
/*
    public String getSid() {
        return sid;
    }

    public void setSid(String sid) {
        this.sid = sid;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPwd() {
        return pwd;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd;
    }*/

    public JYUser(String name, String pwd) {
        this.name = name;
        this.pwd = pwd;
    }

    /*public JYUser(){super();}*/

    /*@Override
    public String toString() {
        return "JYUser{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", pwd='" + pwd + '\'' +", sid='" + sid +
         '}';
    }*/
}
