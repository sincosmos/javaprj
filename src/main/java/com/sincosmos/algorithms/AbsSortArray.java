package com.sincosmos.algorithms;

import java.util.Arrays;
import java.util.Comparator;

public class AbsSortArray {
    public static void main(String[] args) {
        //int[] arr = new int[]{1, 1, 2, 3, 3, 3, 3, 4, 4, 5, 5, 6, 6, 7, 7, 8,
        //       8, 9, 9, 10, 10, 11, 11, 12, 12, 11, 12, 7, 8, 9, 11, 11, 12, 2, 11, 4, 4};

        //Integer[] arr = new Integer[] {1,1,2,-3,4,5,6,7,8,9,10,11,11,12,13,13,13};
        int[] arr = new int[] {1,1,2,-3,4,5,6,7,8,9,10,11,11,12,13,13,13};

        //Arrays.sort(arr, Comparator.comparingInt(Math::abs));
        AbsSortArray.absQuickSort(arr, 0, arr.length -1);
        Arrays.stream(arr).forEach(x->System.out.print(x+" "));
    }

    /**
     * @param arr
     * @param st
     * @param ed inclusive
     */
    public static void absQuickSort(int[] arr, int st, int ed){
        if(st>=ed) return;
        int key = Math.abs(arr[ed]);
        int i=st, j=ed;
        while(i<j){
            while(Math.abs(arr[i])<=key && i<j){
                ++i;
            }
            while(Math.abs(arr[j])>=key && i<j){
                --j;
            }
            if(i<j){
                swap(arr, i, j);
            }
        }
        if(Math.abs(arr[i])>key){
            swap(arr, i, ed);
        }
        absQuickSort(arr, st, i-1);
        absQuickSort(arr,i+1,ed);
    }

    /**
     * 交换数组中两个元素
     */
    private static void swap(int[] arr, int a1, int a2){
        int tmp = arr[a1];
        arr[a1] = arr[a2];
        arr[a2] = tmp;
    }
}
