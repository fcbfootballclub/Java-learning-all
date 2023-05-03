package org.example.consumer;

import java.util.function.Function;

public class FunctionDemo {
    public static void main(String[] args) {
        Function<Integer, Integer> function = (x) -> x*x;
        Integer apply = function.apply(3);
        System.out.println(apply);
    }
}
