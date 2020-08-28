package com.sincosmos.algorithms.array;

import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;

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

    public static void mergeSort(int[] arr, int[] sorted){
        //只有一个数，是有序的
        int st = 0, ed = arr.length - 1;
        int md = st + (ed-st)/2;

        while(st<ed){

        }

        if(st>=ed)
            return;

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
        }
    }

    public static void selectionSort(int[] arr){
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

    public static void bubblingSort(int[] arr){
        for(int i=0; i<arr.length; ++i){
            for(int j=0; j<arr.length-i-1; ++j){
                if(arr[j] > arr[j+1]){
                    int tmp = arr[j];
                    arr[j] = arr[j+1];
                    arr[j+1] = tmp;
                }
            }
        }
    }

    /**
     * 计数排序
     * @param arr
     */
    public static void countingSort(int[] arr){
        int min,max;
        min = max = arr[0];
        for(int i=0; i<arr.length; ++i){
            if(arr[i] < min){
                min = arr[i];
            }
            if(arr[i] > max){
                max = arr[i];
            }
        }
        int[] counts = new int[max-min+1];
        for(int i=0; i<arr.length; ++i){
            counts[arr[i] - min] += 1;
        }

        /*
        * 计算每个元素前面有多少个元素
        * */
        for(int i=1; i<counts.length; ++i){
            counts[i] += counts[i-1];
        }
        int[] sorted = new int[arr.length];
        // To make it stable we are operating in reverse order.
        for(int i=arr.length-1; i>=0; --i){
            //比 arr[i] 小的元素有 counts[arr[i] - min] - 1 个
            //因此 arr[i] 排序后的下标是 counts[arr[i] - min] - 1;
            sorted[counts[arr[i] - min] - 1] = arr[i];
            counts[arr[i] - min]--;
        }

        //下面的方式是正确的，但是相等的元素的顺序会发生改变
        /*for(int i=0; i<arr.length; ++i){
            sorted[counts[arr[i] - min] - 1] = arr[i];
            counts[arr[i] - min]--;
        }*/
        System.arraycopy(sorted, 0, arr, 0, arr.length);
    }


    /**
     * Input:
     * A list of unsorted data (between 0 and 1): 0.25 0.36 0.58 0.41 0.29 0.22 0.45 0.79 0.01 0.69
     * Array before Sorting: 0.25 0.36 0.58 0.41 0.29 0.22 0.45 0.79 0.01 0.69
     * Output:
     * Array after Sorting: 0.01 0.22 0.25 0.29 0.36 0.41 0.45 0.58 0.69 0.79
     * @param arr
     */
    public static void bucketSort(double[] arr){
        //平均每个桶 5 个元素
        int num = arr.length / 5 + 1;
        // num 个桶，每个桶中至多有 arr.length 个元素
        ArrayList[] buckets = new ArrayList[num];
        for(int i=0; i<num; ++i){
            buckets[i] = new ArrayList();
        }

        int bucketIndex = -1;
        for(int i=0; i<arr.length; ++i){
            // num * (arr[i] - min) / (max - min) - 1
            bucketIndex = (int) Math.floor(arr[i] * num);
        }
    }

    /**
     * @param arr 没有重复元素，非负整数
     */
    public static void bitMapSort(int[] arr){
        BitSet map = new BitSet();
        for(int i=0; i<arr.length; ++i){
            map.set(arr[i]);
        }
        /*Queue<Integer> significant = new ArrayDeque<>();
        map.stream().forEach(significant::add);
        Integer x;
        int i=0;
        while((x = significant.poll()) != null){
            arr[i++] = x;
        }*/

        AtomicInteger idx = new AtomicInteger(0);
        map.stream().forEach(v->arr[idx.getAndIncrement()] = v);
    }

    public static void main(String[] args){
        /*int[] arr = new int[5];
        int[] sortOrder = {4,3,1,5,2};


        Random rand = new Random(43);

        for(int i=0; i<5; ++i){
            arr[i] = rand.nextInt(100);
        }
        System.out.println("original array");
        Arrays.stream(arr).forEach(x -> System.out.print(x + "\t"));
        System.out.println();

        Map<Integer, Integer> kv = new TreeMap<>();
        for(int i=0; i<arr.length; ++i)
            kv.put(sortOrder[i], arr[i]);

        kv.entrySet().forEach(entry-> System.out.println("order: " + entry.getKey() + " value: " + entry.getValue()));*/

        /*selectionSort(arr);
        System.out.println("array after selection sort");
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

        /*System.out.println("array after quick sort");
        quickSort(arr,0, arr.length - 1);
        Arrays.stream(arr).forEach( x -> System.out.print(x + "\t"));
        System.out.println();*/

        /*System.out.println("array after bubbling sort");
        bubblingSort(arr);
        Arrays.stream(arr).forEach( x -> System.out.print(x + "\t"));
        System.out.println();*/

        int[] arr ={2,6,1,7,3,-2,3,7,-1,-2};
        countingSort(arr);
        Arrays.stream(arr).forEach(x -> System.out.print(x + "\t"));
        System.out.println();
    }
}
