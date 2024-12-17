package com.vogetec.translatetool.tools;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class CustomThreadPool {
    private static CustomThreadPool instance;
    private ExecutorService executor;

    // 私有构造方法，防止外部实例化
    private CustomThreadPool(int poolSize) {
        executor = Executors.newFixedThreadPool(poolSize);
    }

    // 获取单例实例的方法
    public static synchronized CustomThreadPool getInstance(int poolSize) {
        if (instance == null) {
            instance = new CustomThreadPool(poolSize);
        }
        return instance;
    }

    // 提交任务到线程池执行
    public void executeTask(Runnable task) {
        executor.submit(task);
    }

    // 关闭线程池
    public void shutdown() {
        executor.shutdown();
    }
}
