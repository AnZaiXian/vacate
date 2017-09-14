package com.bw.mapper;

import com.bw.entity.JYUser;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by lenovo on 2017/7/12.
 * 用户名校验
 */
public interface UserMapper extends JpaRepository<JYUser,Integer>{

    //根据用户名和密码查询用户,登录
    JYUser findByNameAndPwd(String name, String pwd);
}
