package com.bw.Tree;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

/**
 * Created by lenovo on 2017/7/12.
 */
@Entity
public class QQTree {
    @Id
    @GeneratedValue
    private Integer id;
    private String name;
    private int tid;
    private String url;
    private String target;

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

    public int getTid() {
        return tid;
    }

    public void setTid(int tid) {
        this.tid = tid;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getTarget() {
        return target;
    }

    public void setTarget(String target) {
        this.target = target;
    }

    @Override
    public String toString() {
        return "QQTree{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", tid=" + tid +
                ", url='" + url + '\'' +
                ", target='" + target + '\'' +
                '}';
    }
}
