package com.sincosmos.collection;

import java.util.*;

public class CollectionTest {
    public static void main(String[] args){
        List<String> test1 = new LinkedList<>();
        List<String> test2 = new ArrayList<>();

        String[] test3 = new String[]{"aa", "bb", "cc"};
        List<String> test4 = new ArrayList<String>(Arrays.asList(test3));

        Collections.reverse(test4);
        HashSet<String> test6 = new HashSet<>();
        test6.add(null);

        //NullPointerException
        TreeSet<String> test7 = new TreeSet<>();
        test7.add(null);
    }
}
