package com.sincosmos.algorithms.array;

class BinarySearchRecursive {

    public static int binarySearch(int[] arr, int st, int ed, int target){
        if(st>ed){
            return -1;
        }
        int md = st + (ed-st)/2;

        if(arr[md]==target)
            return md;
        else if(arr[md] > target){
            return binarySearch(arr, st, md-1, target);
        }else {
            return binarySearch(arr, md+1, ed, target);
        }
    }


    public static void main(String[] args) {
        int[] arr = {1,2,3,4,5,6,7,10};
        int target = 10;
        int index = binarySearch(arr, 0, arr.length-1, target);
        System.out.println("the index is: " + index);
    }
}
