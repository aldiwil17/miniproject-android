package com.tokopedia.testproject.problems.algorithm.waterJug;

import java.util.Collections;

public class Solution {

    public static int minimalPourWaterJug(int jug1, int jug2, int target) {
        // TODO, return the smallest number of POUR action to do the water jug problem
        // below is stub, replace with your implementation!

        int from = jug1;
        int to = 0;
        int temp = 0;

        int step = 1;

        while (from != target && to != target)
        {
            temp = Math.min(from, jug2 - to);

            to   += temp;
            from -= temp;

            step++;

            if (from == target || to == target)
                break;

            if (from == 0)
            {
                from = jug1;
                step++;
            }

            if (to == jug2)
            {
                to = 0;
                step++;
            }
        }
        return temp;
    }


}
