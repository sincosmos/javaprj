package com.sincosmos.collection;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Iterator;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Set;

public class StackTest {
    public static void main(String[] args){
        /*Stack<Integer> stack = new Stack<>(10);
        for(int i=0; i<11; i++){
            stack.push(i);
        }
        System.out.println("stack pop top::" + stack.pop());
        System.out.println("stack peek top::" + stack.peek());
        stack.forEach(System.out::println);*/

        LocalDate today = LocalDate.now();
        LocalDate ndaysAgo = today.minusDays(5);
        System.out.println(today.format(DateTimeFormatter.ISO_LOCAL_DATE));
        System.out.println(ndaysAgo.format(DateTimeFormatter.ISO_LOCAL_DATE));
        ClassLoader cl = StackTest.class.getClassLoader();
        System.out.println(cl.toString());
        try {
            Class clazz = cl.loadClass("com.sincosmos.collection.PowerSet");
            PowerSet powSet = (PowerSet) clazz.newInstance();
            Set<Integer> set = new HashSet<>(Arrays.asList(1,2,3));
            Set<Set<Integer>> sub = powSet.powerSet(set);
            System.out.println(sub);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        }
    }
}
