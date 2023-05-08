package org.example.consumer;

import java.util.function.Predicate;

public class PredicateDemo {
    public static void main(String[] args) {
        Predicate<Integer> test = s -> s%2 ==0;
        System.out.println(test.test(3));
    }
}
