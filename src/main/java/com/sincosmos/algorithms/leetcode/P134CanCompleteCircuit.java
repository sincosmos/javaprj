package com.sincosmos.algorithms.leetcode;

public class P134CanCompleteCircuit {
    public int canCompleteCircuit(int[] gas, int[] cost) {
        //从每个加油站向左出发是否能回到出发点
        boolean[] dpLeft = new boolean[gas.length];
        //从每个加油站向右出发是否能回到出发点
        boolean[] dpRight = new boolean[gas.length];

        for(int i=0; i<gas.length; ++i){
            if(dfs(i-1, 0,0, gas, cost)) return i;
            if(dfs(i+1, 0,1, gas, cost)) return i;
        }

        return -1;
    }

    private boolean dfs(int cur, int remainingGas, int direction, int[] gas, int[] cost){
        int pre=0;

        //go left
        if(direction == 0){
            pre = cur + 1;
        }
        if(cur == -1 && direction == 0) {
            pre = 0;
            cur = gas.length-1;
        }
        //go right
        if(direction == 1){
            pre = cur - 1;
        }
        if(cur == gas.length && direction == 1) {
            pre=gas.length-1;
            cur = 0;
        }

        remainingGas = gas[pre] + remainingGas - gas[cur];
        if(remainingGas<0) return false;

        if(direction == 0){
            return dfs(cur-1, remainingGas, direction, gas, cost);
        }else{
            return dfs(cur+1, remainingGas, direction, gas, cost);
        }
    }
}
