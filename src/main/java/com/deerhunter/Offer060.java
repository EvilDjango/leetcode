package com.deerhunter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 剑指 Offer 60. n个骰子的点数
 * 把n个骰子扔在地上，所有骰子朝上一面的点数之和为s。输入n，打印出s的所有可能的值出现的概率。
 * <p>
 * <p>
 * <p>
 * 你需要用一个浮点数数组返回答案，其中第 i 个元素代表这 n 个骰子所能掷出的点数集合中第 i 小的那个的概率。
 * <p>
 * <p>
 * <p>
 * 示例 1:
 * <p>
 * 输入: 1
 * 输出: [0.16667,0.16667,0.16667,0.16667,0.16667,0.16667]
 * 示例 2:
 * <p>
 * 输入: 2
 * 输出: [0.02778,0.05556,0.08333,0.11111,0.13889,0.16667,0.13889,0.11111,0.08333,0.05556,0.02778]
 * <p>
 * <p>
 * 限制：
 * <p>
 * 1 <= n <= 11
 * <p>
 * 通过次数88,642提交次数154,980
 * <p>
 * Copyright (c) @2022 deerhunter0837@gmail.com All Rights Reserved.
 *
 * @author xuejunc
 * @createTime 2022/4/21 下午7:32
 */
public class Offer060 {

    /**
     * 动态规划
     *
     * @param n
     * @return
     */
    public double[] dicesProbability(int n) {
        // counts[i]表示总和为i+1的情况出现的次数
        int[] counts = new int[]{1, 1, 1, 1, 1, 1};
        for (int i = 1; i < n; i++) {
            int[] newCounts = new int[counts.length + 6];
            for (int j = 0; j < counts.length; j++) {
                for (int k = 1; k < 7; k++) {
                    newCounts[j + k] += counts[j];
                }
            }
            counts = newCounts;
        }
        double[] ans = new double[counts.length - n + 1];
        double total = Math.pow(6, n);
        for (int i = n - 1; i < counts.length; i++) {
            ans[i - n + 1] = counts[i] / total;
        }
        return ans;
    }

    /**
     * 参考优秀题解
     * 这里因为忽略了每个位置具体代表的点数总和而优化了效率
     *
     * @param n
     * @return
     */
    public double[] dicesProbability2(int n) {
        double[] dp = new double[6];
        Arrays.fill(dp, 1.0 / 6.0);
        for (int i = 2; i <= n; i++) {
            double[] temp = new double[5 * i + 1];
            for (int j = 0; j < dp.length; j++) {
                for (int k = 0; k < 6; k++) {
                    temp[j + k] += dp[j] / 6;
                }
            }
            dp = temp;
        }
        return dp;
    }
}
