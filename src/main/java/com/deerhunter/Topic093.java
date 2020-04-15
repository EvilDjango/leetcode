package com.deerhunter;

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

        public List<String> restoreIpAddresses(String s) {
            this.results = new ArrayList<>();
            sb = new StringBuilder();
            dfs(s.toCharArray(), 0, 0);
            return this.results;
        }

        private void dfs(char[] chars, int count, int i) {
            if (count == 4 && i == chars.length) {
                results.add(sb.toString());
                return;
            }
            int remainCount = 4 - count;
            int remainsChars = chars.length - i;
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
                if (count < 3) {
                    sb.append('.');
                }
                dfs(chars, count + 1, i + j + 1);
                sb.delete(len, count < 3 ? len + j + 2 : len + j + 1);
            }
        }
    }
}
