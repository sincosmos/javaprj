package com.sincosmos.thinkjava.io;

import java.io.File;
import java.util.Arrays;
import java.util.regex.Pattern;

public class DirList {
	public static void main(String[] args) {
		File path = new File(".");
		String[] files = null;
		if(args.length == 0 ) {
			files = path.list();
		}else {
			files = path.list( (dir, str) -> {
					Pattern pattern = Pattern.compile(args[0]);
					return pattern.matcher(str).matches();
				});
		}
		Arrays.asList(files).forEach(System.out::println);
	}
}
