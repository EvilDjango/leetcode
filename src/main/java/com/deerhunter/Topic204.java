package com.deerhunter;

import java.util.ArrayList;
import java.util.List;

/**
 * 204. Count Primes
 * Easy
 * <p>
 * 2661
 * <p>
 * 711
 * <p>
 * Add to List
 * <p>
 * Share
 * Count the number of prime numbers less than a non-negative number, n.
 * <p>
 * <p>
 * <p>
 * Example 1:
 * <p>
 * Input: n = 10
 * Output: 4
 * Explanation: There are 4 prime numbers less than 10, they are 2, 3, 5, 7.
 * Example 2:
 * <p>
 * Input: n = 0
 * Output: 0
 * Example 3:
 * <p>
 * Input: n = 1
 * Output: 0
 * <p>
 * <p>
 * Constraints:
 * <p>
 * 0 <= n <= 5 * 106
 * <p>
 * Copyright (c) 20120 deerhunter0837@gmail.com All Rights Reserved.
 *
 * @author xuejunc
 * @createTime 2021/1/27 11:23
 */
public class Topic204 {
    /**
     * 埃氏筛
     */
    public static class Solution1 {
        public int countPrimes(int n) {
            int count = 0;
            boolean[] isComposite = new boolean[n];
            for (int i = 2; i < n; i++) {
                if (!isComposite[i]) {
                    count++;
                    if ((long) i * i < n) {
                        for (int j = i * i; j < n; j += i) {
                            isComposite[j] = true;
                        }
                    }
                }
            }
            return count;
        }
    }

    /**
     * 线性筛
     */
    public static class Solution2 {
        public int countPrimes(int n) {
            List<Integer> primes = new ArrayList<>();
            boolean[] isComposite = new boolean[n];
            for (int i = 2; i < n; i++) {
                if (!isComposite[i]) {
                    primes.add(i);
                }
                for (int j = 0; j < primes.size() && (long) i * primes.get(j) < n; j++) {
                    int prime = primes.get(j);
                    isComposite[i * prime] = true;
                    if (i % prime == 0) {
                        break;
                    }
                }
            }
            return primes.size();
        }
    }
}
