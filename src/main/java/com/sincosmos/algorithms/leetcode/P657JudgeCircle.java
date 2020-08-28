package com.sincosmos.algorithms.leetcode;

public class P657JudgeCircle {
    public boolean judgeCircle(String moves) {
        if(moves==null) return false;
        int cntV=0, cntH=0;
        for(int i=0; i<moves.length(); ++i){
            if(moves.charAt(i) == 'L') cntV++;
            else if(moves.charAt(i) == 'R') cntV--;
            else if(moves.charAt(i) == 'U') cntH++;
            else if(moves.charAt(i) == 'D') cntH--;
        }
        return cntV == 0 && cntH == 0;
    }
}
