package com.sincosmos.thinkjava.io;

import java.io.*;
import java.util.Arrays;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.regex.Pattern;

public class DirTree {
	public static void traverse(File path, int depth, ExecutorService executor) {
		File[] files = path.listFiles((dir, name)->{
			Pattern pattern = Pattern.compile("^(?!\\.).*");
			return pattern.matcher(name).matches();
		});
		Arrays.asList(files).forEach((file)->{
			/*for(int i=0; i<depth; ++i) {
				System.out.print("  ");
			}*/
			//System.out.println(file.toString());
			if(file.isDirectory()) {
				traverse(file, depth+1, executor);
			}else if(file.toString().contains(".java")){
				//System.out.println(file.toString());
				executor.submit(new Analysis(file.toString()));
			}
		});
	}
	public static void main(String[] args) {
		File path = new File(".");
		if(args.length != 0 ) {
			path = new File(args[0]); 
		}		
		traverse(path, 0, Executors.newFixedThreadPool(10));
	}
}

class Analysis implements Runnable{
    private String fileName;
    public Analysis(String fileName){
        this.fileName = fileName;
    }

    @Override
    public void run() {
        try {
            BufferedReader content = new BufferedReader(new FileReader(fileName));
            String line;
            while((line = content.readLine()) != null){
                if(line.contains("String")){
                    System.out.println(fileName + "::" + line);
                }
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
