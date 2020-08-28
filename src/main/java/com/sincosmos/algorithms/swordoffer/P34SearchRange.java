package com.sincosmos.algorithms.swordoffer;

public class P34SearchRange {
    public int[] searchRange(int[] nums, int target) {
        int first = searchFirst(nums, 0, nums.length-1, target);
        int last = searchLast(nums, 0, nums.length-1, target);
        return new int[]{first,last};

    }

    public int searchFirst(int[] nums, int st, int ed, int target){
        if(st>ed) return -1;
        int mid = st + (ed-st)/2;
        if(nums[mid] == target){
            int first = searchFirst(nums, st, mid-1, target);
            if(first == -1){
                return mid;
            }else{
                return first;
            }
        }else if(nums[mid]<target){
            return searchFirst(nums, mid+1, ed, target);
        }else{
            return searchFirst(nums, st, mid-1, target);
        }
    }

    public int searchLast(int[] nums, int st, int ed, int target){
        if(st>ed) return -1;
        int mid = st + (ed-st)/2;
        if(nums[mid] == target){
            int last = searchLast(nums, mid+1, ed, target);
            if(last == -1){
                return mid;
            }else{
                return last;
            }
        }else if(nums[mid]<target){
            return searchLast(nums, mid+1, ed, target);
        }else{
            return searchLast(nums, st, mid-1, target);
        }
    }
}
