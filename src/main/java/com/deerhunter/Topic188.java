package com.deerhunter;

import java.util.ArrayList;
import java.util.List;

/**
 * 188. Best Time to Buy and Sell Stock IV
 * Hard
 * <p>
 * 2129
 * <p>
 * 134
 * <p>
 * Add to List
 * <p>
 * Share
 * You are given an integer array prices where prices[i] is the price of a given stock on the ith day.
 * <p>
 * Design an algorithm to find the maximum profit. You may complete at most k transactions.
 * <p>
 * Notice that you may not engage in multiple transactions simultaneously (i.e., you must sell the stock before you buy again).
 * <p>
 * <p>
 * <p>
 * Example 1:
 * <p>
 * Input: k = 2, prices = [2,4,1]
 * Output: 2
 * Explanation: Buy on day 1 (price = 2) and sell on day 2 (price = 4), profit = 4-2 = 2.
 * Example 2:
 * <p>
 * Input: k = 2, prices = [3,2,6,5,0,3]
 * Output: 7
 * Explanation: Buy on day 2 (price = 2) and sell on day 3 (price = 6), profit = 6-2 = 4. Then buy on day 5 (price = 0) and sell on day 6 (price = 3), profit = 3-0 = 3.
 * <p>
 * <p>
 * Constraints:
 * <p>
 * 0 <= k <= 100
 * 0 <= prices.length <= 1000
 * 0 <= prices[i] <= 1000
 * <p>
 * Copyright (c) 20120 deerhunter0837@gmail.com All Rights Reserved.
 *
 * @author xuejunc
 * @createTime 2021/1/4 14:57
 */
public class Topic188 {
    /**
     * 先计算得到所有的可以赚到的差价，个数记为n。若k>=n，则可以赚到最大利润。若k<n,则需要进行n-k次操作：依次尝试将相邻的差价区间合并，取总利润减少最小的一种合并方案。
     */
    public static class Solution1 {
        public int maxProfit(int k, int[] prices) {
            int len = prices.length;
            if (len < 2) {
                return 0;
            }
            List<Integer> buyPrices = new ArrayList<>();
            List<Integer> profits = new ArrayList<>();
            int price = -1;
            for (int i = 0; i < len - 1; i++) {
                if (price == -1 && prices[i + 1] > prices[i]) {
                    price = prices[i];
                    buyPrices.add(price);
                } else if (price != -1 && prices[i + 1] < prices[i]) {
                    profits.add(prices[i] - price);
                    price = -1;
                }
            }
            if (price != -1) {
                profits.add(prices[len - 1] - price);
            }
            int toRemove = profits.size() - k;
            for (int i = 0; i < toRemove; i++) {
                int indexToRemove = -1;
                int add = 0;
                int minLoss = Integer.MAX_VALUE;
                for (int j = 0; j < profits.size(); j++) {
                    int curLoss = profits.get(j);
                    int curAdd = 0;
                    if (j + 1 < buyPrices.size() && buyPrices.get(j + 1) > buyPrices.get(j)) {
                        curLoss -= buyPrices.get(j + 1) - buyPrices.get(j);
                        curAdd = buyPrices.get(j + 1) - buyPrices.get(j);
                    }
                    if (curLoss < minLoss) {
                        minLoss = curLoss;
                        add = curAdd;
                        indexToRemove = j;
                    }
                }
                profits.remove(indexToRemove);
                buyPrices.remove(indexToRemove);
                if (add > 0) {
                    profits.set(indexToRemove, profits.get(indexToRemove) + add);
                    buyPrices.set(indexToRemove, buyPrices.get(indexToRemove) - add);
                }
            }
            int maxProfit = 0;
            for (int profit : profits) {
                maxProfit += profit;
            }
            return maxProfit;
        }
    }

    /**
     * 动态规划
     */
    public static class Solution2 {
        public int maxProfit(int k, int[] prices) {
            int n = prices.length;
            if (k < 1 || n < 2) {
                return 0;
            }

            if (k * 2 >= prices.length) {
                int res = 0;
                for (int i = 1; i < n; i++) {
                    res += Math.max(0, prices[i] - prices[i - 1]);
                }
                return res;
            }

            int[] dp = new int[2 * k];
            dp[0] = -prices[0];
            for (int i = 1; i < k; i++) {
                dp[2 * i] = Integer.MIN_VALUE;
            }
            for (int i = 1; i < n; i++) {
                for (int j = k - 1; j >= 0; j--) {
                    dp[2 * j + 1] = Math.max(dp[2 * j + 1], dp[2 * j] + prices[i]);
                    dp[2 * j] = Math.max(dp[2 * j], (j == 0 ? 0 : dp[2 * j - 1]) - prices[i]);
                }
            }
            int maxProfit = 0;
            for (int i = 0; i < k; i++) {
                maxProfit = Math.max(maxProfit, dp[2 * i + 1]);
            }
            return maxProfit;
        }
    }
}
