package com.bw.mapper;

import com.bw.entity.QQDept;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by lenovo on 2017/7/13.
 * QQ好友的分类
 *
 */
public interface DeptMapper extends JpaRepository<QQDept,Integer>{

}
