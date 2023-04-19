package org.example.test;

import org.example.customThread.CustomThreadFactory;
import org.example.executor.LogginProcessor;

import java.util.concurrent.*;

public class TestThreadFactory {
    public static void main(String[] args) {
        int core = Runtime.getRuntime().availableProcessors();
        System.out.println(core);
        ExecutorService executorService = Executors.newFixedThreadPool(core, new CustomThreadFactory());
        ExecutorService executorService1 = new ThreadPoolExecutor(3, 12, 10, TimeUnit.SECONDS, new ArrayBlockingQueue<>(300));
        for(int i = 0; i < 50; i++) {
//            executorService.submit(new LogginProcessor());
            executorService1.submit(new LogginProcessor());
        }
    }
}
