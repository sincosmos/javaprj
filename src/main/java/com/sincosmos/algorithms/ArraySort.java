package com.sincosmos.algorithms;

import java.util.Arrays;
import java.util.Random;

public class ArraySort {
    public static void quickSort(int[] arr, int st, int ed){
        if(st>=ed) return;
        //选取最后一个元素作为基准数
        int key = arr[ed];
        int i=st, j=ed;
        while(i<j){
            while(arr[i] <= key && i<j)
                ++i;
            while(arr[j] >= key && i<j)
                --j;

            //交换 arr[i] 和 arr[j]
            if(i<j){
                int temp = arr[j];
                arr[j] = arr[i];
                arr[i] = temp;
            }
        }
        // 交换 arr[i] 和 key
        arr[ed] = arr[i];
        arr[i] = key;
        quickSort(arr, st, i-1);
        quickSort(arr, i+1, ed);

    }

    /**
     * @param arr
     * @param st
     * @param ed arr.length - 1
     * @param sorted
     */
    public static void mergeSort(int[] arr, int st, int ed, int[] sorted){
        //只有一个数，是有序的
        if(st>=ed)
            return;
        int md = st + (ed-st)/2;
        mergeSort(arr, st, md, sorted);
        mergeSort(arr, md+1, ed, sorted);

        //merge two parts
        int k=st, i=st, j=md+1;
        while(i<=md && j<=ed){
            if(arr[i] > arr[j]){
                sorted[k++] = arr[j++];
            }else{
                sorted[k++] = arr[i++];
            }
        }
        while(i<=md){
            sorted[k++] = arr[i++];
        }
        while(j<=ed){
            sorted[k++] = arr[j++];
        }

        //copy sorted array
        for(k=st; k<=ed; ++k){
            arr[k] = sorted[k];
        }
    }

    public static void insertSort(int[] arr){
        for(int i=1; i<arr.length; ++i){
            int cur = arr[i];
            int j=i-1;
            while(j>=0 && arr[j] > cur){
                arr[j+1] = arr[j];
                j--;
            }
            arr[j+1] = cur;
            /*for(int j=i-1; j>=0; --j){
                if(arr[j] > cur){
                    arr[j+1] = arr[j];
                    arr[j]=cur;
                }else{
                    arr[j+1] = cur;
                    break;
                }
            }*/
            /*for(; j>=0 && arr[j] > cur; --j){
                arr[j+1]=arr[j];
            }
            arr[j+1] = cur;*/
        }
    }

    public static void bubblingSort(int[] arr){
        for(int i=0; i<arr.length; ++i){
            int min = arr[i];
            int idx = i;
            for(int j=i+1; j<arr.length; ++j){
                if(min > arr[j]){
                    min=arr[j];
                    idx = j;
                }
            }
            arr[idx] = arr[i];
            arr[i] = min;
        }
    }

    public static void main(String[] args){
        int[] arr = new int[21];
        Random rand = new Random(43);

        for(int i=0; i<21; ++i){
            arr[i] = rand.nextInt(100);
        }
        System.out.println("original array");
        Arrays.stream(arr).forEach(x -> System.out.print(x + "\t"));
        System.out.println();

        /*bubblingSort(arr);
        System.out.println("array after bublling sort");
        Arrays.stream(arr).forEach( x -> System.out.print(x + "\t"));
        System.out.println();*/

        /*System.out.println("array after insertion sort");
        insertSort(arr);
        Arrays.stream(arr).forEach( x -> System.out.print(x + "\t"));
        System.out.println();*/

        /*System.out.println("array after merge sort");
        int[] sorted = new int[arr.length];
        mergeSort(arr,0, arr.length - 1, sorted);
        Arrays.stream(arr).forEach( x -> System.out.print(x + "\t"));
        System.out.println();*/

        System.out.println("array after quick sort");
        quickSort(arr,0, arr.length - 1);
        Arrays.stream(arr).forEach( x -> System.out.print(x + "\t"));
        System.out.println();
    }
}
