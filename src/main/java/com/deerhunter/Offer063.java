package com.deerhunter;

/**
 * 剑指 Offer 63. 股票的最大利润
 * 假设把某股票的价格按照时间先后顺序存储在数组中，请问买卖该股票一次可能获得的最大利润是多少？
 * <p>
 * <p>
 * <p>
 * 示例 1:
 * <p>
 * 输入: [7,1,5,3,6,4]
 * 输出: 5
 * 解释: 在第 2 天（股票价格 = 1）的时候买入，在第 5 天（股票价格 = 6）的时候卖出，最大利润 = 6-1 = 5 。
 * 注意利润不能是 7-1 = 6, 因为卖出价格需要大于买入价格。
 * 示例 2:
 * <p>
 * 输入: [7,6,4,3,1]
 * 输出: 0
 * 解释: 在这种情况下, 没有交易完成, 所以最大利润为 0。
 * <p>
 * <p>
 * 限制：
 * <p>
 * 0 <= 数组长度 <= 10^5
 * <p>
 * <p>
 * <p>
 * 注意：本题与主站 121 题相同：https://leetcode-cn.com/problems/best-time-to-buy-and-sell-stock/
 * <p>
 * 通过次数191,503提交次数301,579
 * <p>
 * Copyright (c) @2022 deerhunter0837@gmail.com All Rights Reserved.
 *
 * @author xuejunc
 * @createTime 2022/4/21 下午12:07
 */
public class Offer063 {
    /**
     * 后缀树组
     *
     * @param prices
     * @return
     */
    public int maxProfit(int[] prices) {
        int n = prices.length;
        int[] suffix = new int[n];
        int max = Integer.MIN_VALUE;
        for (int i = n - 1; i >= 0; i--) {
            if (prices[i] > max) {
                max = prices[i];
            }
            suffix[i] = max;
        }
        int min = Integer.MAX_VALUE;
        int maxProfit = 0;
        for (int i = 0; i < n; i++) {
            if (prices[i] < min) {
                min = prices[i];
            }
            if (suffix[i] - min > maxProfit) {
                maxProfit = suffix[i] - min;
            }
        }
        return maxProfit;
    }

    /**
     * 最优解，维持一个历史最低点，一次遍历。参考官方题解。
     *
     * @param prices
     * @return
     */
    public int maxProfit2(int[] prices) {
        int min = Integer.MAX_VALUE;
        int maxProfit = 0;
        for (int price : prices) {
            if(price<min){
                min=price;
            }
            if(price-min>maxProfit){
                maxProfit=price-min;
            }
        }
        return maxProfit;
    }
}
