package com.bw.mapper;
/*

import com.bw.entity.Fruit;
import com.mongodb.WriteResult;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Component;

import javax.management.Query;
*/

/**
 * Created by lenovo on 2017/7/19.
 */
//@Component
public class FruitMongodbMapper {

   /* @Autowired
    private MongoTemplate mongoTemplate;
*/
    /**
     * 创建对象
     *
     * @param fruit
     */
   /* public void saveUser(Fruit fruit) {
        mongoTemplate.save(fruit);
    }*/

    /**
     * 根据用户名查询对象
     *
     * @param type
     * @return
     */
   /* public Fruit findUserByUserName(String type) {
        Query query;
        query = new Query(Criteria.where("type").is(type));
        Fruit fruit;
        fruit = mongoTemplate.findOne(query, Fruit.class);
        return fruit;
    }

    *//**
     * 更新对象
     *
     * @param fruit
     *//*
    public void updateUser(Fruit fruit) {
        Query query = new Query(Criteria.where("id").is(fruit.getId()));
        Update update = new Update().set("userName", fruit.getId()).set("type", fruit.getType());
        //更新查询返回结果集的第一条
        WriteResult writeResult = mongoTemplate.updateFirst(query, update, Fruit.class);
        //更新查询返回结果集的所有
        // mongoTemplate.updateMulti(query,update,UserEntity.class);

    }*/
}