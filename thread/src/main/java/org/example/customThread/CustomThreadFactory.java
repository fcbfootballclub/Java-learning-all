package org.example.customThread;

import java.util.concurrent.ThreadFactory;

public class CustomThreadFactory implements ThreadFactory {
    public static int count = 0;
    @Override
    public Thread newThread(Runnable r) {
        Thread thread = new Thread(r);
        thread.setName("thread number " + count);
        thread.setPriority(7);
        count++;
        return thread;
    }
}
