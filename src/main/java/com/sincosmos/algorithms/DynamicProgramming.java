package com.sincosmos.algorithms;

public class DynamicProgramming {
    public int fibonacci(int n){
        if(n <=0 ){
            return n;
        }

        int memos0=0;
        int memos1=1;
        int memosi=0;
        for(int i=2; i<=n; ++i){
            memosi = memos0 + memos1;
            memos0 = memos1;
            memos1 = memosi;
        }
        return memosi;
    }

    public int cutRod(int[] price, int n){
        if(n==0)
            return 0;
        int[] rprice = new int[n+1];
        for(int j=1; j<=n; ++j){
            int np = -1;
            for(int i=1; i<=j; ++i){
                int cp = price[i] + rprice[j-i];
                np = np>cp? np:cp;
            }
            rprice[j] = np;
        }
        return rprice[n];
    }

    public static void main(String[] args){
        DynamicProgramming dp = new DynamicProgramming();
        // fibonacci
        /*for(int i=1; i<10; ++i){
            System.out.println(i+"::"+dp.fibonacci(i));
        }*/
        //cut rod
        int[] price = {1,5,8,9,10,17,17,20,24,30};
        for(int i=1; i<=10; ++i){
            System.out.println(i + "::" + dp.cutRod(price, i));
        }
    }
}
