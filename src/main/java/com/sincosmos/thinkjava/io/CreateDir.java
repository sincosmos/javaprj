package com.sincosmos.thinkjava.io;

import java.io.File;

public class CreateDir {
    public static void main(String[] args){
        String path = "/Users/kangle5/Documents/test";
        File rootDir = new File(path);
        if (!rootDir.exists() || !rootDir.isDirectory()) {
            System.out.println(rootDir.mkdirs());
        }

    }
}
