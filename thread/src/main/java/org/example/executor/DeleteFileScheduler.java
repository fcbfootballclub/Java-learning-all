package org.example.executor;

import java.io.File;
import java.util.concurrent.Callable;

public class DeleteFileScheduler implements Runnable {
    @Override
    public void run() {
        File folder = new File("/home/henry/Desktop/test");
        if(folder.isDirectory()) {
            for(File file : folder.listFiles()) {
                if(System.currentTimeMillis() - file.lastModified() > 5 * 60 * 1000) {
                    System.out.println("this file will be deleted " + file.getName());
                    file.delete();
                }
            }
        }
        System.out.println(Thread.currentThread().getName() + " is running!");

    }
}
