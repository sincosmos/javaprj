package com.sincosmos.thinkjava.arrays;


class BerylliumSphere{
	private static long counter;
	private final long id = counter++;
	public String toString() {
		return "Sphere " + id;
	}
}

public class ArrayInitializtion {
	public void sphere(BerylliumSphere[] sps){
		for(BerylliumSphere sp : sps)
			System.out.println(sp);
	}
}
