package com.example.chatai.service;

import lombok.Getter;
import lombok.Setter;
import lombok.SneakyThrows;
import lombok.experimental.Accessors;

import java.util.Arrays;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicLong;

/**
 * @author wuhongzhang@vhsoft.com.cn
 * @date 2025/6/11 10:48
 * @description
 */
@Getter
@Setter
@Accessors(chain = true)
public class Test {
    @SneakyThrows
    public static void main(String[] args) throws InterruptedException {
        test1();
        test2();

        System.out.println("提交任务完成");
        while (true) {
        }
    }



    //自定义线程池
    private static class DefaultForkJoinPoolThread extends ForkJoinWorkerThread {
        protected DefaultForkJoinPoolThread(ForkJoinPool pool) {
            super(pool);
        }
    }

    /**
     * 获取线程创建工厂
     * @param trheadName 线程名称
     * @return
     */
    private static ForkJoinPool.ForkJoinWorkerThreadFactory getForkJoinFactory(String trheadName){
        ForkJoinPool.ForkJoinWorkerThreadFactory factory = new ForkJoinPool.ForkJoinWorkerThreadFactory() {
            final AtomicLong count = new AtomicLong(0);
            @Override
            public ForkJoinWorkerThread newThread(ForkJoinPool pool) {
                DefaultForkJoinPoolThread thread = new DefaultForkJoinPoolThread(pool);
                thread.setName(trheadName+"-"+count.getAndIncrement());
                return thread;
            }
        };
        return factory;
    }

    private static void test1() {
        ForkJoinPool customPlatformPool = new ForkJoinPool(10,getForkJoinFactory("whz"),null,false);
        customPlatformPool.submit(() -> {
            System.out.println(Thread.currentThread());
            ThreadFactory factory = Thread.ofVirtual().factory();
            ExecutorService executorService = Executors.newThreadPerTaskExecutor(factory);

            executorService.submit(() -> {
                // 死循环持续运行
                while (true) {
                    try {
                        TimeUnit.SECONDS.sleep(1);
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                    System.out.println(Thread.currentThread() + "sss");
                }
            });
        });
    }





    private static void test2() {
        ThreadFactory factory = Thread.ofVirtual().factory();
        ExecutorService executorService = Executors.newThreadPerTaskExecutor(factory);

        executorService.submit(() -> {
            // 死循环持续运行
            while (true) {
                try {
                    TimeUnit.SECONDS.sleep(1);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                System.out.println(Thread.currentThread() + "sss");
            }
        });


    }

}
