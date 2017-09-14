package com.bw.mapper;

import com.bw.entity.QQFriends;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import javax.transaction.Transactional;
import java.util.List;

/**
 * Created by lenovo on 2017/7/12.
 */
public interface FriendMapper extends JpaRepository<QQFriends,Integer>{

    /**
     * 查询好友列表
     */
    @Query(value="select f.id, f.qname,f.talks,f.message,f.logintime,f.dept_id,d.dname from qqfriends f,qqdept d where d.id=f.dept_id",nativeQuery = true)
    List<QQFriends> findAll();

    /**
     * 修改好友的信息
     * @Transactional注解 添加事物的注解,对查询超时的时设置
     *  @Modifying 他是API在执行增删改时需要加载@query()注解上的
     */
    @Transactional
    @Modifying
    @Query("update QQFriends f set f.qname = ?1,f.message=?2,f.talks=?3 ,f.logintime=?4,f.dept.id=?5 where f.id=?6")
    void  modifyById(String qname,String message,String talks,String logintime,Integer pid,Integer id);

    /**
     * 查询出一共有多少条数据==>直接调用count方法即可
     */




}
