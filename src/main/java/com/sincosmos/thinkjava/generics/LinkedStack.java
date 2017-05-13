package com.sincosmos.thinkjava.generics;

public class LinkedStack <T> {
	
	/* Inner class Node: a container to store an element in the stack
	 * */
	/*private static class Node<U> {
		U item;
		Node<U> next;
		Node() {
			item = null;
			next = null;
		}
		Node(U item, Node<U> next){
			this.item = item;
			this.next = next;
		}
		boolean end(){
			return item == null && next == null;
		}
	}*/
	
	/*private Node<T> top = new Node<T>(); //End sentinel
	
	public void push(T item){
		top = new Node<T>(item, top);
	}
	
	public T pop(){
		T result = top.item;
		if(!top.end()){
			top = top.next;
		}
		return result;
	}*/
	
	private class Node {
		T item;
		Node next;
		Node() {
			item = null;
			next = null;
		}
		Node(T item, Node next){
			this.item = item;
			this.next = next;
		}
		boolean end(){
			return item == null && next == null;
		}
	}
	
	private Node top = new Node(); //End sentinel
	
	public void push(T item){
		top = new Node(item, top);
	}
	
	public T pop(){
		T result = top.item;
		if(!top.end()){
			top = top.next;
		}
		return result;
	}
	
	//范型静态方法的类型参数和范型类的类型参数是相互独立的，即这里的 T 和范型类的 T 可以是不同的。
	public static <T> void genericStaticMethod(T item){
		System.out.println(item);
	}
}
