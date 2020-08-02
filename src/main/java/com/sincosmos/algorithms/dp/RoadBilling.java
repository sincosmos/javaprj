package com.sincosmos.algorithms.dp;

/**
 * 给定一个城市的地图，所有的道路都是单行道，而且不会构成环。每条道路都有过路费，问您从S点到T点花费的最少费用。
 *           30
 *     a --------> c  ---
 *     ^ \         |      \ 20
 * 10 /     \      | 5     \/
 *   s     10 \    |       t
 *    \         \  |     /\
 *  20 \         \ |/   /
 *     \/  20     \/   / 10
 *      b ------> d --/
 *
 *  f(t) = min{f(c) + 20, f(d) + 10}
 *  f(c) = min{f(a) + 30}
 *  f(d) = min{f(b)+20, f(c) + 5, f(a) + 10}
 *  ...
 */
public class RoadBilling {
    public void roadBilling(){
        int[] fx = new int[6];
        fx[0] = 0;
        fx[1] = 10;
        fx[2] = 20;
    }
}
