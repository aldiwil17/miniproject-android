package com.tokopedia.testproject.problems.algorithm.continousarea;


import com.tokopedia.testproject.problems.algorithm.maxrectangle.MaximumHistogram;

public class Solution {
    public static int maxContinuousArea(int[][] matrix) {
        // TODO, return the largest continuous area containing the same integer, given the 2D array with integers
        // below is stub
        int maxRegion = 0;
        int temp[] = new int[matrix[0].length];
        MaximumHistogram mh = new MaximumHistogram();
        for(int i = 0; i < matrix.length; i++){
            for(int j = 0; j < matrix[i].length; j++){
                temp[j] += matrix[i][j];
                int size = mh.maxHistogram(temp);
                maxRegion = Math.max(size, maxRegion);
            }
        }
        return maxRegion;
    }
}
