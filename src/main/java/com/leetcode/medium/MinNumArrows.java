package com.leetcode.medium;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MinNumArrows {
    /*
     * 452. Minimum Number of Arrows to Burst Balloons
     *
     * There are some spherical balloons taped onto a flat wall that represents the
     * XY-plane. The balloons are represented as a 2D integer array points where
     * points[i] = [xstart, xend] denotes a balloon whose horizontal diameter
     * stretches between xstart and xend. You do not know the exact y-coordinates of
     * the balloons.
     *
     * Arrows can be shot up directly vertically (in the positive y-direction) from
     * different points along the x-axis. A balloon with xstart and xend is burst by
     * an arrow shot at x if xstart <= x <= xend. There is no limit to the number of
     * arrows that can be shot. A shot arrow keeps traveling up infinitely, bursting
     * any balloons in its path.
     *
     * Given the array points, return the minimum number of arrows that must be shot
     * to burst all balloons.
     * =============================================================================
     * Example 1:
     *
     * Input: points = [[10,16],[2,8],[1,6],[7,12]]
     * Output: 2
     * Explanation: The balloons can be burst by 2 arrows:
     * - Shoot an arrow at x = 6, bursting the balloons [2,8] and [1,6].
     * - Shoot an arrow at x = 11, bursting the balloons [10,16] and [7,12].
     */

    public static void main(String[] args) {
        int[][] case1 = { { 10, 16 }, { 2, 8 }, { 1, 6 }, { 7, 12 } };
        int[][] case2 = { { 1,2 }, { 3,4 }, { 5, 6 }, { 7, 8 } };
        int[][] case3 = { { 1,2 }, { 2,3 }, { 3, 4 }, { 4, 5 } };
        int[][] case4 = { { 1,10 }, { 3,5 },  { 7,9 }};


        int[][] spCase = {{3,9},{7,12},{3,8},{6,8},{9,10},{2,9},{0,9},{3,9},{0,6},{2,8}};


        int arrows = minArrowShotsSolution(spCase);
        System.out.println("min arrows: " + arrows);
    }

    public static int minArrowShotsBrute(int[][] points){
        List<Integer> popped = new ArrayList<>();
        Map<Integer, List<Integer>> arrowIndexMap = new HashMap<>();

        for (int i = 0; i < points.length; i++) {
            // ignore if already popped
            if(popped.contains(i))
                    continue;
            else
                addTOMap(arrowIndexMap, i, i);
            List<Integer> baloons = arrowIndexMap.get(i);
            int[] p1 = points[i];
            for (int j = 1; j < points.length; j++) {
                int[] p2 = points[j];
                boolean overlaps =  doTheyOverlap(p1,p2);// condition to check if 2 points overlap
                if(!popped.contains(j) && overlaps) {
                    // check if it also overlaps with other i overlapping baloons

                    if(baloons == null){}
                    popped.add(j);
                    addTOMap(arrowIndexMap, i, j);
                }
            }
        }
        return arrowIndexMap.keySet().size();
    }

    private static void addTOMap(Map<Integer, List<Integer>> arrowIndexMap,Integer key,  Integer val){
        List<Integer> baloons = arrowIndexMap.get(key);
        if(baloons == null)
            arrowIndexMap.put(key, new ArrayList<>(Arrays.asList(val)));
        else {
            baloons.add(val);
            arrowIndexMap.put(key, baloons);
        }
    }

    private static boolean doTheyOverlap(int[] p1, int[] p2){
        return p1[0] <= p2[1] && p1[1] >= p2[0];
    }

    private static int minArrowShotsSolution(int[][] points) {
        // sort based on first or last points
        Arrays.sort(points, (a,b) -> Integer.compare(a[1], b[1]));

        int arrows = 1;
        int previous = 0;

        for (int current = 1; current < points.length; current++) {

            // if current point starts after prev ends ==> we need new arrow.
            // we keep counting how many baloons we can burst with the single arrow
            if(points[current][0] > points[previous][1]) {
                arrows++;
                previous = current;
            }
        }
        return arrows;
    }

}
