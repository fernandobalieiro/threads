package com.training.quarkus;

import java.util.Random;
import java.util.concurrent.Callable;

public class Threads {

    public static void main(String[] args) {
        final var runnable = new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(5000);
                    System.out.println("Hello World!");
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        };

        runnable.run();
    }
}
