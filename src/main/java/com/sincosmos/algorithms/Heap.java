package com.sincosmos.algorithms;

import java.util.stream.IntStream;

public class Heap {
    public void heapAscSort(int[] arr){
        arrayToMaxHeap(arr, arr.length);
        for(int i = arr.length - 1; i>=0; --i){
            swap(arr, 0, i);
            arrayToMaxHeap(arr, i);
        }
    }

    public void arrayToMaxHeap(int[] arr, int len){
        int root = len / 2 - 1;

        for(int i = root; i>=0; --i){
            maxUp(arr, i, len);
        }
    }

    public void arrayToMinHeap(int[] arr, int len){
        int root = (len - 1) / 2;

        for(int i = root; i>=0; --i){
            //maxUp(arr, root, len);
        }
    }


    private void maxUp(int[] arr, int root, int len){
        int left = 2 * root + 1;
        int right = left + 1;

        int max = root;

        if(arr[left] > arr[max]){
            max = left;
        }
        if(right < len && arr[right] < arr[max]){
            max = right;
        }
        if(max != root){
            swap(arr, max, root);
            maxUp(arr, max, len);
        }
    }

    private void swap(int[] arr, int a1, int a2){
        int tmp = arr[a1];
        arr[a1] = arr[a2];
        arr[a2] = tmp;
    }


    public static void main(String[] args){
        int[] arr = {52,435,85,2,9,90,45,894,42,98,88,683,5,54,4};
        Heap heap = new Heap();
        heap.arrayToMaxHeap(arr, arr.length);
        //heap.heapAscSort(arr);
        IntStream.of(arr).forEach(System.out::println);
    }
}
