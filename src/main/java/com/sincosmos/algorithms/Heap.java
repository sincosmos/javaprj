package com.sincosmos.algorithms;

import java.util.stream.IntStream;

/**
 * 堆是一种可以被看作完全二叉树的数组对象。数组是堆的实际存储方式，完全二叉树是堆的对外性质。
 * 对于堆中的任意一个元素，其在数组中的下标是 i（第一个元素的下标是 0），那么该元素的父节点的下标是 (i-1)/2 向下取整，
 * 该元素的左子节点下标是 2*i + 1，右子节点下标是 2*i + 2。
 * 1) 大顶堆要求对于堆中任意一个元素，其值不小于其左、右子节点的元素值，不大于其父节点的元素值
 * 2) 小顶堆要求对于堆中任意一个元素，其值不大于其左、右子节点的元素值，不小于其父节点的元素值
 * 根据堆的性质，我们很容易知道大顶堆堆顶元素（下标 0）一定是数组的最大值；小顶堆堆顶元素（下标0）一定是数组的最小值。
 * 注意，大顶堆和小顶堆的数组元素不一定（很可能不）是有序的。
 *
 * 数组堆化
 * 数组堆化的过程，就是调整数组中元素的顺序，使每个元素都满足堆的性质的过程。
 * 以大顶堆的构建为例，从数组中最后一个父节点开始调整。
 * 最后一个父节点的下标 root 应该至少存在左节点，即 2*root + 1 <= array.length - 1, root <= array.length / 2 - 1，
 * 所以最后一个父节点的下标是 floor(array.length/2 - 1)。
 * 从最后一个父节点开始，如果它大于左、右（如果存在）子节点的中的最大值，则说明该父节点满足堆的性质，可以将 root--，继续调整下一个
 * 父节点；如果它小于左、右（如果存在）子节点的中的最大值 max（下标为 m），则将它与最大值交换位置，使当前父节点满足堆的性质。
 * 但是交换后，被交换的子节点如果还有子节点的话，由于 array[m] 变小，可能导致 array[m] 和它的子节点之间不满足堆的性质。
 * 因此需要递归地(或非递归)调整 array[m] 和它的子节点，直到父节点满足堆的性质，或调整后的节点没有子节点。
 * 当对 root = 0 的节点完成调整后，数组堆化就完成了。此时，
 * 由于 array[0]>=array[2*0+1] = array[1] && array[0]>=array[2*0+2] = array[2],
 * array[1]>=array[2*1+1] = array[3] && array[1]>=array[2*1+2] = array[4],
 * array[2]>=array[2*2+1] = array[5] && array[2]>=array[2*2+2] = array[6]...,
 * 可以知道 array[0] 必然是整个数组中的最大值。
 *
 * 插入元素
 * 数组形成的堆中插入元素，则堆大小加 1。可以先把待插入元素放在堆的最末端，比较该元素与其父节点是否满足堆的性质，如果不满足则交换
 * 父节点和该元素，直到堆的性质被满足。这个过程元素自下而上，我们常称之为上滤。
 *
 * 删除元素
 * 二叉堆删除是删除头节点的操作，对于大顶堆来说，就是删除最大的元素。首先将头节点和堆最末端的元素交换位置（交换后，最末端的元素
 * 不再被视为堆的一部分，堆的大小减 1），比较新的头节点与其子节点是否满足堆的性质，如果不满足则交换父节点和该元素，直到堆的性质
 * 被满足。这个过程元素自上而下，我们常称之为下沉。
 *
 * 堆排序
 * 可以利用堆对数组进行排序，大顶堆实现升序排列，小顶堆实现降序排列。
 * 以大顶堆实现升序排列为例，我们设数组长度为 len。在堆排序过程中，将数组分为前后两部分，前面一部分是无序区，长度为 heapSize，
 * 后面一部分是有序区。初始状态下 heapSize = len。排序过程如下。
 * step 1. 将数组 heapSize 部分转化为大顶堆
 * step 2. 交换堆顶元素（index = 0，为堆区最大值）与堆区（无序区）最后一个元素交换
 * step 3. 交换后，最后一个元素进入了有序区，堆区变小（heapSize--）
 * 重复 step 1,2,3 直到 heapSize=1，此时数组排序完成
 *
 * Top K
 * 例如从上百万个数字中，取出最大的 10 个数字；可以首先读入 10 个数字，构建一个大小为 10 的最小堆，然后依次读入剩下的数字，如果
 * 当前数字大于最小堆堆顶元素，则删除堆顶元素，插入新元素，并调整堆使之满足最小堆堆性质。这样可以避免将上百万数字一次性加载进内存。
 *
 * 中位数
 * 中位数就是 Top arr.length/2 问题
 *
 * 优先队列
 *
 */
