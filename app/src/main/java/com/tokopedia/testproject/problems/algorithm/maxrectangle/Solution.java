package com.tokopedia.testproject.problems.algorithm.maxrectangle;


public class Solution {
    public static int maxRect(int[][] matrix) {
        // TODO, return the largest area containing 1's, given the 2D array of 0s and 1s
        // below is stub
        int temp[] = new int[matrix[0].length];
        MaximumHistogram mh = new MaximumHistogram();
        int maxArea = 0;
        int area = 0;
        for(int i=0; i < matrix.length; i++){
            for(int j=0; j < temp.length; j++){
                if(matrix[i][j] == 0){
                    temp[j] = 0;
                }else{
                    temp[j] += matrix[i][j];
                }
            }
            area = mh.maxHistogram(temp);
            if(area > maxArea){
                maxArea = area;
            }
        }
        return maxArea;
    }
}
