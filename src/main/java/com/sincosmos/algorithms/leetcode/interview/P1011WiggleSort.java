package com.sincosmos.algorithms.leetcode.interview;

import java.util.Arrays;

/**
 * 在一个整数数组中，“峰”是大于或等于相邻整数的元素，相应地，“谷”是小于或等于相邻整数的元素。例如，在数组{5, 8, 6, 2, 3, 4, 6}中，{8, 6}是峰， {5, 2}
 * 是谷。现在给定一个整数数组，将该数组按峰与谷的交替顺序排序。
 *
 * 示例:
 *
 * 输入: [5, 3, 1, 2, 3]
 * 输出: [5, 1, 3, 2, 3]
 *
 * 链接：https://leetcode-cn.com/problems/peaks-and-valleys-lcci
 */
public class P1011WiggleSort {
    public void wiggleSort(int[] nums) {
        if(nums == null) return;
        quickSort(nums, 0, nums.length-1);
        for(int i=0; i<nums.length; ++i){
            if(i+1<nums.length){
                swap(nums, i, i+1);
                i++;
            }
        }
    }

    public void quickSort(int[] nums, int st, int ed){
        if(st >= ed) return;
        int key = nums[ed];
        int i=st, j=ed;
        while(i < j){
            while(i < j && nums[i] <= key){
                i++;
            }
            while(i<j && nums[j] >= key){
                j--;
            }
            swap(nums, i, j);
        }
        swap(nums, ed, i);
        quickSort(nums, st, i-1);
        quickSort(nums, i+1, ed);
    }

    private void swap(int[] nums, int i, int j){
        int tmp = nums[i];
        nums[i] = nums[j];
        nums[j] = tmp;
    }


    public static void main(String[] args){
        P1011WiggleSort sort = new P1011WiggleSort();
        int[] arr ={2,6,1,7,3,-2,3,7,-1,-2};
        sort.quickSort(arr, 0, arr.length-1);
        Arrays.stream(arr).forEach(x -> System.out.print(x + "\t"));
        System.out.println();
    }
}
