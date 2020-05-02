package com.rival.asyn;

import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.concurrent.CompletableFuture;

public class Asyn {


    /**
     * <p>异步非阻塞</p>
     *
     * */
    public static void main(String[] args) throws InterruptedException {

        //spring线程池，正常情况是核心线程数生效，到队列满了之后，为最大线程数生效
        ThreadPoolTaskExecutor threadPoolTaskExecutor = new ThreadPoolTaskExecutor();
        threadPoolTaskExecutor.setCorePoolSize(10);
        threadPoolTaskExecutor.setMaxPoolSize(15);
        threadPoolTaskExecutor.initialize();
        /**
         *CompletableFuture.runAsync()为不需要返回值得异步方法
         * thenApply需要返回结果
         * thenAccept不需要返回结果
         * */
        CompletableFuture.supplyAsync(() -> {
            try {
                Thread.sleep(5000);
                return "asyn result";
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return null;
        } , threadPoolTaskExecutor).thenAccept(result -> {
            System.out.println("异步任务结束，返回结果："+result);
        });
        Thread.sleep(1000);
        System.out.println("主线程执行");
    }


}
