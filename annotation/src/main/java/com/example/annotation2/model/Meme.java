package com.example.annotation2.model;

import com.example.annotation2.Init;
import com.example.annotation2.JsonElement;
import com.example.annotation2.JsonSerializable;

import java.lang.reflect.Field;

@JsonSerializable
public class Meme {
    private int age;
    @JsonElement
    private String name;
    public void getNameAndAge() {
        System.out.println(name + age);
    }

    @Init
    public void initNames() {
        if(name == null) {
            Class<? extends Meme> aClass = this.getClass();
            for(Field field : aClass.getDeclaredFields()) {
                if (field.isAnnotationPresent(JsonElement.class)) {
                    JsonElement jsonElement = field.getAnnotation(JsonElement.class);
                    this.name = jsonElement.key();
                }
            }
        }
        this.name = this.name.substring(0, 1).toUpperCase()
                + this.name.substring(1);
    }

    public Meme(int age, String name) {
        this.age = age;
        this.name = name;
    }

    public Meme() {
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
