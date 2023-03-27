package com.mzvn.reflection.entity;


public abstract class Animal implements Eating {

    public static String CATEGORY = "domestic";
    private String name;

    public Animal() {
    }

    public Animal(String bird) {
        this.name = bird;
    }

    protected abstract String getSound();

    // constructor, standard getters and setters omitted


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}