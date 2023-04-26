package com.example.annotation2.model;

import com.example.annotation2.JsonSerializable;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class Momo {
    Class<? extends Meme> aclass;

    String name;

    public void handleAnnotation(Meme meme) {
        if (aclass.isAnnotationPresent(JsonSerializable.class)) {
            System.out.println("annotation");
            Method[] methods = aclass.getMethods();
            for (Method method : methods) {
                if (method.getName().equals("getNameAndAge")) {
                    try {
                        method.invoke(meme);
                    } catch (IllegalAccessException e) {
                        throw new RuntimeException(e);
                    } catch (InvocationTargetException e) {
                        throw new RuntimeException(e);
                    }
                }
            }

        }
    }

    public Class<? extends Meme> getAclass() {
        return aclass;
    }

    public void setAclass(Class<? extends Meme> aclass) {
        this.aclass = aclass;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
