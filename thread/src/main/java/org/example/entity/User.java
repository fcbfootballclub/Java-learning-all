package org.example.entity;

import java.util.logging.Level;
import java.util.logging.Logger;

public class User {
    private String id;
    private String name;
    private String emailAddress;

    public String getId() {
        return id;
    }

    public User setId(String id) {
        this.id = id;
        return this;
    }

    public String getName() {
        return name;
    }

    public User setName(String name) {
        this.name = name;
        return this;
    }

    public String getEmaillAddress() {
        return emailAddress;
    }

    public User setEmaillAddress(String emaillAddress) {
        this.emailAddress = emaillAddress;
        return this;
    }

    @Override
    public String toString() {
        return "User{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", emaillAddress='" + emailAddress + '\'' +
                '}';
    }

    public static void main(String[] args) {
        int a = 1;
        int b = 2;
        System.out.println(sum(a, b));
    }

    public static int sum(int a, int b) {
        Logger c = Logger.getLogger("ahiahi do ngokc");
        c.log(Level.INFO, "this");
        c.info("ahiahi do ngok");
        return a + b;
    }
}
