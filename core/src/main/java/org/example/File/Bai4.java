package org.example.File;

import java.io.File;
import java.io.FilenameFilter;
import java.util.Date;

public class Bai4 {
    public static void main(String[] args) {
        File file = new File("/home/henry/Downloads");
        System.out.println(file.canRead());
        System.out.println(file.canWrite());
        long l = file.lastModified();
        Date date = new Date(l);
        System.out.println(date);
    }
}
