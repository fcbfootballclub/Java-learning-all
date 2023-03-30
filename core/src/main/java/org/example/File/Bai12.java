package org.example.File;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class Bai12 {
    public static void main(String[] args) {
        BufferedReader bufferedReader = null;
        StringBuilder stringBuilder = new StringBuilder("");
        try {
            bufferedReader = new BufferedReader(new FileReader("/home/henry/Desktop/demo.txt"));
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                stringBuilder.append(line);
                stringBuilder.append("\n");
            }
            System.out.println(stringBuilder);
            bufferedReader.close();
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
