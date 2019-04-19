package com.sincosmos.algorithms;

/**
 * 给定排序数组a[]，和一个元素k，求k在a[]中出现的次数，时间复杂度要求不大于O(log n)
 */
public class BinarySearch2 {
    public static int binarySearch(int[] arr, int target, boolean firstOrLast){
        int st = 0, ed = arr.length - 1;
        int mid;

        while(st<=ed){
            mid = st + (ed-st) / 2;
            if(arr[mid] == target) {
                if(firstOrLast){
                    //find first occurrence
                    if(mid == 0) return mid;
                    if(arr[mid-1] != target) return mid;
                    ed = mid - 1;
                }else{
                    //find last
                    if(mid == arr.length - 1) return mid;
                    if(arr[mid+1] != target) return mid;
                    st = mid + 1;
                }
            }else if(arr[mid] > target) {
                ed = mid - 1;
            }else{
                st = mid + 1;
            }

        }

        return -1;
    }

    public static void main(String[] args) {
        int[] arr = {1,2,3,3,3,4,5,6,7,10,10,10,10};
        int target = 10;
        int first = binarySearch(arr, target, true);
        int last = binarySearch(arr, target, false);
        System.out.println("the occurrence is: " + (last - first + 1));
    }
}
