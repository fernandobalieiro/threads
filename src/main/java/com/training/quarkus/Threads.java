package com.training.quarkus;

import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Threads {

    private static final ExecutorService executor = Executors.newFixedThreadPool(100);

    public static void main(String[] args) throws InterruptedException {
        final var start = System.currentTimeMillis();

        for (int i = 0; i < 1_000_000; i++) {
            final var calculation = new IntegerCalculation();
            executor.submit(calculation);
        }

        executor.awaitTermination(20000, java.util.concurrent.TimeUnit.MILLISECONDS);

        final var end = System.currentTimeMillis();
        System.out.println("Time taken: " + (end - start) + "ms");
    }

    static final class IntegerCalculation implements Runnable {

        @Override
        public void run() {
            final var random = new Random();
            System.out.println(Thread.currentThread().getName() + ": " +  random.nextInt());
        }
    }
}

