package com.sincosmos.algorithms.leetcode;

/**
 * Given an array with n objects colored red, white or blue, sort them in-place so that objects of the same color are adjacent, with the colors in the order red, white and blue.
 *
 * Here, we will use the integers 0, 1, and 2 to represent the color red, white, and blue respectively.
 *
 * Note: You are not suppose to use the library's sort function for this problem.
 *
 * Example:
 *
 * Input: [2,0,2,1,1,0]
 * Output: [0,0,1,1,2,2]
 */
public class P75SortColor {
    private void swap(int[] arr, int a1, int a2){
        int tmp = arr[a1];
        arr[a1] = arr[a2];
        arr[a2] = tmp;
    }
    public void sortColors(int[] nums) {
        int i=0, lastZero = 0, firstTwo = nums.length - 1;
        while(i<=firstTwo){
            if(nums[i] == 2){
                // nums[i] 与从后往前第一个不确定是否是 2 的元素交换位置
                swap(nums, i, firstTwo);
                // firstTwo 之后的元素都确定是 2，firstTwo 指向从后往前第一个不确定是否是 2 的元素
                firstTwo--;
                // 交换后，因为不确定 i 指向位置是几，因此 i 指向不变，下一个循环继续操作 nums[i】
                // 但由于循环跳出条件是 i<=firstTwo，firstTwo 减小导致循环元素个数仍然是 arr.length
                continue;
            }else if(nums[i] == 0){
                // nums[i] 与从前往后最后一个不确定是 0 的元素交换位置
                // 交换前，lastZero 指向的元素一定不会是 2，因为 [lastZero, i) 之间的 2 都已经被交换到后面
                // 因此，交换后的 nums[i] 可以不用处理，这里直接利用 if-else 语句外的 i++
                swap(nums, lastZero, i);
                lastZero++;
            }
            // 因为我们按顺序将所有的 0 放在 lastZero 前面，所有 2 放在 firstTwo 后面，
            // 因此，nums[i] == 1 时，i 直接向前行进
            i++;
        }
    }
}
