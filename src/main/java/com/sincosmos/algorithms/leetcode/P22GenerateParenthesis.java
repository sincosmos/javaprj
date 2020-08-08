package com.sincosmos.algorithms.leetcode;

import java.util.ArrayList;
import java.util.List;

public class P22GenerateParenthesis {
    public List<String> generateParenthesis(int n) {
        List<List<String>> dp = new ArrayList<>();
        List<String> dp0 = new ArrayList<>();
        dp0.add("");
        dp.add(0, dp0);
        List<String> dp1 = new ArrayList<>();
        dp1.add("()");
        dp.add(1, dp1);

        int inside, right;
        for(int i=2; i<=n; ++i){
            List<String> dpi  = new ArrayList<>();
            for(inside = 0; inside < i;  inside++){
                right = i - 1 - inside;
                for(String sinside: dp.get(inside)){
                    for(String sright: dp.get(right)){
                        dpi.add("(" + sinside + ")" + sright);
                    }
                }
            }
            dp.add(i, dpi);
        }

        return dp.get(n);
    }
}
