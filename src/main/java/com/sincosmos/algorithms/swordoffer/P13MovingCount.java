package com.sincosmos.algorithms.swordoffer;

public class P13MovingCount {
    //即求 (0～m-1, 0~n-1) 坐标数位和小于等于 k 的个数。
    public int movingCount(int m, int n, int k) {
        int maxRowIndex = m-1;
        for(int i=0; i<m; ++i){
            int sum = i/10 + i%10;
            if(sum > k) {
                maxRowIndex = i-1;
                break;
            }
        }

        int maxColIndex = n-1;
        for(int i=0; i<n; ++i){
            int sum = i/10 + i%10;
            if(sum > k) {
                maxColIndex = i-1;
                break;
            }
        }

        
        return 0;
    }
}
