package org.example.executor;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;
import java.util.logging.Logger;

public class LogginProcessor implements Callable<Boolean> {
    @Override
    public Boolean call() throws Exception {
        Logger.getLogger("Logging infomation! " + Thread.currentThread().getName());
        System.out.println("Logging infomation! " + Thread.currentThread().getName());
        return true;
    }

    public static void main(String[] args) {
        ExecutorService executorService = Executors.newSingleThreadExecutor();
        List<LogginProcessor> list = new ArrayList<>();
        list.add(new LogginProcessor());
        list.add(new LogginProcessor());
        list.add(new LogginProcessor());
        System.out.println(Thread.currentThread().getName() + "--------------");
        try {
            List<Future<Boolean>> futures = executorService.invokeAll(list);
            for(Future<Boolean> future : futures) {
                System.out.println(future.get());
            }
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        } catch (ExecutionException e) {
            throw new RuntimeException(e);
        }
        System.out.println("----------------");
        try {
            Boolean aBoolean = executorService.invokeAny(list);
            System.out.println(aBoolean);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        } catch (ExecutionException e) {
            throw new RuntimeException(e);
        }
    }
}
