package org.example.File;

import java.io.*;
import java.util.Arrays;
import java.util.Date;

public class Bai10 {
    public static void main(String[] args) {
        File file = new File("/home/henry/Desktop/demo.txt");
        if(file.isFile()) {
            InputStream inputStream = null;
            try {
                inputStream = new FileInputStream(file);
                byte file_content[] = new byte[2*1024];
                int read_count = 0;
                while((read_count = inputStream.read(file_content)) > 0){
                    System.out.println(new String(file_content, 0, read_count-1));
                }
                System.out.println(Arrays.toString(file_content));

            } catch (FileNotFoundException e) {
                throw new RuntimeException(e);
            } catch (IOException e) {
                throw new RuntimeException(e);
            } finally {
                try {
                    inputStream.close();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        }
    }
}
