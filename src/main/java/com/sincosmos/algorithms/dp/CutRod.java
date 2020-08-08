package com.sincosmos.algorithms.dp;

public class CutRod {
    int[] price = new int[]{1,3,5,7};

    public int cutRod(int n){
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


    public int cutRod2(int n){
        if(n==0) return 0;
        int[] maxPrice = new int[n+1];
        for(int j=1; j<=n; ++j){
            int max = -1;
            for(int i=1; i < price.length && i<=j; ++i){
                int cp = price[i] + maxPrice[j-i];
                max = Math.max(max, cp);
            }
            maxPrice[j] = max;
        }
        return maxPrice[n];
    }

    public int cutRod3(int n){
        if(n==0) return 0;
        int max = -1;
        for(int i=1; i<=n; ++i){
            max = Math.max(max, price[i-1] + cutRod3(n-i));
        }
        return max;
    }

    public static void main(String[] args){
        CutRod cutRod = new CutRod();

        System.out.print(cutRod.cutRod2(7));

        System.out.print(cutRod.cutRod3(7));
    }
}
