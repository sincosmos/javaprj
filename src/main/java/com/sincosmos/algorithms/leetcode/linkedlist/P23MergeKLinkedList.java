package com.sincosmos.algorithms.leetcode.linkedlist;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;

/**
 * Merge k sorted linked lists and return it as one sorted list. Analyze and describe its complexity.
 *
 * Example:
 *
 * Input:
 * [
 *   1->4->5,
 *   1->3->4,
 *   2->6
 * ]
 * Output: 1->1->2->3->4->4->5->6
 */
public class P23MergeKLinkedList {
    //最小堆
    static class MinHeap<T> {
        List<T> holder = new ArrayList<>();
        int size = 0;
        boolean isEmpty(){ return size == 0;}
        Comparator<T> comparator;
        //MinHeap(){}
        MinHeap(Comparator<T> comparator){
            this.comparator = comparator;
        }
        void insert(T x){
            holder.add(x);
            int child = size;
            int parent = parent(size);
            while(parent>=0){
                if(comparator.compare(holder.get(parent), holder.get(child)) > 0){
                    swap(parent, child);
                    child = parent;
                    parent = parent(parent);

                }else{
                    break;
                }
            }
            size++;
        }

        void swap(int parent, int child) {
            T tmp = holder.get(parent);
            holder.set(parent, holder.get(child));
            holder.set(child, tmp);
        }

        T remove(){
            if(isEmpty()) return null;
            T rtn = holder.get(0);
            T last = holder.remove(--size);
            if(rtn == last) return rtn;
            holder.set(0, last);
            int parent = 0 ;
            int left=left(0);
            int right= left + 1;
            while(left < size){
                int min = left;
                if(right < size && comparator.compare(holder.get(left), holder.get(right)) > 0){
                    min = right;
                }
                if(comparator.compare(holder.get(min), holder.get(parent)) < 0){
                    swap(min, parent);
                    parent = min;
                    left = left(parent);
                    right = left + 1;
                }else{
                    break;
                }
            }
            return rtn;
        }

        int parent(int i){return (i - 1) >> 1;}
        int left(int i){return (i<<1) + 1;}
    }

    public static ListNode mergeKLists(ListNode[] lists) {
        PriorityQueue<ListNode> queue = new PriorityQueue<>(Comparator.comparingInt(x -> x.val));
        ListNode tmp;
        for(int i=0; i<lists.length; ++i){
            tmp = lists[i];
            if(tmp != null){
                queue.offer(tmp);
            }
        }
        ListNode head, tail;
        head = tail = new ListNode(-1);

        while(!queue.isEmpty()){
           tmp = queue.poll();
           if(tmp.next != null){
               queue.offer(tmp.next);
           }
           tail.next = tmp;
           tail = tmp;
        }
        return head.next;
    }

    public static ListNode mergeKLists2(ListNode[] lists) {
        MinHeap<ListNode> queue = new MinHeap<>(Comparator.comparingInt(x -> x.val));
        ListNode tmp;
        for(int i=0; i<lists.length; ++i){
            tmp = lists[i];
            if(tmp != null){
                queue.insert(tmp);
            }
        }
        ListNode head, tail;
        head = tail = new ListNode(-1);

        while(!queue.isEmpty()){
            tmp = queue.remove();
            if(tmp.next != null){
                queue.insert(tmp.next);
            }
            tail.next = tmp;
            tail = tmp;
        }
        return head.next;
    }

    public static void main(String[] args){
        MinHeap<Integer> heap = new MinHeap<>(Integer::compare);

        heap.insert(11);
        heap.insert(8);
        heap.insert(11);
        heap.insert(3);
        heap.insert(12);
        heap.insert(5);
        heap.insert(8);
        heap.insert(7);
        heap.insert(3);

        while(!heap.isEmpty()){
            Integer x = heap.remove();
            System.out.println(x);
        }
    }
}
