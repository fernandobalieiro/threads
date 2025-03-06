package com.training.quarkus;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class Threads {

    public static void main(String[] args) throws InterruptedException, ExecutionException {

        final var completableFuture = calculateAsync();

        final var result = completableFuture.get();
        System.out.println(result);
    }

    public static Future<String> calculateAsync() {
        final var completableFuture = new CompletableFuture<String>();

        Executors.newCachedThreadPool().submit(() -> {
            Thread.sleep(10_000);
            completableFuture.complete("Hello!");
            return null;
        });

        return completableFuture;
    }
}

