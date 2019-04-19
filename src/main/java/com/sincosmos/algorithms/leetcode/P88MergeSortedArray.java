package com.sincosmos.algorithms.leetcode;

import java.util.Arrays;
import java.util.stream.IntStream;

/**
 * Given two sorted integer arrays nums1 and nums2, merge nums2 into nums1 as one sorted array.
 *
 * Note:
 *
 * The number of elements initialized in nums1 and nums2 are m and n respectively.
 * You may assume that nums1 has enough space (size that is greater or equal to m + n) to hold additional elements from nums2.
 * Example:
 *
 * Input:
 * nums1 = [1,2,3,0,0,0], m = 3
 * nums2 = [2,5,6],       n = 3
 *
 * Output: [1,2,2,3,5,6]
 */
public class P88MergeSortedArray {
    public void merge(int[] nums1, int m, int[] nums2, int n) {
        int last = m + n - 1;
        while(m > 0 && n > 0){
            if(nums1[m-1] > nums2[n-1]){
                nums1[last--] = nums1[--m];
            }else{
                nums1[last--] = nums2[--n];
            }
        }
        while(n>0){
            nums1[last--] = nums2[--n];
        }
    }

    public static void mergeInsert(int[] nums1, int m, int[] nums2, int n) {
        //copy
        for(int i=0; i<n; ++i){
            nums1[m+i] = nums2[i];
        }
        //insert sort
        int cur, ipos;
        for(int i=1; i<m+n; ++i){
            cur = nums1[i];
            ipos = i-1;
            while(ipos>=0 && nums1[ipos] > cur){
                nums1[ipos + 1] = nums1[ipos];
                ipos--;
            }
            nums1[ipos+1] = cur;
        }
    }

    public static void main(String[] args){
        int[] nums1={2,0};
        int[] nums2={1};
        mergeInsert(nums1, 1, nums2, 1);
        IntStream.of(nums1).forEach(x->System.out.print(x+" "));
    }
}
