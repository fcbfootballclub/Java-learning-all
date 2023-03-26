package com.example.annotation.entity;

import com.example.annotation.annotation.VeryImportant;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@VeryImportant
public class Cat {
     String name;
     int age;

    public Cat(String name) {
        this.name = name;
    }

    public void meow() {
        System.out.println("Meow!!");
    }

    @VeryImportant
    public void eat() {
        System.out.println("Munch");
    }
}
