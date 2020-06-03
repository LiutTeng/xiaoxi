package com.xiaoxi.study.util;

import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author liuteng
 * 线程池工具类
 */
public class ThreadPoolUtil {

    private static ExecutorService  executor;

    private static ThreadPoolUtil threadPoolUtil;
    /**
     * 核心线程数量
     */
    private static final int CORE_POOL_SIZE = 10;
    /**
     * 最大线程数量
     */
    private static final int MAXI_NUM_POOLSIZE = 20;
    /**
     * 线程存活时间
     */
    private static final long KEEP_ALIVE_TIME = 5L;
    /**
     * 任务队列大小
     */
    private static final int QUEUE_SIZE = 15;
    /**
     * 阻塞队列
     */
    private static BlockingQueue<Runnable> workQueue = new ArrayBlockingQueue<>(QUEUE_SIZE);
    /**
     * 私有构造器
     */
    private ThreadPoolUtil(String threadName){
        executor = new ThreadPoolExecutor(CORE_POOL_SIZE, MAXI_NUM_POOLSIZE, KEEP_ALIVE_TIME, TimeUnit.MINUTES, workQueue, new MyThreadFactory(threadName), new MyRejectedExecutionHandler());
    }
    /**
     * 线程池构建工厂
     */
    public static ThreadPoolUtil getInstance(String threadName){
        if (null == threadPoolUtil) {
            synchronized (ThreadPoolUtil.class) {
                if (null == threadPoolUtil) {
                    threadPoolUtil = new ThreadPoolUtil(threadName);
                }
            }
        }
        return threadPoolUtil;
    }

    public ExecutorService getThreadPoolExecutor(){
        return executor;
    }

    /**
     * 内部类，线程工厂，用于创造线程池所需要的线程
     */
    static class MyThreadFactory implements ThreadFactory {

        private String threadName;

        private final AtomicInteger threadNumber = new AtomicInteger(1);

        public MyThreadFactory (String threadName) {
            this.threadName = threadName;
        }

        @Override
        public Thread newThread(Runnable run) {
            Thread thread = new Thread(run, threadName + "-" + threadNumber.getAndIncrement());
            thread.setUncaughtExceptionHandler((t, e) -> System.out.println("Exception: " + e.getMessage()));
            return thread;
        }

    }

    /**
     * 内部类，拒绝任务后的请求处理
     */
    static class MyRejectedExecutionHandler implements RejectedExecutionHandler {
        @Override
        public void rejectedExecution(Runnable r, ThreadPoolExecutor executor) {
            System.out.println("ThreadPool over");
        }
    }
}
