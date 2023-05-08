package org.example.consumer;

import java.util.function.Consumer;

public class ConsumerDemo {
    public static void main(String[] args) {
        Consumer<Object> cs = s -> System.out.println(s.toString());
        cs.accept("hello");
    }
}
