package com.sincosmos;

import java.nio.ByteBuffer;
import java.util.Base64;
import java.util.stream.IntStream;

public class MainTest {
	public static void main(String[] args) {
		/*int [] intArr = {-39, 25, -63, -38, 103, -116, 8, -23, -85, 109, 16, 73, 42, -123, 93, -99, -118, 61, 121, -77, 73, -65, 127, -45};
		String base64 = convertToBase64(intArr);
		System.out.println(base64);*/
		
		String str = "Test中文666";
		
		char[] arr = str.toCharArray();
		for(int i = arr.length - 1; i>=0; i--) {
			System.out.print(arr[i]);
		}
	}
	
	public static String convertToBase64(int[] ints) {
	    ByteBuffer buf = ByteBuffer.allocate(ints.length);
	    IntStream.of(ints).forEach(i -> buf.put((byte)i));
	    return Base64.getEncoder().encodeToString(buf.array());
	}
}
