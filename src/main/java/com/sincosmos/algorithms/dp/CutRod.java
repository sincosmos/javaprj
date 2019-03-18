package com.sincosmos.algorithms.dp;

public class CutRod {
    public int cutRod(int[] price, int n){
        if(n==0)
            return 0;
        int[] maxPrice = new int[n+1];
        for(int j=1; j<=n; ++j){
            int np = -1;
            for(int i=1; i<=j; ++i){
                int cp = price[i] + maxPrice[j-i];
                np = np>cp? np:cp;
            }
            maxPrice[j] = np;
        }
        return maxPrice[n];
    }
}
