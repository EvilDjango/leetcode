package com.deerhunter.topic;

import java.util.ArrayList;
import java.util.List;

/**
 * 给定一个只包含数字的字符串，复原它并返回所有可能的 IP 地址格式。
 * <p>
 * 示例:
 * <p>
 * 输入: "25525511135"
 * 输出: ["255.255.11.135", "255.255.111.35"]
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/restore-ip-addresses
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * <p>
 * Copyright (c) 2019, deerhunter0837@gmail.com All Rights Reserved.
 *
 * @author xuejunc
 * @createTime 2020/4/15 01:17
 */
public class Topic093 {
    /**
     * 回溯法
     */
    public static class Solution1 {
        private List<String> results;
        private StringBuilder sb;
        private char[] chars;

        public List<String> restoreIpAddresses(String s) {
            results = new ArrayList<>();
            sb = new StringBuilder();
            chars = s.toCharArray();
            dfs(0, 0);
            return this.results;
        }

        /**
         * 回溯查找可能的ip数字
         *
         * @param count 当前已经确定的数字
         * @param i     下一个待处理的字符下标
         */
        private void dfs(int count, int i) {
            // 找到了4个数字，并且字符刚好用完，标志着我们找到了一个解
            if (count == 4 && i == chars.length) {
                results.add(sb.toString());
                return;
            }

            int remainCount = 4 - count;
            int remainsChars = chars.length - i;
            // 判断剩余的字符是否有解
            if (remainCount > remainsChars || remainCount * 3 < remainsChars) {
                return;
            }

            int len = sb.length();
            // 不允许出现以0开头的多位数
            int maxLen = chars[i] == '0' ? 1 : 3;
            for (int j = 0; j < maxLen && i + j < chars.length; j++) {
                // 取三位数时，需要判断是否超出255
                if (j == 2 && (chars[i] - '0') * 100 + (chars[i + 1] - '0') * 10 + chars[i + 2] - '0' > 255) {
                    continue;
                }
                for (int k = 0; k <= j; k++) {
                    sb.append(chars[i + k]);
                }
                // 第四个数字后面不需要加点
                if (count < 3) {
                    sb.append('.');
                }
                dfs(count + 1, i + j + 1);
                // 因为第四个数字后面不需要加点，回溯时少删除一位
                sb.delete(len, count < 3 ? len + j + 2 : len + j + 1);
            }
        }
    }
}
