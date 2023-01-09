package com.leetcode.easy;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class DeleteColumnsMakeSorted {
    /**
     * 944. Delete Columns to Make Sorted
     *
     * You are given an array of n strings strs, all of the same length.
     *
     * The strings can be arranged such that there is one on each line, making a
     * grid. For example, strs = ["abc", "bce", "cae"] can be arranged as:
     *
     * abc
     * bce
     * cae
     *
     * You want to delete the columns that are not sorted lexicographically. In the
     * above example (0-indexed), columns 0 ('a', 'b', 'c') and 2 ('c', 'e', 'e')
     * are sorted while column 1 ('b', 'c', 'a') is not, so you would delete column 1.
     *
     * Return the number of columns that you will delete.
     */

    public static void main(String[] args) {
        String[] case1 = {"cba","daf","ghi"};
        String[] case2 = {"a","b"};
        String[] case3 = {"zyx","wvu","tsr"};


        minDeletionSizeBrute(case1);
        minDeletionSizeOptimized(case1);

    }

    private static int minDeletionSizeBrute(String[] strs) {
        int count = 0;
        for(int i = 0; i < strs[0].length(); i++) {
            List<Character> chars = new ArrayList<>();
            for(int j = 0; j < strs.length; j++) {
                chars.add(strs[j].charAt(i));
            }
            List<Character> sortedChars = new ArrayList<>(chars);
                Collections.sort(sortedChars);
                if(!chars.equals(sortedChars))
                    count++;
        }
        return count;
    }

    private static int minDeletionSizeOptimized(String[] strs) {
        int count = 0;
        for(int i = 0; i < strs[0].length(); i++) {
            for(int j = 1; j < strs.length; j++) {
                if(strs[j].charAt(i) < strs[j-1].charAt(i)) {
                    count++;
                    break;
                }
            }
        }
        return count;
    }


}
