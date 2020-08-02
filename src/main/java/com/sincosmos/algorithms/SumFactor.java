package com.sincosmos.algorithms;

import java.util.*;

/**
 * 输出一个数字所有的加法分解组合
 * 比如 3 可以分解为1+1+1, 1+2，就输出 [1,1,1],[1,2]
 */
public class SumFactor {
    public Set<List<Integer>> sumFactor(int n){
        int k = (int) Math.round(Math.ceil(n/2.0));
        Set<List<Integer>> fn = new HashSet<>();
        for(int i=1; i<=k; ++i){

        }
        return null;
    }

    public static void main(String[] args){
        SumFactor sf = new SumFactor();
        Set<List<Integer>> sfs = sf.sumFactor(4);
        sfs.forEach(x->{
            StringBuilder sb = new StringBuilder();
            x.forEach(y->sb.append(y + " "));
            System.out.println(sb.toString());
        });
    }
}
