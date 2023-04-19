package org.example.test;

import org.example.thread.ReadFileRunnable;

import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class Main {
    public static void main(String[] args) {
//        ReadFile readFile = new ReadFile();
//        readFile.start();
//
//        System.out.println("Hello world!");
//
        String str1 = "hello";
        String str2 = "hello";
        System.out.println(str1 == str2);
        ReadFileRunnable readFileRunnable = new ReadFileRunnable();
//        Thread thread = new Thread(readFileRunnable);
//        thread.start();
        Executor executor = Executors.newSingleThreadExecutor();
        executor.execute(readFileRunnable);
        Executor executor1 = Executors.newFixedThreadPool(5);
        executor1.execute(readFileRunnable);

        ExecutorService executor2 = Executors.newFixedThreadPool(3);
        executor2.execute(readFileRunnable);


    }
}