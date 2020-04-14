package com.deerhunter;

/**
 * 一条包含字母 A-Z 的消息通过以下方式进行了编码：
 * <p>
 * 'A' -> 1
 * 'B' -> 2
 * ...
 * 'Z' -> 26
 * 给定一个只包含数字的非空字符串，请计算解码方法的总数。
 * <p>
 * 示例 1:
 * <p>
 * 输入: "12"
 * 输出: 2
 * 解释: 它可以解码为 "AB"（1 2）或者 "L"（12）。
 * 示例 2:
 * <p>
 * 输入: "226"
 * 输出: 3
 * 解释: 它可以解码为 "BZ" (2 26), "VF" (22 6), 或者 "BBF" (2 2 6) 。
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/decode-ways
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * <p>
 * Copyright (c) 2019, deerhunter0837@gmail.com All Rights Reserved.
 *
 * @author xuejunc
 * @createTime 2020/4/13 18:04
 */
public class Topic091 {
    public static class Solution1 {
        public int numDecodings(String s) {
            if (s.length() == 0) {
                return 0;
            }

            int[] dp = new int[s.length()];
            int lastNum = 0;
            // 前面紧挨着一个2位配对
            boolean lastTwoBit = false;
            for (int i = 0; i < s.length(); i++) {
                int num = s.charAt(i) - '0';

                dp[i] = i == 0 ? 1 : dp[i - 1];

                int twoBit = lastNum * 10 + num;
                // 与前一个数字成功配对的情况
                if (twoBit > 9 && twoBit < 27) {

                    if (num == 0) {
                        // 上一个数字必须和当前数字组合，所以之前的配对必须拆开
                        if (lastTwoBit) {
                            dp[i] = dp[i - 2];
                        } else {
                            // do nothing
                            // 若前面没有配对，因为当前数字为0，无法单独被解析，总数不会增加
                        }

                    } else {
                        // 如果前面已经有一个配对了，则当前配对和上一个配对不能同时取
                        if (lastTwoBit) {
                            dp[i] = dp[i - 1] + dp[i - 2];
                        } else {
                            dp[i] = dp[i - 1] << 1;
                        }
                    }

                    lastTwoBit = true;

                    // 当前数字为0，并且无法与前面的数字组合成一个可解析数字，则无解。
                } else if (num == 0) {
                    return 0;
                }

                lastNum = num;
            }
            return dp[s.length() - 1];
        }
    }

    /**
     * 比解法1更简洁
     */
    public static class Solution2 {
        public int numDecodings(String s) {
            if (s.length() == 0) {
                return 0;
            }
            // 前两个位置的解码方法数
            int pp = 1;
            // 前一个位置的解法方法数
            int pre = 1;
            // 当前的解法方法数
            int count = 1;
            for (int i = 0; i < s.length(); i++) {
                char c = s.charAt(i);
                char prevChar = i == 0 ? ' ' : s.charAt(i - 1);
                if (c == '0') {
                    if (prevChar == '1' || prevChar == '2') {
                        count = pp;
                    } else {
                        return 0;
                    }
                } else if (prevChar == '1' || (prevChar == '2' && c < 7)) {
                    count = pp + pre;
                } else {
                    count = pre;
                }
                pp = pre;
                pre = count;

            }
            return count;
        }
    }
}
