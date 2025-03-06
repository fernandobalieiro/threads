package com.training.quarkus;

import java.util.ArrayList;
import java.util.Random;
import java.util.concurrent.Callable;
import java.util.stream.IntStream;

public class Threads {

    public static void main(String[] args) {

        //final var result = new IntegerCalculation();
        final var list = new ArrayList<Integer>();

        for (int i = 0; i < 10; i++) {
            final var result2 = new IntegerCalculation();
            list.add(result2.call());
        }

        System.out.println("Result: " + list);
    }

    final static class IntegerCalculation implements Callable<Integer> {
        @Override
        public Integer call() {
            final var random = new Random();
            System.out.println(Thread.currentThread().getName());
            return random.nextInt();
        }

    }
}

