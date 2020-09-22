package com.deerhunter;

import java.util.ArrayList;
import java.util.List;

/**
 * 给定一个字符串 s，将 s 分割成一些子串，使每个子串都是回文串。
 * <p>
 * 返回 s 所有可能的分割方案。
 * <p>
 * 示例:
 * <p>
 * 输入: "aab"
 * 输出:
 * [
 * ["aa","b"],
 * ["a","a","b"]
 * ]
 * <p>
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/palindrome-partitioning
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * <p>
 * Copyright (c) 20120 deerhunter0837@gmail.com All Rights Reserved.
 *
 * @author xuejunc
 * @createTime 2020/9/20 20:27
 */
public class Topic131 {
    /**
     * 暴力算法，中心扩展，深度遍历回溯
     */
    public static class Solution1 {
        public List<List<String>> partition(String s) {
            List<List<String>> result = new ArrayList<>();
            int len = s.length();
            // radiuses[i]表示以每个位置为中心的最大回文半径,当i为偶数时，表示以i/2处的字符为中心，当i为奇数时，表示以i/2处字符的右侧间隙为中心
            int[] radiuses = new int[2 * len - 1];
            for (int i = 0; i < 2 * len - 1; i++) {
                radiuses[i] = getRadius(s, i);
            }
            dfs(s, 0, 0, radiuses, result, new ArrayList<>());
            return result;
        }

        /**
         * 深度搜索全部回文组合
         *
         * @param s        原始字符串
         * @param i        当前位置
         * @param lack     前面部分需要补充的长度
         * @param radiuses 以每个位置为中心的最大回文半径
         * @param result   结果集合
         */
        private void dfs(String s, int i, int lack, int[] radiuses, List<List<String>> result, List<String> current) {
            if (i == radiuses.length) {
                if (lack == 0) {
                    result.add(new ArrayList<>(current));
                }
                return;
            }

            boolean isGap = i % 2 != 0;
            if (isGap) {
                // 当前位置不扩展
                dfs(s, i + 1, lack, radiuses, result, current);
                if (lack > 0 && radiuses[i] >= lack) {
                    current.add(s.substring(i / 2 - lack + 1, i / 2 + lack + 1));
                    dfs(s, i + 1, -lack, radiuses, result, current);
                    current.remove(current.size() - 1);
                }
            } else {
                // 当前位置不扩展
                dfs(s, i + 1, lack + 1, radiuses, result, current);
                // 位置被前面的扩展占用，无法扩展
                if (lack >= 0 && radiuses[i] >= lack) {
                    current.add(s.substring(i / 2 - lack, i / 2 + lack + 1));
                    dfs(s, i + 1, -lack, radiuses, result, current);
                    current.remove(current.size() - 1);
                }
            }
        }

        private static int getRadius(String s, int i) {
            boolean isGap = i % 2 != 0;
            int radius = 0;
            int left = isGap ? i / 2 : i / 2 - 1, right = isGap ? i / 2 + 1 : i / 2 + 1;
            while (left >= 0 && right < s.length() && s.charAt(left--) == s.charAt(right++)) {
                radius++;
            }
            return radius;
        }

    }

    /**
     * 动态规划+回溯，参考优秀题解
     */
    public static class Solution2 {
        public List<List<String>> partition(String s) {
            List<List<String>> result = new ArrayList<>();
            int len = s.length();
            if (len == 0) {
                return result;
            }

            boolean[][] isPalindrome = new boolean[len][len];
            for (int i = 0; i < len; i++) {
                for (int j = 0; j <= i; j++) {
                    if (s.charAt(i) == s.charAt(j)) {
                        isPalindrome[j][i] = i - j < 3 || isPalindrome[j + 1][i - 1];
                    }
                }
            }
            dfs(s, 0, isPalindrome, result, new ArrayList<>());

            return result;
        }

        private void dfs(String s, int index, boolean[][] isPalindrome, List<List<String>> result, ArrayList<String> partition) {
            if (index == s.length()) {
                result.add(new ArrayList<>(partition));
                return;
            }
            for (int i = index; i < s.length(); i++) {
                if (isPalindrome[index][i]) {
                    partition.add(s.substring(index, i + 1));
                    dfs(s, i + 1, isPalindrome, result, partition);
                    partition.remove(partition.size() - 1);
                }
            }
        }
    }
}
