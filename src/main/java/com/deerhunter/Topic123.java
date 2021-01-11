package com.deerhunter;

import java.util.HashMap;
import java.util.Map;

/**
 * 给定一个数组，它的第 i 个元素是一支给定的股票在第 i 天的价格。
 * <p>
 * 设计一个算法来计算你所能获取的最大利润。你最多可以完成 两笔 交易。
 * <p>
 * 注意: 你不能同时参与多笔交易（你必须在再次购买前出售掉之前的股票）。
 * <p>
 * 示例 1:
 * <p>
 * 输入: [3,3,5,0,0,3,1,4]
 * 输出: 6
 * 解释: 在第 4 天（股票价格 = 0）的时候买入，在第 6 天（股票价格 = 3）的时候卖出，这笔交易所能获得利润 = 3-0 = 3 。
 *      随后，在第 7 天（股票价格 = 1）的时候买入，在第 8 天 （股票价格 = 4）的时候卖出，这笔交易所能获得利润 = 4-1 = 3 。
 * 示例 2:
 * <p>
 * 输入: [1,2,3,4,5]
 * 输出: 4
 * 解释: 在第 1 天（股票价格 = 1）的时候买入，在第 5 天 （股票价格 = 5）的时候卖出, 这笔交易所能获得利润 = 5-1 = 4 。  
 *      注意你不能在第 1 天和第 2 天接连购买股票，之后再将它们卖出。  
 *      因为这样属于同时参与了多笔交易，你必须在再次购买前出售掉之前的股票。
 * 示例 3:
 * <p>
 * 输入: [7,6,4,3,1]
 * 输出: 0
 * 解释: 在这个情况下, 没有交易完成, 所以最大利润为 0。
 * 通过次数56,290提交次数123,695
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/best-time-to-buy-and-sell-stock-iii
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * <p>
 * Copyright (c) 20120 deerhunter0837@gmail.com All Rights Reserved.
 *
 * @author xuejunc
 * @createTime 2020/9/16 11:44
 */
public class Topic123 {
    public static class Solution {
        public static int maxProfit(int[] prices) {
            int len = prices.length;
            if (len < 2) {
                return 0;
            }
            // leftMaxProfit[i]表示在[0,i]区间发生最多一次交易的最大收益
            int[] leftMaxProfit = new int[len];
            leftMaxProfit[0] = 0;
            int min = prices[0];
            for (int i = 1; i < len; i++) {
                leftMaxProfit[i] = Math.max(prices[i] - min, leftMaxProfit[i - 1]);
                min = Math.min(min, prices[i]);
            }
            // rightMaxProfit[i]表示在[i,len-1]区间发生最多一次交易的最大收益
            int[] rightMaxProfit = new int[len];
            rightMaxProfit[len - 1] = 0;
            int max = prices[len - 1];
            for (int i = len - 2; i >= 0; i--) {
                rightMaxProfit[i] = Math.max(max - prices[i], rightMaxProfit[i + 1]);
                max = Math.max(max, prices[i]);
            }

            int maxProfit = 0;
            // i表示第二次交易所在的时间段起点
            for (int i = 1; i < len; i++) {
                int profit = leftMaxProfit[i - 1] + rightMaxProfit[i];
                maxProfit = Math.max(maxProfit, profit);
            }
            maxProfit = Math.max(leftMaxProfit[len - 1], maxProfit);
            return maxProfit;
        }
    }

    /**
     * 动态规划,参考优秀题解 https://leetcode-cn.com/problems/best-time-to-buy-and-sell-stock-iii/solution/dong-tai-gui-hua-by-liweiwei1419-7/
     */
    public static class Solution2 {
        public static int maxProfit(int[] prices) {
            int n = prices.length;
            if (n < 2) {
                return 0;
            }
            // dp[0]表示当前第一次买入最大收益，dp[1]表示当前第一次卖出最大收益，dp[2]表示当前第二次买入最大收益，dp[3]表示当前第二次卖出最大收益
            int[] dp = new int[4];
            dp[0] = -prices[0];
            dp[2] = Integer.MIN_VALUE;
            // dp[1] dp[3]出事的最大值设置为0表示，后面如果遇到卖出会亏损的情况，可以选择继续持有

            for (int i = 1; i < n; i++) {
                dp[3] = Math.max(dp[3], prices[i] + dp[2]);
                dp[2] = Math.max(dp[2], dp[1] - prices[i]);
                dp[1] = Math.max(dp[1], prices[i] + dp[0]);
                dp[0] = Math.max(dp[0], -prices[i]);
            }
            return Math.max(dp[1], dp[3]);
        }
    }


    /**
     * 暴力算法，循环
     */
    public static class Solution3 {
        public static int maxProfit(int[] prices) {
            int max = 0;
            int len = prices.length;
            for (int i = 0; i < len; i++) {
                for (int j = i + 1; j < len; j++) {
                    // 完成一次交易的最大利润
                    max = Math.max(max, prices[j] - prices[i]);
                    for (int k = j + 1; k < len; k++) {
                        for (int l = k + 1; l < len; l++) {
                            // 完成两次交易的最大利润
                            max = Math.max(max, prices[l] - prices[k] + prices[j] - prices[i]);
                        }
                    }
                }
            }
            return max;
        }
    }

    /**
     * 记忆化递归，深度搜索
     */
    public static class Solution4 {
        public static int maxProfit(int[] prices) {
            return maxProfit(new HashMap<>(), prices, 0, 0);
        }

        private static int maxProfit(Map<String, Integer> map, int[] prices, int index, int count) {
            // 最多只允许2次交易
            if (count >= 2) {
                return 0;
            }
            String key = index + "@" + count;
            if (map.containsKey(key)) {
                return map.get(key);
            }
            int maxProfit = 0;
            for (int i = index; i < prices.length; i++) {
                for (int j = i + 1; j < prices.length; j++) {
                    maxProfit = Math.max(maxProfit, prices[j] - prices[i] + maxProfit(map, prices, j + 1, count + 1));
                }
            }
            map.put(key, maxProfit);
            return maxProfit;
        }
    }

    /**
     * 动态规划
     */
    public static class Solution5 {
        public static int maxProfit(int[] prices) {
            int len = prices.length;
            int[] dp = new int[4];
            dp[0] = -prices[0];
            dp[1] = 0;
            dp[2] = Integer.MIN_VALUE;
            dp[3] = 0;
            for (int i = 1; i < len; i++) {
                dp[3] = Math.max(dp[3], prices[i] + dp[2]);
                dp[2] = Math.max(dp[2], dp[1] - prices[i]);
                dp[1] = Math.max(dp[1], prices[i] + dp[0]);
                dp[0] = Math.max(dp[0], -prices[i]);
            }
            return Math.max(dp[3], dp[1]);
        }
    }

}
