package org.example.consumer;

public interface CustomFunction<T> {
    boolean sudo(T t);

    public static void main(String[] args) {
         CustomFunction<Integer> demo = s -> s % 3 == 0;
        boolean sudo = demo.sudo(9);
        System.out.println(sudo);
    }
}
