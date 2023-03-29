package com.mzvn.reflection.entity;

public class Dog extends Animal {
    private int legs;
    public String country;

    public Dog() {
    }

    public Dog(int legs, String country) {
        this.legs = legs;
        this.country = country;
    }

    public Dog(String name, int legs, String country) {
        super(name);
        this.legs = legs;
        this.country = country;
    }

    @Override
    protected String getSound() {
        return null;
    }

    @Override
    public String eats() {
        return null;
    }

    @Override
    public String toString() {
        return "Dog{" +
                "legs=" + legs +
                ", country='" + country + '\'' +
                '}';
    }

    public void setLegs(int legs) {
        this.legs = legs;
    }
}
