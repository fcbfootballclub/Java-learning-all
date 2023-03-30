package org.example.File;

import java.io.*;

public class Bai15 {
    public static void main(String[] args) {
        BufferedReader bufferedReader = null;
        BufferedWriter bufferedWriter = null;
        StringBuilder stringBuilder = new StringBuilder("");
        try {
            bufferedReader = new BufferedReader(new FileReader("/home/henry/Desktop/demo.txt"));
            bufferedWriter = new BufferedWriter(new FileWriter("/home/henry/Desktop/demo.txt"));
            bufferedWriter.write("write string !!!!");
            bufferedWriter.flush();
            bufferedWriter.write("ahiahi 2");
            bufferedWriter.flush();
            bufferedWriter.append("lfdsafs");
            bufferedWriter.close();
            bufferedReader.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
