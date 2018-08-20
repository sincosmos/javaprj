package com.sincosmos.algorithms;

import java.util.*;

class Person implements Comparable<Person>{
    public String name;
    public Integer age;

    public Person(String name, Integer age){
        this.name = name;
        this.age = age;
    }

    @Override
    public int compareTo(Person o1) {
        return Integer.compare(this.age, o1.age);
    }

    @Override
    public String toString(){
        return this.name + ": " + this.age;
    }
}

class Node<T> {
    T elem;
    Node<T> next;
    public Node(T elem){
        this.elem = elem;
    }

    public Node<T> next(){
        return next;
    }
    public void next(Node<T> next){
        this.next = next;
    }
}

class NodeList<T> implements Iterable<T>{
    Node<T> head = null;
    public NodeList(){
    }
    public void addNode(Node<T> next){
        if(head == null) {
            head = next;
            return;
        }
        Node<T> cur = head;
        while(cur.next() != null){
            cur = cur.next();
        }
        cur.next(next);
    }

    public Node<T> head(){
        return this.head;
    }

    @Override
    public Iterator<T> iterator() {
        return null;
    }
}

public class TreeSetPractice {

    public static TreeSet<Integer> getTopInList(int[] arr, int n){
        TreeSet<Integer> top = new TreeSet<>();
        for(Integer i : arr){
            if(top.size()<n+1){
                top.add(i);
            }else{
                if(top.first() < i){
                    top.pollFirst();
                    top.add(i);
                }
            }
        }
        return top;
    }

    public static void resortLinkedList(NodeList<Integer> nodeList){
        Node<Integer> head = nodeList.head();
        Node<Integer> cur = nodeList.head();
        while(cur.next() != null){
            cur = cur.next();
        }
    }

    public static void displaySubset(SortedSet<Integer> set){
        if(set.size() == 0){
            System.out.println(set.toString());
        }
        int cur = set.first();
        System.out.print("{" + cur);
        SortedSet<Integer> remain = set.tailSet(cur);

    }

    public static void main(String[] args) {
        int[] arr = new int[60];
        Random rand = new Random(43);

        for (int i = 0; i < 60; ++i) {
            arr[i] = rand.nextInt(100);
        }
        //System.out.println("original array after sort");
        //Arrays.sort(arr);
        //Arrays.stream(arr).forEach(x -> System.out.print(x + "\t"));
        //System.out.println();

        TreeSet<Integer> top = getTopInList(arr, 5);
        top.descendingSet().stream().forEach(x -> System.out.print(x + "\t"));

        String[] names = new String[]{"Jim", "WangTao", "Mary", "John", "Lily", "Tom", "Song", "Fred", "Diana"};
        TreeSet<Person> persons = new TreeSet<>();
        for(int i=0; i<20; ++i){
            Person person = new Person(names[rand.nextInt(9)], rand.nextInt(100));
            persons.add(person);
        }
        persons.stream().forEach(System.out::println);

        System.out.println();
        //Returns the least element in this set greater than or equal to the given element, or null if there is no such element.
        System.out.println(persons.ceiling(new Person("Liang", 35)));
        System.out.println(persons.floor(new Person("Liang", 35)));
        System.out.println(persons.first());
        System.out.println(persons.last());
        System.out.println(persons.higher(new Person("Liang", 35)));
        System.out.println(persons.lower(new Person("Liang", 35)));

        NodeList<Integer> nodeList = new NodeList<>();
        for(int i=1; i<10; ++i){
            nodeList.addNode(new Node<Integer>(i));
        }

        System.out.println();
        //while((Node<Integer> cur = nodeList.n)
    }
}
