package com.bw.servcice;

import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.AsyncResult;
import org.springframework.stereotype.Service;

import java.util.concurrent.Future;

/**
 * Created by lenovo on 2017/7/17.
 *  负载均衡和redis和ngiux结合的异步刷新
 */
@Service
public class MsgFutureServer {
    @Async
    public Future<String> sendA() throws Exception {
        System.out.println("send A");
        Long startTime = System.currentTimeMillis();
        Thread.sleep(2000);
        Long endTime = System.currentTimeMillis();
        System.out.println("耗时：" + (endTime - startTime));
        return new AsyncResult<String>("success");
    }

    @Async
    public Future<String> sendB() throws Exception {
        System.out.println("send B");
        Long startTime = System.currentTimeMillis();
        Thread.sleep(2000);
        Long endTime = System.currentTimeMillis();
        System.out.println("耗时：" + (endTime - startTime));
        return new AsyncResult<String>("success");
    }

}
