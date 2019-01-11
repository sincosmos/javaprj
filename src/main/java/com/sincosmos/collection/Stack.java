package com.sincosmos.collection;

import java.util.AbstractCollection;
import java.util.Iterator;

public class Stack<E>  extends AbstractCollection<E>{
    private Object[] items;
    private int topIndex;

    public Stack(int capacity){
        if (capacity <= 0)
            throw new IllegalArgumentException();
        this.items = new Object[capacity];
        topIndex = -1;
    }

    @Override
    public boolean add(E e){
        if(push(e)){
          return true;
        }else{
            throw new IllegalStateException("Stack full");
        }
    }

    public E peek() {
        if(topIndex < 0)
            return null;
        return (E) items[topIndex];
    }

    public boolean push(E e) {
        if (e == null)
            throw new NullPointerException();
        if(topIndex + 1 == items.length)
            return false;
        items[++topIndex] = e;
        return true;
    }

    public E pop() {
        if(topIndex < 0)
            return null;
        //help GC to reclaim memory
        items[topIndex] = null;
        return (E) items[topIndex--];
    }

    @Override
    public Iterator<E> iterator() {
        return new Iterator<E>() {
            private int cursor = topIndex;
            @Override
            public boolean hasNext() {
                return cursor >= 0;
            }

            @Override
            public E next() {
                return (E) items[cursor--];
            }
        };
    }

    @Override
    public int size() {
        return topIndex + 1;
    }
}
