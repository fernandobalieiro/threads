package com.training.quarkus;

import java.util.ArrayList;
import java.util.Random;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import com.training.quarkus.repository.CallerRepository;

public class Threads {

    private static final ExecutorService executor = Executors.newFixedThreadPool(10);

    public static void main(String[] args) throws InterruptedException {

        final var list = new ArrayList<Future<Integer>>();

        for (int i = 0; i < 10; i++) {
            final var calculation = new IntegerCalculation(CallerRepository.getInstance());
            list.add(executor.submit(calculation));
        }

        executor.awaitTermination(1000, java.util.concurrent.TimeUnit.MILLISECONDS);

        list.forEach(e -> {
            try {
                System.out.println("Result: " + e.get());
            } catch (InterruptedException ex) {
                throw new RuntimeException(ex);
            } catch (ExecutionException ex) {
                throw new RuntimeException(ex);
            }
        });
    }

    final static class IntegerCalculation implements Callable<Integer> {

        final CallerRepository callerRepository;

        public IntegerCalculation(final CallerRepository callerRepository) {
            this.callerRepository = callerRepository;
        }

        @Override
        public Integer call() {
            final var random = new Random();
            System.out.println("Inside Thread: " + Thread.currentThread().getName());
            return random.nextInt();
        }

    }
}

