package com.sincosmos.thinkjava.io;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

public class BufferedInputFile {
	public static void main(String[] args) throws IOException {
		String filename = "."+ File.separator + "pom.xml";
		BufferedReader in = new BufferedReader(new FileReader(filename));
		List<String> buf = new LinkedList<>();
		
		String tmp;
		while((tmp = in.readLine())!=null) {
			buf.add(tmp);
		}
		in.close();
		Collections.reverse(buf);
		buf.stream().map((s)->s.toUpperCase())
			.filter((s)->s.matches("^\\S+.*"))
			.forEach(System.out::println);
	}
}