public class Heap {
    private int[] heap;
    //堆容量
    private int capacity = 0;
    //堆中有序区的大小
    private int heapSize = 0;

    //默认构建大顶堆
    private boolean maxHeap = true;

    public boolean isMaxHeap() {
        return maxHeap;
    }

    public Heap(){
        this(10);
    }

    public Heap(int capacity){
        if(capacity > 0){
            this.capacity = capacity;
            this.heap = new int[capacity];
        }else{
            throw new IllegalArgumentException("ERROR: wrong initial heap size");
        }
    }

    public Heap(int capacity, boolean maxHeap){
        this(capacity);
        this.maxHeap = maxHeap;
    }

    /**
     * 往堆中插入元素
     * @param x 待插入的元素
     */
    public void insert(int x){
        if(heapSize >= capacity){
            System.out.println("ERROR: Heap is full");
            return;
        }
        heap[heapSize] = x;
        if(maxHeap) maxHeapInsert(x);
        else minHeapInsert(x);
    }

    /**
     * 往大顶堆堆中插入元素
     * @param x 待插入的元素
     */
    private void maxHeapInsert(int x){

        int xpos = heapSize;
        int p=Heap.parent(xpos);
        //上滤
        for(; p>=0; p=Heap.parent(xpos)){
            if(heap[p]<heap[xpos]){
                Heap.swap(heap, p, xpos);
                xpos = p;
            }else{
                break;
            }
        }

        heapSize++;
    }

    /**
     * 往小顶堆堆中插入元素
     * @param x 待插入的元素
     */
    private void minHeapInsert(int x){

        int xpos = heapSize;
        int p=Heap.parent(xpos);
        //上滤
        for(; p>=0; p=Heap.parent(xpos)){
            if(heap[p]>heap[xpos]){
                Heap.swap(heap, p, xpos);
                xpos = p;
            }else{
                break;
            }
        }

        heapSize++;
    }

    /**
     * 查看堆顶元素
     */
    public int peek(){
        if(heapSize == 0){
            throw new IndexOutOfBoundsException("ERROR: Heap is empty");
        }
        return heap[0];
    }

    /**
     * 删除堆顶元素
     */
    public int pop(){
        if(heapSize == 0){
            throw new IndexOutOfBoundsException("ERROR: Heap is empty");
        }
        if(maxHeap) return maxHeapDelete();
        else return minHeapDelete();
    }

    /**
     * 删除大顶堆顶元素
     */
    private int maxHeapDelete(){
        int rtn = heap[0];
        heap[0] = heap[--heapSize];
        int xpos = 0;
        int left, max;
        while((left = Heap.left(xpos))<heapSize){
            //首先找出左右子节点中较大的
            max = left;
            if(left+1<heapSize && heap[left+1]>heap[left]){
                max = left + 1;
            }
            //比较父节点和较大子节点
            if(heap[max] > heap[xpos]){
                Heap.swap(heap, max, xpos);
                xpos = max;
            }else{
                //父节点比两个子节点都大，满足堆性质
                break;
            }
        }
        return rtn;
    }

    /**
     * 删除小顶堆顶元素
     */
    private int minHeapDelete(){
        int rtn = heap[0];
        heap[0] = heap[--heapSize];
        int xpos = 0;
        int left, min;
        while((left = Heap.left(xpos))<heapSize){
            //首先找出左右子节点中较小的
            min = left;
            if(left+1<heapSize && heap[left]>heap[left+1]){
                min = left + 1;
            }
            //比较父节点和较小子节点
            if(heap[min] < heap[xpos]){
                Heap.swap(heap, min, xpos);
                xpos = min;
            }else{
                break;
            }
        }
        return rtn;
    }


