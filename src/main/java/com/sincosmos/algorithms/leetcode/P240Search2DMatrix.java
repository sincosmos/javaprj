package com.sincosmos.algorithms.leetcode;

/**
 * Write an efficient algorithm that searches for a value in an m x n matrix. This matrix has the following properties:
 *
 * Integers in each row are sorted in ascending from left to right.
 * Integers in each column are sorted in ascending from top to bottom.
 * Example:
 *
 * Consider the following matrix:
 *
 * [
 *   [1,   4,  7, 11, 15],
 *   [2,   5,  8, 12, 19],
 *   [3,   6,  9, 16, 22],
 *   [10, 13, 14, 17, 24],
 *   [18, 21, 23, 26, 30]
 * ]
 * Given target = 5, return true.
 *
 * Given target = 20, return false.
 */
public class P240Search2DMatrix {

    public boolean searchMatrix(int[][] matrix, int target) {
        if(matrix == null || matrix.length == 0 || matrix[0] == null) return false;
        int columns = matrix[0].length;
        int rows = matrix.length;

        int i=0, j = columns - 1;


        while(i<rows && j>=0){
            if(matrix[i][j] == target) return true;
            if(matrix[i][j] < target){
                ++i;
            }else{
                --j;
            }
        }

        return false;
    }

    public boolean searchMatrix2(int[][] matrix, int target) {
        if(matrix == null || matrix.length == 0 || matrix[0] == null) return false;
        int rows = matrix.length;
        int ed = matrix[0].length - 1;

        for(int i=0; i<rows; ++i){

            if(target >= matrix[i][0] && target <= matrix[i][ed]){
                if( binarySearch(matrix[i], target) ) return true;
            }
        }
        return false;
    }

    private boolean binarySearch(int[] matrix, int target) {
        int ed = matrix.length - 1, st = 0, mid;
        while(st <= ed ){
            mid = st + (ed - st) / 2;
            if(matrix[mid] == target) return true;
            if(matrix[mid] > target){
                ed = mid - 1;
            }else{
                st = mid + 1;
            }
        }
        return false;
    }
}
