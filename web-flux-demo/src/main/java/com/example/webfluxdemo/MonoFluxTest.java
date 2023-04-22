package com.example.webfluxdemo;

import reactor.core.publisher.Mono;

public class MonoFluxTest {
    public void testMono() {
        Mono<String> monoString = Mono.just("ahihi");
        monoString.subscribe(System.out::println);
    }
}
