package com.deerhunter;

import java.util.HashSet;
import java.util.Set;

/**
 * 202. Happy Number
 * Easy
 * <p>
 * 2799
 * <p>
 * 481
 * <p>
 * Add to List
 * <p>
 * Share
 * Write an algorithm to determine if a number n is happy.
 * <p>
 * A happy number is a number defined by the following process:
 * <p>
 * Starting with any positive integer, replace the number by the sum of the squares of its digits.
 * Repeat the process until the number equals 1 (where it will stay), or it loops endlessly in a cycle which does not include 1.
 * Those numbers for which this process ends in 1 are happy.
 * Return true if n is a happy number, and false if not.
 * <p>
 * <p>
 * <p>
 * Example 1:
 * <p>
 * Input: n = 19
 * Output: true
 * Explanation:
 * 12 + 92 = 82
 * 82 + 22 = 68
 * 62 + 82 = 100
 * 12 + 02 + 02 = 1
 * Example 2:
 * <p>
 * Input: n = 2
 * Output: false
 * <p>
 * Copyright (c) 20120 deerhunter0837@gmail.com All Rights Reserved.
 *
 * @author xuejunc
 * @createTime 2021/1/22 10:57
 */
public class Topic202 {
    private static int getNext(int i) {
        int res = 0;
        while (i != 0) {
            res += Math.pow((i % 10), 2);
            i /= 10;
        }
        return res;
    }

    public static class Solution1 {
        public boolean isHappy(int n) {
            Set<Integer> nums = new HashSet<>();
            while (n != 1 && !nums.contains(n)) {
                nums.add(n);
                n = getNext(n);
            }
            return n == 1;
        }
    }

    /**
     * 快慢指针法查找环
     */
    public static class Solution2 {

        public boolean isHappy(int n) {
            int slow = n;
            int fast = getNext(n);
            while (fast != 1 && slow != fast) {
                fast = getNext(getNext(fast));
                slow = getNext(slow);
            }
            return fast == 1;
        }
    }
}
