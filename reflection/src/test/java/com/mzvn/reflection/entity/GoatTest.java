package com.mzvn.reflection.entity;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class GoatTest {
    @Test
    public void givenObject_whenGetsClassName_thenCorrect() {
        Object goat = new Goat("goat");
        Class<?> clazz = goat.getClass();

        assertEquals("Goat", clazz.getSimpleName());
        assertEquals("com.baeldung.reflection.Goat", clazz.getName());
        assertEquals("com.baeldung.reflection.Goat", clazz.getCanonicalName());
    }
}