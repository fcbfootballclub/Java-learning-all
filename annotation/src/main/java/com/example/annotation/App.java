package com.example.annotation;

import com.example.annotation.annotation.VeryImportant;
import com.example.annotation.entity.Cat;

public class App {
    public static void main(String[] args) throws NoSuchMethodException {
         Cat myCat = new Cat("Henry");
         if(myCat.getClass().isAnnotationPresent(VeryImportant.class)) {
             System.out.println("Annotated!");
         }
        System.out.println(myCat.getClass());
        Class<? extends Cat> aClass = myCat.getClass();
        aClass.getMethod("meow").;
    }
}
