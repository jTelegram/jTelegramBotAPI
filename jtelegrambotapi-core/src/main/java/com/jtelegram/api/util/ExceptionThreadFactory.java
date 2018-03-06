package com.jtelegram.api.util;

import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;

// When you are using an ExecutorService
// you should still expect EXCEPTIONS TO
// PRINT
public class ExceptionThreadFactory implements ThreadFactory {
    private final ThreadFactory factory = Executors.defaultThreadFactory();

    @Override
    public Thread newThread(Runnable r) {
        Thread thread = factory.newThread(r);
        thread.setUncaughtExceptionHandler((t, e) -> e.printStackTrace());
        return thread;
    }
}
