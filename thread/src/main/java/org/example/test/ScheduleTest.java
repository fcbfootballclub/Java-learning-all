package org.example.test;

import org.example.executor.DeleteFileScheduler;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class ScheduleTest {
    public static void main(String[] args) {
        ScheduledExecutorService scheduledExecutorService = Executors.newSingleThreadScheduledExecutor();
//        scheduledExecutorService.schedule(new DeleteFileScheduler(), 5, TimeUnit.SECONDS);
        scheduledExecutorService.scheduleAtFixedRate((Runnable) new DeleteFileScheduler(), 5, 4, TimeUnit.SECONDS);
//        scheduledExecutorService.scheduleWithFixedDelay()
        ExecutorService sv = Executors.newSingleThreadScheduledExecutor();

    }
}
