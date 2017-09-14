package com.bw.mapper;

import com.bw.entity.Redis;
import org.springframework.cache.annotation.CacheConfig;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.jpa.repository.JpaRepository;

import javax.transaction.Transactional;

/**
 * Created by lenovo on 2017/7/12.
 */
@Transactional
@CacheConfig(cacheNames = "userMapper")
public interface ReadisMapper extends JpaRepository<Redis,Integer>{

    @Cacheable(key = "#p0")
    Redis findUserByName(String userName);

   /* @Cacheable()
    List<User> findAll();

    @Cacheable(key = "#p0")
    User findByUid(Integer uid);

    User findUserByUnameAndUpwd(String uname,String upwd);

    void deleteByUid(Integer uid);


    @CachePut(key = "#p0")
    @Modifying
    @Query("update User u set u.uname=?1,u.upwd=?2,u.sex=?3,u.age=?4,u.address=?5 where uid=?6")
    void update(String uname, String upwd, String sex, Integer age, String address, Integer uid);
*/


}
