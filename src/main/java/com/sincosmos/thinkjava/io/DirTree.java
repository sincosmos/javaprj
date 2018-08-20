package com.sincosmos.thinkjava.io;

import java.io.File;
import java.util.Arrays;
import java.util.regex.Pattern;

public class DirTree {
	public static void traverse(File path, int depth) {
		File[] files = path.listFiles((dir, name)->{
			Pattern pattern = Pattern.compile("^(?!\\.).*");
			return pattern.matcher(name).matches();
		});
		Arrays.asList(files).forEach((file)->{
			for(int i=0; i<depth; ++i) {
				System.out.print("  ");
			}
			System.out.println(file.toString());
			if(file.isDirectory()) {
				traverse(file, depth+1);
			}
		});
	}
	public static void main(String[] args) {
		File path = new File(".");
		if(args.length != 0 ) {
			path = new File(args[0]); 
		}		
		traverse(path, 0);
	}
}
