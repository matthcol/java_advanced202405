package org.example.concurrent;

import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.Executors;
public class Main {

    static void demoThreadPool(){
        try (ThreadPoolExecutor executor = (ThreadPoolExecutor) Executors.newFixedThreadPool(4)) {
            executor.execute(() -> {
                long x = 0;
                for (long i=0; i<10_000_000_000L; i++ ) x += 3;
                System.out.println("Job result: " + x);
            });
            System.out.println("Job started");
        } // close => shutdown
    }

    static void demoForkJoinPool(){
        try (ForkJoinPool executor = new ForkJoinPool(4)) {

        } // close => shutdown

    }

    public static void main(String[] args) {
        // main thread
        // demoThreadPool();
    }
}
