package org.example.File;

import java.io.File;
import java.io.FileDescriptor;
import java.io.FilenameFilter;

public class Bai2 {
    public static void main(String[] args) {
        File file = new File("/home/henry/Downloads");
        if(file.isDirectory()) {
            String[] list = file.list(new FilenameFilter() {
                @Override
                public boolean accept(File dir, String name) {
                    if (name.toLowerCase().endsWith(".deb")) {
                        return true;
                    }
                    return false;
                }
            });
            for (String s : list) {
                System.out.println(s);
            }
        }
    }
}
