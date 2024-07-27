package com.deerhunter.offer;

/**
 * 剑指 Offer 46. 把数字翻译成字符串
 * 给定一个数字，我们按照如下规则把它翻译为字符串：0 翻译成 “a” ，1 翻译成 “b”，……，11 翻译成 “l”，……，25 翻译成 “z”。一个数字可能有多个翻译。请编程实现一个函数，用来计算一个数字有多少种不同的翻译方法。
 * <p>
 * <p>
 * <p>
 * 示例 1:
 * <p>
 * 输入: 12258
 * 输出: 5
 * 解释: 12258有5种不同的翻译，分别是"bccfi", "bwfi", "bczi", "mcfi"和"mzi"
 * <p>
 * <p>
 * 提示：
 * <p>
 * 0 <= num < 231
 * 通过次数177,539提交次数339,286
 * <p>
 * Copyright (c) @2022 deerhunter0837@gmail.com All Rights Reserved.
 *
 * @author xuejunc
 * @createTime 2022/4/30 下午5:09
 */
public class Offer046 {
    /**
     * 动态规划
     *
     * @param num
     * @return
     */
    public int translateNum(int num) {
        String s = String.valueOf(num);
        int n = s.length();
        int[] dp = new int[n + 1];
        dp[0] = 1;
        for (int i = 0; i < n; i++) {
            dp[i + 1] += dp[i];
            if (i > 0) {
                String sub = s.substring(i - 1, i + 1);
                if (sub.compareTo("09") > 0 && sub.compareTo("26") < 0) {
                    dp[i + 1] += dp[i - 1];
                }
            }
        }
        return dp[n];
    }

    /**
     * 动态规划，滚动变量
     *
     * @param num
     * @return
     */
    public int translateNum2(int num) {
        String s = String.valueOf(num);
        int n = s.length();
        // p,q,r分别表示截止前两个位置，前一个位置，当前位置的方案数
        int p = 0, q = 0, r = 1;
        for (int i = 0; i < n; i++) {
            p = q;
            q = r;
            r = 0;
            r += q;
            if (i == 0) {
                continue;
            }
            String sub = s.substring(i - 1, i + 1);
            if (sub.compareTo("09") > 0 && sub.compareTo("26") < 0) {
                r += p;
            }
        }
        return r;
    }

}
