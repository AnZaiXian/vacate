package com.bw.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;


/**
 * Created by lenovo on 2017/7/13.
 * QQ好友列表
 *
 */
@Entity
@NoArgsConstructor
@Data
public class QQFriends {
    @Id
    @GeneratedValue
    private Integer id;
    private String qname;
    private String talks;//发表的说说
    private String message;//留言
    private String logintime;//访问时间
    //单向的一对一
    @OneToOne
    private QQDept dept;//QQ好友角色


    public QQFriends(String qname, String talks, String message, String logintime) {
        this.qname = qname;
        this.talks = talks;
        this.message = message;
        this.logintime = logintime;
    }

}
