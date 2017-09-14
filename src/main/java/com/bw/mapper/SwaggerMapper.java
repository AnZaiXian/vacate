package com.bw.mapper;

import com.bw.entity.SwaggerUser;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by lenovo on 2017/7/20.
 * Swagger2
 */
public interface SwaggerMapper extends JpaRepository<SwaggerUser,Long>{

}
