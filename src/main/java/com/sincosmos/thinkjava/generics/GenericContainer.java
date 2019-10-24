package com.sincosmos.thinkjava.generics;

import java.util.ArrayList;

public class GenericContainer<T> {
    InternalContainer<T> internalContainer = new InternalContainer<>();
    public void put(T elem){
        internalContainer.put(elem);
    }

    public static void main(String[] args){
        GenericContainer<String> strings = new GenericContainer<>();
        strings.put("He");
    }
}

class InternalContainer<V> extends ArrayList<V>{

    public void put(V elem) {
        this.add(elem);
    }
}
