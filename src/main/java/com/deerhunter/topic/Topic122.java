package com.deerhunter.topic;

/**
 * 给定一个数组，它的第 i 个元素是一支给定股票第 i 天的价格。
 * <p>
 * 设计一个算法来计算你所能获取的最大利润。你可以尽可能地完成更多的交易（多次买卖一支股票）。
 * <p>
 * 注意：你不能同时参与多笔交易（你必须在再次购买前出售掉之前的股票）。
 * <p>
 *  
 * <p>
 * 示例 1:
 * <p>
 * 输入: [7,1,5,3,6,4]
 * 输出: 7
 * 解释: 在第 2 天（股票价格 = 1）的时候买入，在第 3 天（股票价格 = 5）的时候卖出, 这笔交易所能获得利润 = 5-1 = 4 。
 *      随后，在第 4 天（股票价格 = 3）的时候买入，在第 5 天（股票价格 = 6）的时候卖出, 这笔交易所能获得利润 = 6-3 = 3 。
 * 示例 2:
 * <p>
 * 输入: [1,2,3,4,5]
 * 输出: 4
 * 解释: 在第 1 天（股票价格 = 1）的时候买入，在第 5 天 （股票价格 = 5）的时候卖出, 这笔交易所能获得利润 = 5-1 = 4 。
 *      注意你不能在第 1 天和第 2 天接连购买股票，之后再将它们卖出。
 *      因为这样属于同时参与了多笔交易，你必须在再次购买前出售掉之前的股票。
 * 示例 3:
 * <p>
 * 输入: [7,6,4,3,1]
 * 输出: 0
 * 解释: 在这种情况下, 没有交易完成, 所以最大利润为 0。
 *  
 * <p>
 * 提示：
 * <p>
 * 1 <= prices.length <= 3 * 10 ^ 4
 * 0 <= prices[i] <= 10 ^ 4
 * <p>
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/best-time-to-buy-and-sell-stock-ii
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * <p>
 * Copyright (c) 20120 deerhunter0837@gmail.com All Rights Reserved.
 *
 * @author xuejunc
 * @createTime 2020/9/15 16:41
 */
public class Topic122 {
    /**
     * 暴力法
     */
    public static class Solution1 {
        private static int maxProfit;

        public static int maxProfit(int[] prices) {
            maxProfit = 0;
            dfs(prices, 0, 0, null);
            return maxProfit;
        }

        private static void dfs(int[] prices, int profit, int index, Integer inPrice) {
            if (index == prices.length) {
                maxProfit = Math.max(maxProfit, profit);
                return;
            }
            for (int i = index; i < prices.length; i++) {
                // 当前还没有买入，那么选择一个时间点买入
                if (inPrice == null) {
                    dfs(prices, profit, i + 1, prices[i]);
                    // 当前已经买入，那么选择一个时间点卖出
                } else {
                    dfs(prices, profit + prices[i] - inPrice, i + 1, null);
                }
            }
        }

    }

    public static class Solution2 {
        public static int maxProfit(int[] prices) {
            int maxProfit = 0;
            Integer inPrice = null;
            int len = prices.length - 1;
            for (int i = 0; i < len; i++) {
                boolean up = prices[i] < prices[i + 1];
                // 上涨时买入
                if (up && inPrice == null) {
                    inPrice = prices[i];
                }
                // 下跌时卖出
                if (!up && inPrice != null) {
                    maxProfit += prices[i] - inPrice;
                    inPrice = null;
                }
            }
            if (inPrice != null && prices[len] != inPrice) {
                maxProfit += prices[len] - inPrice;
            }
            return maxProfit;
        }
    }

    /**
     * 简洁的方法，参考了题解
     */
    public static class Solution3 {
        public static int maxProfit(int[] prices) {
            int maxProfit = 0;
            for (int i = 1; i < prices.length; i++) {
                if (prices[i] > prices[i - 1]) {
                    maxProfit += prices[i] - prices[i - 1];
                }
            }
            return maxProfit;
        }
    }
}
