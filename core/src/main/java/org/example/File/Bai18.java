package org.example.File;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Bai18 {
    public static void main(String [ ] args) throws FileNotFoundException {
        new Bai18().findLongestWords();
    }

    public String findLongestWords() throws FileNotFoundException {

        String longest_word = "";
        String current;
        Scanner sc = new Scanner(new File("/home/henry/Desktop/demo.txt"));


        while (sc.hasNext()) {
            current = sc.next();
            System.out.println("word " + current);
            if (current.length() > longest_word.length()) {
                longest_word = current;
            }

        }
        System.out.println("\n"+longest_word+"\n");
        return longest_word;
    }
}
