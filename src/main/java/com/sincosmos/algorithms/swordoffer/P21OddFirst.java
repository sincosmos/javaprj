package com.sincosmos.algorithms.swordoffer;

public class P21OddFirst {
    public int[] exchange(int[] nums) {
        int i=0, j=nums.length-1;
        while(i<j){
            while((nums[i] & 1) == 1 && i<j){
                //nums[i] 是奇数
                ++i;
            }
            while((nums[j] & 1) == 0 && i<j){
                //nums[j] 是偶数
                --j;
            }
            //nums[i] 是偶数，nums[j]是奇数，交换二者
            int tmp = nums[i];
            nums[i] = nums[j];
            nums[j] = tmp;
            i++;
            j--;
        }
        return nums;
    }
}
