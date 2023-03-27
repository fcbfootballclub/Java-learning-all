package com.mzvn.reflection.entity;

public class Goat extends Animal implements Locomotion {

    public Goat(String goatName) {
        this.setName(goatName);
    }


    @Override
    protected String getSound() {
        return "bleat";
    }

    @Override
    public String getLocomotion() {
        return "walks";
    }

    @Override
    public String eats() {
        return "grass";
    }

    // constructor omitted
}
