package org.example.File;

import java.io.File;

public class Bai1 {
    public static void main(String[] args) {
        File file = new File("/home/henry/Downloads");
        if(file.exists()) {
            System.out.println(file.getName());
        } else {
            return;
        }
        while(file.isDirectory()) {
            File[] files = file.listFiles();
            for (File file1 : files) {
                System.out.println("\t" + file1.getName());
            }
        }
    }
}