    /**
     * @return 树状堆结构
     */
    public String toString(){
        int height = (int) Math.ceil(Math.log(heapSize) / Math.log(2));
        StringBuilder sb = new StringBuilder();
        for(int i=0; i<height; i++){
            //层偏移
            for(int k=0; k<height-i; ++k) sb.append(" ");
            //当前层中元素个数
            int cur = (int) Math.pow(2, i);
            for(int j=cur-1; j< 2 * cur - 1 && j<heapSize; j++){
                sb.append(" " + heap[j]);
            }
            sb.append("\n");
        }

        return sb.toString();
    }

    private static void swap(int[] arr, int a1, int a2){
        int tmp = arr[a1];
        arr[a1] = arr[a2];
        arr[a2] = tmp;
    }

    private static int parent(int pos){
        return (int) Math.floor((double)(pos - 1)/2);
    }

    private static int left(int pos){
        return (pos << 1) + 1;
    }

    /**
     * 利用最大堆实现数组升序排列
     */
    public static void topK(int k, int[] arr){
        Heap heap = new Heap(k,false);
        for(int i=0; i<k; ++i){
            heap.insert(arr[i]);
        }
        for(int i=k; i<arr.length; ++i){
            if(heap.peek() < arr[i]){
                heap.pop();
                heap.insert(arr[i]);
            }
        }
        System.out.println(heap);
        for(int i=0; i<k; ++i){
            System.out.println(heap.pop() + "");
        }

    }

    /**
     * 利用最大堆实现数组升序排列
     * @param arr 待排序数组
     */
    public static void heapAscSort(int[] arr){
        for(int i=arr.length; i>0; --i){
            //将无序区转化为最大堆，则无序区的最大元素必在堆顶
            Heap.arrayToMaxHeap(arr, i);
            //将无序区最大元素放在堆后一个位置，则无序区减小 1
            Heap.swap(arr, 0, i-1);
        }
    }

    /**
     * 将数组无序区转化为最大堆
     * @param arr 被转化为最大堆的数组
     * @param heapSize 数组中未排序区域长度
     */
    private static void arrayToMaxHeap(int[] arr, int heapSize){
        //找到无序区最后一个元素父节点
        int parent = Heap.parent(heapSize - 1);
        //从无序区最后一个元素的父节点开始调节，直到第一个父节点
        //使所有父、子节点的满足堆堆要求
        for(int i=parent; i>=0; --i){
            Heap.maxUp(arr, i, heapSize);
        }
    }

    /**
     * 使用非递归的算法调整数组，使数组中从索引为 root 的元素开始到所有 root 的子孙元素的大小符合堆结构要求
     * 即，arr[root] >= arr[left] && arr[root] >= arr[right] 依次往下
     * @param arr 转化为最大堆的数组
     * @param root 当前根节点
     * @param heapSize 数组中堆区域的长度
     */
    private static void maxUp(int[] arr, int root, int heapSize){
        int left, max, cur=root;
        while((left = Heap.left(cur)) < heapSize){
            max = left;
            //如果右子节点存在且较大
            if(left + 1 < heapSize && arr[left + 1]>arr[left]){
                max = left + 1;
            }
            if(arr[max] > arr[cur]){
                Heap.swap(arr, max, cur);
                cur = max;
            }else{
                //父节点大于左右子节点，满足堆堆性质
                break;
            }
        }
    }

    public static void main(String[] args){
        int[] arr = {52,435,85,2,9,90,45,894,42,98,88,88,683,5,54,4};
        Heap.heapAscSort(arr);
        IntStream.of(arr).forEach((x)->{System.out.print(x + "\t");});
        Heap.topK(10, arr);
    }

    /**
     * 小顶堆实现数据降序排序
     * @param arr
     */
    public static void headDescSort(int [] arr){
        int heapPart = arr.length - 1;
        while(heapPart > 0){
            heap(arr, heapPart);
            swap(arr, 0, heapPart);
            heapPart--;
        }

    }

    private static void heap(int[] arr, int ed) {
        int root = (ed + 1) / 2;
        int left, right, min;
        while(root >= 0){
            left = 2 * root + 1;
            right = 2 * root + 2;
            if(right <= ed && arr[left] < arr[right]){
                min = right;
            }else{
                min = left;
            }
            if(arr[root] > arr[min]){
                swap(arr, root, min);

                while(min <= (ed + 1) / 2){
                    int l = 2 * min + 1;
                    int r = 2 * min + 2;
                    if(r<=ed && arr[l] < arr[right]){
                        min = r;
                    }else{
                        min = l;
                    }

                }
            }

            root--;
        }
    }

}
