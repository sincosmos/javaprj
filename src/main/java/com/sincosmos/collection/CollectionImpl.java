package com.sincosmos.collection;

import com.sun.istack.internal.NotNull;

import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class CollectionImpl<E> implements Collection<E> {
    private transient Object[] collection;
    private int size;

    CollectionImpl(){
        this.collection = new Object[]{};
    }

    CollectionImpl(int capacity){
        if(capacity>0)
            this.collection = new Object[capacity];
        else
            this.collection = new Object[]{};
    }

    CollectionImpl(@NotNull Collection<? extends E> c){
        this.collection = c.toArray();
        this.size = this.collection.length;
    }

    @Override
    public int size() {
        return this.size;
    }

    @Override
    public boolean isEmpty() {
        return this.size == 0;
    }

    @Override
    public boolean contains(Object o) {
        if(o == null){
            for(int i=0; i<size; i++){
                if(this.collection[i] == null) return true;
            }
        }else{
            for(int i=0; i<size; i++){
                if(o.equals(this.collection[i])) return true;
            }
        }
        return false;
    }

    @Override
    public Iterator<E> iterator() {
        return new Iterator<E>() {
            int cursor;
            @Override
            public boolean hasNext() {
                return cursor != size;
            }

            @Override
            public E next() {
                if(cursor >= size){
                    throw new NoSuchElementException();
                }
                int i = cursor;
                cursor++;
                return (E) collection[i];
            }
        };
    }

    @Override
    public Object[] toArray() {
        return Arrays.copyOf(this.collection, size);
    }

    @Override
    public <T> T[] toArray(T[] a) {
        return null;
    }

    @Override
    public boolean add(E e) {
        return false;
    }

    @Override
    public boolean remove(Object o) {
        return false;
    }

    @Override
    public boolean containsAll(Collection<?> c) {
        return false;
    }

    @Override
    public boolean addAll(Collection<? extends E> c) {
        return false;
    }

    @Override
    public boolean removeAll(Collection<?> c) {
        return false;
    }

    @Override
    public boolean retainAll(Collection<?> c) {
        return false;
    }

    @Override
    public void clear() {

    }
}
