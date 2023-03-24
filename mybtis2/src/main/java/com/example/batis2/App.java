package com.example.batis2;

import java.util.Arrays;

public class App {
    public static void main(String[] args) {
        Integer a = null;
        System.out.println(a);
        int sum = sum("f");
        System.out.println(sum);
    }

    public static  int sum(String x, Integer... list) {
        System.out.println(Arrays.toString(list));
        int sum = 0;
        for(int i : list) {
            sum += i;
        }
        return sum;
    }
}
