package com.example.annotation;

import com.example.annotation.annotation.ImportantString;
import com.example.annotation.annotation.Runimmediately;
import com.example.annotation.annotation.VeryImportant;
import com.example.annotation.entity.Cat;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class App {
    public static void main(String[] args) throws InvocationTargetException, IllegalAccessException {
         Cat myCat = new Cat("Henry");
         if(myCat.getClass().isAnnotationPresent(VeryImportant.class)) {
             System.out.println("Annotated!");
         }
        System.out.println(myCat.getClass());
        Class<? extends Cat> aClass = myCat.getClass();
//        Method meow = aClass.getMethod("meow");
//        meow.invoke(myCat);
        Method[] methods = aClass.getMethods();
        for(Method method : methods) {
            if(method.isAnnotationPresent(Runimmediately.class)) {
                Runimmediately runimmediately = method.getAnnotation(Runimmediately.class);
                for (int i = 0; i < runimmediately.times(); i++) {
                    method.invoke(myCat);
                }
            }
        }

        System.out.println("-------");
        Field[] fields = aClass.getDeclaredFields();
        for(Field field : fields) {
            if(field.isAnnotationPresent(ImportantString.class)) {
                field.setAccessible(true);
                field.set(myCat, "HENRY 2");
                System.out.println(field.getName() + "is importatnt string");
            }
        }
        System.out.println(myCat);
    }
}
