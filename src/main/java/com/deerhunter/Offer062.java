package com.deerhunter;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;
import java.util.Vector;

/**
 * 剑指 Offer 62. 圆圈中最后剩下的数字
 * 0,1,···,n-1这n个数字排成一个圆圈，从数字0开始，每次从这个圆圈里删除第m个数字（删除后从下一个数字开始计数）。求出这个圆圈里剩下的最后一个数字。
 * <p>
 * 例如，0、1、2、3、4这5个数字组成一个圆圈，从数字0开始每次删除第3个数字，则删除的前4个数字依次是2、0、4、1，因此最后剩下的数字是3。
 * <p>
 * <p>
 * <p>
 * 示例 1：
 * <p>
 * 输入: n = 5, m = 3
 * 输出: 3
 * 示例 2：
 * <p>
 * 输入: n = 10, m = 17
 * 输出: 2
 * <p>
 * <p>
 * 限制：
 * <p>
 * 1 <= n <= 10^5
 * 1 <= m <= 10^6
 * 通过次数143,117提交次数217,576
 * <p>
 * Copyright (c) @2022 deerhunter0837@gmail.com All Rights Reserved.
 *
 * @author xuejunc
 * @createTime 2022/4/21 下午12:42
 */
public class Offer062 {
    /**
     * 暴力算法，会超时
     *
     * @param n
     * @param m
     * @return
     */
    public int lastRemaining(int n, int m) {
        boolean[] deleted = new boolean[n];
        int left = n;
        int step = 0;
        for (int i = 0; left > 1; i++) {
            int index = i % n;
            if (deleted[index]) {
                continue;
            }
            step++;
            if (step == m) {
                deleted[index] = true;
                left--;
                step = 0;
            }
        }
        for (int i = 0; i < n; i++) {
            if (!deleted[i]) {
                return i;
            }
        }
        return 0;
    }

    /**
     * 数学+递归
     *
     * @param n
     * @param m
     * @return
     */
    public int lastRemaining2(int n, int m) {
        if (n == 1) {
            return 0;
        }
        return (m + lastRemaining2(n - 1, m)) % n;
    }

    /**
     * 数学+迭代
     *
     * @param n
     * @param m
     * @return
     */
    public int lastRemaining3(int n, int m) {
        int result = 0;
        for (int i = 2; i < n + 1; i++) {
            result = (m + result) % i;
        }
        return result;
    }
}
