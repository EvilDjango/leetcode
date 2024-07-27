package com.deerhunter.offer;

import java.util.ArrayList;
import java.util.List;

/**
 * 剑指 Offer 57 - II. 和为s的连续正数序列
 * 输入一个正整数 target ，输出所有和为 target 的连续正整数序列（至少含有两个数）。
 * <p>
 * 序列内的数字由小到大排列，不同序列按照首个数字从小到大排列。
 * <p>
 * <p>
 * <p>
 * 示例 1：
 * <p>
 * 输入：target = 9
 * 输出：[[2,3,4],[4,5]]
 * 示例 2：
 * <p>
 * 输入：target = 15
 * 输出：[[1,2,3,4,5],[4,5,6],[7,8]]
 * <p>
 * <p>
 * 限制：
 * <p>
 * 1 <= target <= 10^5
 * <p>
 * <p>
 * 通过次数181,446提交次数255,377
 * <p>
 * Copyright (c) @2022 deerhunter0837@gmail.com All Rights Reserved.
 *
 * @author xuejunc
 * @createTime 2022/4/24 下午9:10
 */
public class Offer057B {
    /**
     * 记序列起始值为start，长度为size，那么start=target/size-(size-1)/2
     * 从2开始枚举size，当start<=0时终止循环，因为题目要求的是正整数序列。
     *
     * @param target
     * @return
     */
    public int[][] findContinuousSequence(int target) {
        List<int[]> list = new ArrayList<>();
        int size = 2;
        while (true) {
            int start = target / size - ((size - 1) >> 1);
            if (start <= 0) {
                break;
            }
            if (size * (2 * start + size - 1) >> 1 != target) {
                size++;
                continue;
            }
            int[] seq = new int[size];
            for (int i = 0; i < size; i++) {
                seq[i] = start + i;
            }
            list.add(seq);
            size++;
        }
        int n = list.size();
        int[][] ans = new int[n][];
        for (int i = 0; i < n; i++) {
            ans[i] = list.get(n - i - 1);
        }
        return ans;
    }

    /**
     * 双指针法，参考了官方题解
     *
     * @param target
     * @return
     */
    public int[][] findContinuousSequence2(int target) {
        List<int[]> list = new ArrayList<>();
        int left = 1, right = 2;
        int sum = left + right;
        while (left < right) {
            if (sum < target) {
                right++;
                sum += right;
            } else if (sum > target) {
                sum -= left;
                left++;
            } else {
                int[] seq = new int[right - left + 1];
                for (int i = 0; i < right - left + 1; i++) {
                    seq[i] = left + i;
                }
                list.add(seq);
                sum -= left;
                left++;
            }
        }
        return list.toArray(new int[list.size()][]);
    }
}
