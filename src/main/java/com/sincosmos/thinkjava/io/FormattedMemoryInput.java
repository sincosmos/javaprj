package com.sincosmos.thinkjava.io;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;

public class FormattedMemoryInput {
	public static void main(String[] args) throws IOException {
		String filename = "."+ File.separator + "10.27 weekly report";
		String outname = "10.27 weekly report.out";
		
		BufferedReader buf = new BufferedReader(new FileReader(filename));
		//PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter(outname)));
		PrintWriter out = new PrintWriter(outname);
		String str;
		StringBuffer sb = new StringBuffer();
		while((str=buf.readLine())!=null) {
			sb.append(str + "\n");
			out.println(str);
			System.out.println(str);
		}
		
		buf.close();
		out.close();
		
		InputStreamReader in = new InputStreamReader(
				new ByteArrayInputStream(sb.toString().getBytes()), "UTF-8"
				);
		
		/*DataInputStream in = new DataInputStream( 
			new BufferedInputStream(
					new ByteArrayInputStream(sb.toString().getBytes()))); */
	    int tmp;
		while((tmp=in.read())!=-1) 
	    	System.out.print((char)tmp);
	    in.close();
	}
}
