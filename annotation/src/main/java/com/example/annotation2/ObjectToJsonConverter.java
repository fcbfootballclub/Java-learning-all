package com.example.annotation2;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

public class ObjectToJsonConverter {
    public String convertToJson(Object object) throws RuntimeException {
        try {
            checkIfSerializable(object);
            initializeObject(object);
            System.out.println(getJsonString(object));
            return getJsonString(object);
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    private String getJsonString(Object object) {
        StringBuilder stringBuilder = new StringBuilder("");
        System.out.println("****");
        Map<String, String> map = new HashMap<>();
        for(Field field : object.getClass().getDeclaredFields()) {
            if(field.isAnnotationPresent(JsonElement.class)) {
                try {
                    field.setAccessible(true);
                    map.put(field.getName(), (String) field.get(object));
                } catch (IllegalAccessException e) {
                    throw new RuntimeException(e);
                }
            }
        }
        String jsonString = map.entrySet()
                .stream()
                .map(entry -> "\"" + entry.getKey() + "\":\""
                        + entry.getValue() + "\"")
                .collect(Collectors.joining(","));
        return "{" + jsonString + "}";

    }

    private void initializeObject(Object object) {
        Class<?> aClass = object.getClass();
        for(Method method : aClass.getDeclaredMethods()) {
            if(method.isAnnotationPresent(Init.class)) {
                try {
                    method.invoke( object);
                } catch (IllegalAccessException e) {
                    throw new RuntimeException(e);
                } catch (InvocationTargetException e) {
                    throw new RuntimeException(e);
                }
            }
        }
    }

    private void checkIfSerializable(Object object) {
        Class<?> aClass = object.getClass();
        if(aClass.isAnnotationPresent(JsonSerializable.class)) {
            System.out.println("able to serialization");;
        } else {
            throw new RuntimeException("not annotated");
        }
    }
}
