package com.sincosmos.algorithms.leetcode;

public class P215FindKthLargest {
    public int findKthLargest(int[] nums, int k) {
        for(int i = nums.length -1; i >= nums.length - k; --i){
            heap(nums, i);
            swap(nums, 0, i);
        }
        return nums[nums.length - k];
    }

    public void swap(int[] nums, int i, int j){
        int tmp = nums[i];
        nums[i] = nums[j];
        nums[j] = tmp;
    }

    public void heap(int[] nums, int ed){
        int root = (ed+1)/2 - 1;

        while(root >= 0){
            int swapped = root;
            while(swapped <= (ed+1)/2 - 1){
                int left = 2*swapped + 1, right=left + 1, max;
                max = left;
                if(right<=ed && nums[left]<nums[right]){
                    max = right;
                }
                if(nums[max] > nums[swapped]){
                    swap(nums, swapped, max);
                    swapped = max;
                }else{

                    break;
                }
            }
            root--;
        }
    }

    public static void main(String[] args) {
        P215FindKthLargest findKthLargest = new P215FindKthLargest();
        //int[] nums = {3,2,1,5,6,4};
        int[] nums = {3,2,3,1,2,4,5,5,6};
        System.out.print(findKthLargest.findKthLargest(nums, 4));
    }
}
