package com.example.annotation.entity;

import com.example.annotation.annotation.ImportantString;
import com.example.annotation.annotation.Runimmediately;
import com.example.annotation.annotation.VeryImportant;

@VeryImportant
public class Cat {
     @ImportantString
     String name;
     int age;

    public Cat(String name) {
        this.name = name;
    }

    public void meow() {
        System.out.println("Meow!!");
    }

    @Runimmediately(times = 3)
    public void eat() {
        System.out.println("Munch");
    }

    @Override
    public String toString() {
        return "Cat{" +
                "name='" + name + '\'' +
                ", age=" + age +
                '}';
    }
}
