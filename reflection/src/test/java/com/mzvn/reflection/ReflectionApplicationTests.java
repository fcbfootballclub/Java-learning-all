package com.mzvn.reflection;

import com.mzvn.reflection.entity.Bird;
import com.mzvn.reflection.entity.Dog;
import com.mzvn.reflection.entity.Goat;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.lang.reflect.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class ReflectionApplicationTests {

    @Test
    public void givenObject_whenGetsClassName_thenCorrect() {
        Object goat = new Goat("goat");
        Class<?> clazz = goat.getClass();

        assertEquals("Goat", clazz.getSimpleName());
//        assertEquals("com.baeldung.reflection.Goat", clazz.getName());
//        assertEquals("com.baeldung.reflection.Goat", clazz.getCanonicalName());
    }

    @Test
    public void givenClassName_whenCreatesObject_thenCorrect() throws ClassNotFoundException {
        Class<?> clazz = Class.forName("com.mzvn.reflection.entity.Goat");

        assertEquals("Goat", clazz.getSimpleName());
        assertEquals("com.mzvn.reflection.entity.Goat", clazz.getName());
        assertEquals("com.mzvn.reflection.entity.Goat", clazz.getCanonicalName());
    }

    @Test
    public void givenClass_whenRecognisesModifiers_thenCorrect() throws ClassNotFoundException {
        Class<?> goatClass = Class.forName("com.mzvn.reflection.entity.Goat");
        Class<?> animalClass = Class.forName("com.mzvn.reflection.entity.Animal");

        int goatMods = goatClass.getModifiers();
        int animalMods = animalClass.getModifiers();
        System.out.println(goatMods);
        System.out.println(animalMods);

        assertTrue(Modifier.isPublic(goatMods));
        assertTrue(Modifier.isAbstract(animalMods));
        assertTrue(Modifier.isPublic(animalMods));
    }


    //package information
    @Test
    public void givenClass_whenGetsPackageInfo_thenCorrect() {
        Goat goat = new Goat("goat");
        Class<?> goatClass = goat.getClass();
        Package pkg = goatClass.getPackage();

        assertEquals("com.mzvn.reflection.entity", pkg.getName());
    }


    //get super class
    @Test
    public void givenClass_whenGetsSuperClass_thenCorrect() {
        Goat goat = new Goat("goat");
        String str = "any string";

        Class<?> goatClass = goat.getClass();
        Class<?> goatSuperClass = goatClass.getSuperclass();

        assertEquals("Animal", goatSuperClass.getSimpleName());
        assertEquals("Object", str.getClass().getSuperclass().getSimpleName());
    }

    //get interfacew of class
    @Test
    public void givenClass_whenGetsImplementedInterfaces_thenCorrect() throws ClassNotFoundException {
        Class<?> goatClass = Class.forName("com.mzvn.reflection.entity.Goat");
        Class<?> animalClass = Class.forName("com.mzvn.reflection.entity.Animal");

        Class<?>[] goatInterfaces = goatClass.getInterfaces();
        Class<?>[] animalInterfaces = animalClass.getInterfaces();

        assertEquals(1, goatInterfaces.length);
        assertEquals(1, animalInterfaces.length);
        assertEquals("Locomotion", goatInterfaces[0].getSimpleName());
        assertEquals("Eating", animalInterfaces[0].getSimpleName());
    }


    //get constructors of class
    @Test
    public void givenClass_whenGetsConstructor_thenCorrect() throws ClassNotFoundException {
        Class<?> goatClass = Class.forName("com.mzvn.reflection.entity.Goat");

        Constructor<?>[] constructors = goatClass.getConstructors();
        System.out.println(constructors);
        System.out.println(constructors[0].getName());
        System.out.println(constructors[1].getName());
        assertEquals(2, constructors.length);
        assertEquals("com.mzvn.reflection.entity.Goat", constructors[0].getName());
    }


    @Test
    public void givenClass_whenGetsFields_thenCorrect() throws ClassNotFoundException {
        Class<?> animalClass = Class.forName("com.mzvn.reflection.entity.Animal");
        Field[] fields = animalClass.getDeclaredFields();

        List<String> actualFields = getFieldNames(fields);

        assertEquals(2, actualFields.size());
        assertTrue(actualFields.containsAll(Arrays.asList("name", "CATEGORY")));
    }

    private static List<String> getFieldNames(Field[] fields) {
        List<String> fieldNames = new ArrayList<>();
        for (Field field : fields)
            fieldNames.add(field.getName());
        return fieldNames;
    }


    //get method of a class
    @Test
    public void givenClass_whenGetsMethods_thenCorrect() throws ClassNotFoundException {
        Class<?> animalClass = Class.forName("com.mzvn.reflection.entity.Animal");
        Method[] methods = animalClass.getDeclaredMethods();
        List<String> actualMethods = getMethodNames(methods);

        assertEquals(3, actualMethods.size());
        assertTrue(actualMethods.containsAll(Arrays.asList("getName",
                "setName", "getSound")));
    }
    private static List<String> getMethodNames(Method[] methods) {
        List<String> methodNames = new ArrayList<>();
        for (Method method : methods)
            methodNames.add(method.getName());
        return methodNames;
    }

    //inspect constructor
    @Test
    public void givenClass_whenGetsAllConstructors_thenCorrect() throws ClassNotFoundException {
        Class<?> birdClass = Class.forName("com.mzvn.reflection.entity.Bird");
        Constructor<?>[] constructors = birdClass.getConstructors();

        assertEquals(3, constructors.length);
    }


    @Test
    public void givenClass_whenGetsEachConstructorByParamTypes_thenCorrect() throws ClassNotFoundException, NoSuchMethodException {
        Class<?> birdClass = Class.forName("com.mzvn.reflection.entity.Bird");

        Constructor<?> cons1 = birdClass.getConstructor();
        Constructor<?> cons2 = birdClass.getConstructor(String.class);
        Constructor<?> cons3 = birdClass.getConstructor(String.class, boolean.class);
    }


    @Test
    public void givenClass_whenInstantiatesObjectsAtRuntime_thenCorrect() throws ClassNotFoundException, NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {
        Class<?> birdClass = Class.forName("com.mzvn.reflection.entity.Bird");
        Constructor<?> cons1 = birdClass.getConstructor();
        Constructor<?> cons2 = birdClass.getConstructor(String.class);
        Constructor<?> cons3 = birdClass.getConstructor(String.class,
                boolean.class);

        Bird bird1 = (Bird) cons1.newInstance();
        Bird bird2 = (Bird) cons2.newInstance("Weaver bird");
        Bird bird3 = (Bird) cons3.newInstance("dove", true);

        assertEquals("bird", bird1.getName());
        assertEquals("Weaver bird", bird2.getName());
        assertEquals("dove", bird3.getName());

        assertFalse(bird1.walks());
        assertTrue(bird3.walks());
    }


    //inspect fields
    @Test
    public void givenClass_whenGetsPublicFields_thenCorrect() throws ClassNotFoundException {
        Class<?> birdClass = Class.forName("com.mzvn.reflection.entity.Bird");
        Field[] fields = birdClass.getFields();

        assertEquals(1, fields.length);
        assertEquals("CATEGORY", fields[0].getName());
    }

    @Test
    public void givenClass_whenGetsPublicFieldByName_thenCorrect() throws ClassNotFoundException, NoSuchFieldException {
        Class<?> birdClass = Class.forName("com.mzvn.reflection.entity.Bird");
        Field field = birdClass.getField("CATEGORY");
//        Field field1 = birdClass.getField("no");
        assertEquals("CATEGORY", field.getName());
//        assertEquals(null, field1);
    }

    @Test
    public void givenClass_whenGetsDeclaredFields_thenCorrect() throws ClassNotFoundException {
        Class<?> birdClass = Class.forName("com.mzvn.reflection.entity.Bird");
        Field[] fields = birdClass.getDeclaredFields();

        assertEquals(1, fields.length);
        assertEquals("walks", fields[0].getName());
    }

    @Test
    public void givenClass_whenGetsFieldsByName_thenCorrect() throws ClassNotFoundException, NoSuchFieldException {
        Class<?> birdClass = Class.forName("com.mzvn.reflection.entity.Bird");
        Field field = birdClass.getDeclaredField("walks");

        assertEquals("walks", field.getName());
    }


    //get fields type
    @Test
    public void givenClassField_whenGetsType_thenCorrect() throws ClassNotFoundException, NoSuchFieldException {
        Field field = Class.forName("com.mzvn.reflection.entity.Bird")
                .getDeclaredField("walks");
        Class<?> fieldClass = field.getType();

        assertEquals("boolean", fieldClass.getSimpleName());
    }

    //
    @Test
    public void givenClassField_whenSetsAndGetsValue_thenCorrect() throws ClassNotFoundException, NoSuchMethodException, NoSuchFieldException, InvocationTargetException, InstantiationException, IllegalAccessException {
        Class<?> birdClass = Class.forName("com.mzvn.reflection.entity.Bird");
        Bird bird = (Bird) birdClass.getConstructor().newInstance();
        Field field = birdClass.getDeclaredField("walks");
        field.setAccessible(true);

        assertFalse(field.getBoolean(bird));
        assertFalse(bird.walks());

        field.set(bird, true);

        assertTrue(field.getBoolean(bird));
        assertTrue(bird.walks());
    }

    @Test
    public void givenClassField_whenGetsAndSetsWithNull_thenCorrect() throws ClassNotFoundException, NoSuchFieldException, IllegalAccessException {
        Class<?> birdClass = Class.forName("com.mzvn.reflection.entity.Bird");
        Field field = birdClass.getField("CATEGORY");
        field.setAccessible(true);

        assertEquals("domestic", field.get(null));
    }

    @Test
    public void givenClassField_whenSetnewValue_thenCorrect() throws ClassNotFoundException, IllegalAccessException, NoSuchMethodException, InvocationTargetException, InstantiationException, NoSuchFieldException {
        Class<?> dogClass = Class.forName("com.mzvn.reflection.entity.Dog");
        Field[] fields = dogClass.getFields();
        Field[] fields2 = dogClass.getDeclaredFields();
        Dog dog = (Dog) dogClass.getConstructor().newInstance();
        Field field = dogClass.getDeclaredField("legs");
        field.setAccessible(true);
        field.setInt(dog, 9);
        System.out.println(fields);
        System.out.println(fields2);
        System.out.println();
        System.out.println(dog);
    }

    //inspect method
    @Test
    public void givenClass_whenGetsAllPublicMethods_thenCorrect() throws ClassNotFoundException {
        Class<?> birdClass = Class.forName("com.mzvn.reflection.entity.Bird");
        Method[] methods = birdClass.getMethods();
        List<String> methodNames = getMethodNames(methods);

        assertTrue(methodNames.containsAll(Arrays
                .asList("equals", "notifyAll", "hashCode",
                        "walks", "eats", "toString")));
    }

    @Test
    public void givenClass_whenGetsOnlyDeclaredMethods_thenCorrect() throws ClassNotFoundException {
        Class<?> birdClass = Class.forName("com.mzvn.reflection.entity.Bird");
        List<String> actualMethodNames
                = getMethodNames(birdClass.getDeclaredMethods());

        List<String> expectedMethodNames = Arrays
                .asList("setWalks", "walks", "getSound", "eats");

        assertEquals(expectedMethodNames.size(), actualMethodNames.size());
        assertTrue(expectedMethodNames.containsAll(actualMethodNames));
        assertTrue(actualMethodNames.containsAll(expectedMethodNames));
    }
}
