package com.sincosmos.algorithms;

import java.math.BigInteger;

/**
 * 程序健壮性
 *    1. 对输入参数进行合法性校验，避免非法输入
 *    2. 查找中位数时，考虑剩余元素的个数，使用 BigInteger 避免 int 相加结果溢出
 * 程序可读性
 *    1. 将使用堆对数组进行排序的过程拆分，每一个方法目的明确，逻辑清晰，便于理解
 *    2. 添加说明文档，辅助阅读者理解
 * 程序高效性
 *   使用堆对数组进行排序，时间复杂度为 O(nlogn)，空间复杂度为 O(1)
 */
public class ArrayHandling {
    /**
     * 利用最大堆实现数组升序排列
     * @param arr 待排序数组
     */
    public void heapAscSort(int[] arr){
        for(int i=arr.length; i>0; --i){
            //将无序区转化为最大堆
            this.arrayToMaxHeap(arr, i);
            //将无序区最大元素放在堆后一个位置，则无序区减小 1，最大元素进入有序区
            this.swap(arr, 0, i-1);
        }
    }

    /**
     * 将数组无序区转化为最大堆
     * @param arr 被转化为最大堆的数组
     * @param heapSize 数组中未排序区域长度
     */
    private void arrayToMaxHeap(int[] arr, int heapSize){
        //找到无序区最后一个元素父节点
        int parent = this.parent(heapSize - 1);
        //从无序区最后一个元素的父节点开始调节，直到第一个父节点
        for(int i=parent; i>=0; --i){
            this.maxUp(arr, i, heapSize);
        }
    }

    /**
     * @param arr 转化为最大堆的数组
     * @param root 当前根节点
     * @param heapSize 数组中堆区域的长度
     */
    private void maxUp(int[] arr, int root, int heapSize){
        int left, max, cur=root;
        while((left = this.left(cur)) < heapSize){
            max = left;
            //如果右子节点存在且较大
            if(left + 1 < heapSize && arr[left + 1]>arr[left]){
                max = left + 1;
            }
            if(arr[max] > arr[cur]){
                this.swap(arr, max, cur);
                cur = max;
            }else{
                //父节点大于左右子节点，满足堆性质
                break;
            }
        }
    }

    public static void main(String[] args){
        int[] arr = new int[] {1, 1, 2, 3, 3, 3, 3, 4, 4, 5, 5, 6, 6, 7, 7, 8,
               8, 9, 9, 10, 10, 11, 11, 12, 12, 11, 12, 7, 8, 9, 11, 11, 12, 2, 11, 4, 4};

        //int[] arr = new int[] {1,2,3,4,5,6,7,8,9,10,11,12,13,14};


        ArrayHandling heap = new ArrayHandling();
        heap.heapAscSort(arr);

        int eleventh = arr.length - 1;
        int cnt = 1;
        for(int i=arr.length - 1; i>=0 && cnt!=11; --i){
            if(arr[i] < arr[eleventh]){
                eleventh = i;
                cnt++;
            }
        }

        if(cnt != 11){
            System.out.println("Error");
            return;
        }

        System.out.println("The eleventh largest number is: " + arr[eleventh]);

        int med = eleventh/2;
        //除去前 10 大元素后，剩余元素个数是 eleventh + 1
        if((eleventh & 1) == 0){
            //剩余奇数个元素
            System.out.println("剩余数字中的中位数: " + arr[med]);
        }else{
            //剩余偶数个元素
            System.out.println("剩余数字中的中位数: "
                    + BigInteger.valueOf(arr[med]).add(BigInteger.valueOf(arr[med+1])).doubleValue()/2);
        }
    }

    /**
     * 交换数组中两个元素
     */
    private void swap(int[] arr, int a1, int a2){
        int tmp = arr[a1];
        arr[a1] = arr[a2];
        arr[a2] = tmp;
    }

    /**
     * @param pos 当前节点下标
     * @return 父节点下标
     */
    private int parent(int pos){
        return (int) Math.floor((double)(pos - 1)/2);
    }

    /**
     * @param pos 当前节点下标
     * @return 左子节点下标
     */
    private int left(int pos){
        return (pos << 1) + 1;
    }
}
