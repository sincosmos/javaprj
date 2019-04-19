package com.sincosmos.thinkjava.io;

import joinery.DataFrame;
import java.io.IOException;

public class JavaCsv {
    public DataFrame readCsv(final String path){
        DataFrame df = null;
        try {
            long st = System.currentTimeMillis();
            df = DataFrame.readCsv(path);
            long ed = System.currentTimeMillis();
            System.out.println("Read time (s): " + (ed-st)/1000);
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            return df;
        }
    }

    public void writeCsv(final DataFrame df, final String path){
        try {
            long st = System.currentTimeMillis();
            df.writeCsv(path);
            long ed = System.currentTimeMillis();
            System.out.println("Write time (s): " + (ed-st)/1000);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args){
        String readPath  = "test1.csv";
        String writePath = "test2.csv";

        JavaCsv javaCsv = new JavaCsv();

        DataFrame df = javaCsv.readCsv(readPath);
        System.out.println("count: " + df.count());
        javaCsv.writeCsv(df, writePath);
    }
}
