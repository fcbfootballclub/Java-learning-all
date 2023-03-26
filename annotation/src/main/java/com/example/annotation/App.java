package com.example.annotation;

import com.example.annotation.annotation.VeryImportant;
import com.example.annotation.entity.Cat;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class App {
    public static void main(String[] args) throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
         Cat myCat = new Cat("Henry");
         if(myCat.getClass().isAnnotationPresent(VeryImportant.class)) {
             System.out.println("Annotated!");
         }
        System.out.println(myCat.getClass());
        Class<? extends Cat> aClass = myCat.getClass();
        Method meow = aClass.getMethod("meow");
        meow.invoke(null);

    }
}
