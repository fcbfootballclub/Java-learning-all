package com.example.webfluxdemo;

import org.junit.jupiter.api.Test;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;


class MonoFluxTestTest {
    @Test
    public void testMono() {
        Mono<?> monoString = Mono.just("ahihi")
                .then(Mono.error(new RuntimeException("exeption occured")))
                .log();
        monoString.subscribe(System.out::println, (e) -> System.out.println(e.getMessage()));

    }

    @Test
    public void testFlux() {
        Flux<String> fluxString = Flux.just("a", "b", "c")
                .concatWith(Flux.error(new RuntimeException("leuleu")))
                .concatWithValues("dao", "Dinh")
                .log();
        fluxString.subscribe(System.out::println);
    }
}
