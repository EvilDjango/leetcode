package com.deerhunter.topic;

import java.util.List;

/**
 * 给定一个三角形，找出自顶向下的最小路径和。每一步只能移动到下一行中相邻的结点上。
 * <p>
 * 相邻的结点 在这里指的是 下标 与 上一层结点下标 相同或者等于 上一层结点下标 + 1 的两个结点。
 * <p>
 *  
 * <p>
 * 例如，给定三角形：
 * <p>
 * [
 * [2],
 * [3,4],
 * [6,5,7],
 * [4,1,8,3]
 * ]
 * 自顶向下的最小路径和为 11（即，2 + 3 + 5 + 1 = 11）。
 * <p>
 *  
 * <p>
 * 说明：
 * <p>
 * 如果你可以只使用 O(n) 的额外空间（n 为三角形的总行数）来解决这个问题，那么你的算法会很加分。
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/triangle
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * <p>
 * Copyright (c) 20120 deerhunter0837@gmail.com All Rights Reserved.
 *
 * @author xuejunc
 * @createTime 2020/9/15 14:54
 */
public class Topic120 {
    public static class Solution {
        public static int minimumTotal(List<List<Integer>> triangle) {
            if (triangle.size() == 0) {
                return 0;
            }
            int[] dp = new int[triangle.size()];
            for (int i = 0; i < triangle.size(); i++) {
                List<Integer> row = triangle.get(i);
                int len = row.size();
                if (len - 1 > 0) {
                    dp[len - 1] = row.get(len - 1) + dp[len-2];
                }
                for (int j = len - 2; j > 0; j--) {
                    dp[j] = row.get(j) + Math.min(dp[j], dp[j - 1]);
                }
                dp[0] += row.get(0);
            }
            int min = dp[0];
            for (int i = 1; i < dp.length; i++) {
                if (dp[i] < min) {
                    min = dp[i];
                }
            }
            return min;
        }
    }
}
