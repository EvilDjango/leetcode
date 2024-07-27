package com.deerhunter.topic;

import java.util.Arrays;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;

/**
 * 179. Largest Number
 * Medium
 * <p>
 * 2660
 * <p>
 * 287
 * <p>
 * Add to List
 * <p>
 * Share
 * Given a list of non-negative integers nums, arrange them such that they form the largest number.
 * <p>
 * Note: The result may be very large, so you need to return a string instead of an integer.
 * <p>
 * <p>
 * <p>
 * Example 1:
 * <p>
 * Input: nums = [10,2]
 * Output: "210"
 * Example 2:
 * <p>
 * Input: nums = [3,30,34,5,9]
 * Output: "9534330"
 * Example 3:
 * <p>
 * Input: nums = [1]
 * Output: "1"
 * Example 4:
 * <p>
 * Input: nums = [10]
 * Output: "10"
 * <p>
 * <p>
 * Constraints:
 * <p>
 * 1 <= nums.length <= 100
 * 0 <= nums[i] <= 109
 * <p>
 * Copyright (c) 20120 deerhunter0837@gmail.com All Rights Reserved.
 *
 * @author xuejunc
 * @createTime 2021/1/1 19:43
 */
public class Topic179 {
    public static class Solution1 {
        public String largestNumber(int[] nums) {
            int n = nums.length;
            Integer[] integers = new Integer[nums.length];
            for (int i = 0; i < n; i++) {
                integers[i] = nums[i];
            }

            Arrays.sort(integers, (o1, o2) -> concate(o1, o2) > concate(o2, o1) ? -1 : 1);
            if (integers[0] == 0) {
                return "0";
            }
            StringBuilder sb = new StringBuilder();
            for (int i : integers) {
                sb.append(i);
            }
            return sb.toString();
        }

        private long concate(long a, long b) {
            long tmp = b;
            do {
                a *= 10;
                tmp /= 10;
            } while (tmp > 0);
            return a + b;
        }
    }
}
