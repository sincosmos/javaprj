package com.sincosmos.thinkjava.generics;

import java.util.ArrayList;
import java.util.List;

public class ReturnInstanceWithGenericType {

    public <T> List<T> listOf(Class<T> clazz, T value){
        List<T> list = new ArrayList<>();
        list.add(value);
        return list;
    }

    public <T> List<T> magicalListGetter() {
        return new ArrayList<T>();
    }

    public static void main(String[] args){
        ReturnInstanceWithGenericType genericList = new ReturnInstanceWithGenericType();
        List<String> a = genericList.listOf(String.class, "hello");
        a.add("world");

        List<Integer> b = genericList.magicalListGetter();
        b.add(10);
    }
}
