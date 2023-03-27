package com.mzvn.reflection;

import com.mzvn.reflection.entity.Goat;

public class App {
    public static void main(String[] args) {

        givenObject_whenGetsClassName_thenCorrect();


    }

    public static void givenObject_whenGetsClassName_thenCorrect() {
        Object goat = new Goat("goat");
        Class<?> clazz = goat.getClass();
        System.out.println(clazz.getSimpleName());
        System.out.println(clazz.getName());
        System.out.println(clazz.getCanonicalName());
//        assertEquals("Goat", clazz.getSimpleName());
//        assertEquals("com.baeldung.reflection.Goat", clazz.getName());
//        assertEquals("com.baeldung.reflection.Goat", clazz.getCanonicalName());
    }
}
