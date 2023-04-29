package com.example.annotation2;

import com.example.annotation2.model.Meme;
import com.example.annotation2.model.Momo;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class Main {
    public static void main(String[] args) {
        Meme meme = new Meme(1, "leuleu");
/*        Class<? extends Meme> aClass = meme.getClass();
        if(aClass.isAnnotationPresent(JsonSerializable.class)) {
            System.out.println("annotation");
            Method[] methods = aClass.getMethods();
            for(Method method : methods) {
                if(method.getName().equals("getNameAndAge")) {
                    try {
                        method.invoke(meme);
                    } catch (IllegalAccessException e) {
                        throw new RuntimeException(e);
                    } catch (InvocationTargetException e) {
                        throw new RuntimeException(e);
                    }
                }
            }

        }*/
/*        Momo momo = new Momo();
        momo.setAclass(Meme.class);
        momo.handleAnnotation(new Meme(10, "fdsao"));*/
        ObjectToJsonConverter obj = new ObjectToJsonConverter();
        obj.convertToJson(new Meme());

    }
}
