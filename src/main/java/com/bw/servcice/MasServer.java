package com.bw.servcice;

import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

/**
 * Created by lenovo on 2017/7/17.
 * Async代表了异步方法
 */
@Service
public class MasServer {
    @Async
    public void sendA() throws Exception {
        System.out.println("send A");
        Long startTime = System.currentTimeMillis();
        Thread.sleep(2000);
        Long endTime = System.currentTimeMillis();
        System.out.println("耗时：" + (endTime - startTime));
    }

    @Async
    public void sendB() throws Exception {
        System.out.println("send B");
        Long startTime = System.currentTimeMillis();
        Thread.sleep(2000);
        Long endTime = System.currentTimeMillis();
        System.out.println("耗时：" + (endTime - startTime));
    }


}
