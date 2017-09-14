package com.bw.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.io.Serializable;

/**
 * Created by lenovo on 2017/7/13.
 * QQ角色分类
 *
 */
@NoArgsConstructor
@Data
@Entity
public class QQDept implements Serializable{
    @Id
    @GeneratedValue
    private int id;
    private String dname;
    public QQDept(String dname) {
        this.dname = dname;
    }

}
