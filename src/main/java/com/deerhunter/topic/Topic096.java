package com.deerhunter.topic;

import java.util.Arrays;

/**
 * 给定一个整数 n，求以 1 ... n 为节点组成的二叉搜索树有多少种？
 * <p>
 * 示例:
 * <p>
 * 输入: 3
 * 输出: 5
 * 解释:
 * 给定 n = 3, 一共有 5 种不同结构的二叉搜索树:
 * <p>
 * 1         3     3      2      1
 * \       /     /      / \      \
 * 3     2     1      1   3      2
 * /     /       \                 \
 * 2     1         2                 3
 * 通过次数83,532提交次数120,874
 * <p>
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/unique-binary-search-trees
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * <p>
 * Copyright (c) 20120 deerhunter0837@gmail.com All Rights Reserved.
 *
 * @author xuejunc
 * @createTime 2020/9/5 17:21
 */
public class Topic096 {
    /**
     * 带缓存的递归
     */
    static class Solution1 {
        public static int numTrees(int n) {
            int[][] record = new int[n][n];
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    record[i][j] = -1;
                }
            }
            return numTrees(0, n - 1, record);
        }

        private static int numTrees(int left, int right, int[][] record) {
            if (right < 0 || left == record.length) {
                return 1;
            }
            if (record[left][right] != -1) {
                return record[left][right];
            }
            if (left > right) {
                // 空树也是一种情况，所以树的数量至少为1
                record[left][right] = 1;
                return 1;
            }
            int treeNum = 0;
            for (int i = left; i <= right; i++) {
                int leftTreeNum = numTrees(left, i - 1, record);
                int rightTreeNum = numTrees(i + 1, right, record);
                treeNum += leftTreeNum * rightTreeNum;
            }
            record[left][right] = treeNum;
            return treeNum;
        }
    }

    static class Solution2 {
        public static int numTrees(int n) {
            if (n == 0) {
                return 0;
            }
            int[] dp = new int[n + 1];
            dp[0] = 1;
            dp[1] = 1;
            for (int i = 1; i < n; i++) {
                int min = Math.min(i, n - 1 - i);
                for (int j = 0; j <= min; j++) {
                    dp[i + j + 1] += i == j ? dp[i] * dp[j] : dp[i] * dp[j] * 2;
                }
            }
            return dp[n];
        }
    }

    /**
     * 参考官方题解，本质上与解法二是一样的
     */
    static class Solution3 {
        public static int numTrees(int n) {
            if (n == 0) {
                return 0;
            }
            int[] dp = new int[n + 1];
            dp[0] = 1;
            dp[1] = 1;
            for (int i = 2; i <= n; i++) {
                for (int j = 1; j <= i; j++) {
                    dp[i] += dp[j - 1] * dp[i - j];
                }
            }
            return dp[n];
        }
    }
}
