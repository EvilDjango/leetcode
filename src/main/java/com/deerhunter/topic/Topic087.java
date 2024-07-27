package com.deerhunter.topic;

import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * 给定一个字符串 s1，我们可以把它递归地分割成两个非空子字符串，从而将其表示为二叉树。
 * <p>
 * 下图是字符串 s1 = "great" 的一种可能的表示形式。
 * <p>
 * great
 * /    \
 * gr    eat
 * / \    /  \
 * g   r  e   at
 * / \
 * a   t
 * 在扰乱这个字符串的过程中，我们可以挑选任何一个非叶节点，然后交换它的两个子节点。
 * <p>
 * 例如，如果我们挑选非叶节点 "gr" ，交换它的两个子节点，将会产生扰乱字符串 "rgeat" 。
 * <p>
 * rgeat
 * /    \
 * rg    eat
 * / \    /  \
 * r   g  e   at
 * / \
 * a   t
 * 我们将 "rgeat” 称作 "great" 的一个扰乱字符串。
 * <p>
 * 同样地，如果我们继续交换节点 "eat" 和 "at" 的子节点，将会产生另一个新的扰乱字符串 "rgtae" 。
 * <p>
 * rgtae
 * /    \
 * rg    tae
 * / \    /  \
 * r   g  ta  e
 * / \
 * t   a
 * 我们将 "rgtae” 称作 "great" 的一个扰乱字符串。
 * <p>
 * 给出两个长度相等的字符串 s1 和 s2，判断 s2 是否是 s1 的扰乱字符串。
 * <p>
 * 示例 1:
 * <p>
 * 输入: s1 = "great", s2 = "rgeat"
 * 输出: true
 * 示例 2:
 * <p>
 * 输入: s1 = "abcde", s2 = "caebd"
 * 输出: false
 * 通过次数8,676提交次数18,838
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/scramble-string
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * <p>
 * Copyright (c) 2019, deerhunter0837@gmail.com All Rights Reserved.
 *
 * @author xuejunc
 * @date 2020/4/9
 */
public class Topic087 {
    public static class Solution1 {
        /**
         * 暴力法-递归
         *
         * @param s1
         * @param s2
         * @return
         */
        public boolean isScramble(String s1, String s2) {
            if (s1.equals(s2)) {
                return true;
            }
            int len = s1.length();
            if (len == 1) {
                return false;
            }
            for (int i = 1; i < len; i++) {
                if ((isScramble(s1.substring(0, i), s2.substring(0, i)) && isScramble(s1.substring(i), s2.substring(i))) ||
                        (isScramble(s1.substring(0, i), s2.substring(len - i)) && isScramble(s1.substring(i), s2.substring(0, len - i)))) {
                    return true;
                }
            }
            return false;
        }
    }

    /**
     * 带缓存的递归
     */
    public static class Solution2 {
        public boolean isScramble(String s1, String s2) {
            return doScramble(s1, s2, new HashMap<>(), new HashMap<>());
        }

        public boolean doScramble(String s1, String s2, Map<String, Set<String>> match, Map<String, Set<String>> dismatch) {
            if (s1.equals(s2)) {
                return true;
            }
            int len = s1.length();
            if (len == 1) {
                return false;
            }
            if (match.get(s1) != null && match.get(s1).contains(s2)) {
                return true;
            }
            if (dismatch.get(s1) != null && dismatch.get(s1).contains(s2)) {
                return false;
            }

            for (int i = 1; i < len; i++) {
                if ((doScramble(s1.substring(0, i), s2.substring(0, i), match, dismatch) && doScramble(s1.substring(i), s2.substring(i), match, dismatch)) ||
                        (doScramble(s1.substring(0, i), s2.substring(len - i), match, dismatch) && doScramble(s1.substring(i), s2.substring(0, len - i), match, dismatch))) {

                    if (!match.containsKey(s1)) {
                        match.put(s1, new HashSet<>());
                    }
                    match.get(s1).add(s2);
                    if (!match.containsKey(s2)) {
                        match.put(s2, new HashSet<>());
                    }
                    match.get(s2).add(s1);
                    return true;
                }
            }

            if (!dismatch.containsKey(s1)) {
                dismatch.put(s1, new HashSet<>());
            }
            dismatch.get(s1).add(s2);
            if (!dismatch.containsKey(s2)) {
                dismatch.put(s2, new HashSet<>());
            }
            dismatch.get(s2).add(s1);
            return false;
        }
    }

    /**
     * 基本思想：将字符串拆分为2部分，每一部分包含的字符类型和数量相同
     */
    public static class Solution3 {
        public boolean isScramble(String s1, String s2) {
            if (s1.equals(s2)) {
                return true;
            }
            int len = s1.length();
            if (len == 1) {
                return false;
            }
            // 正向匹配
            Map<Character, Integer> charCount = new HashMap<>();
            int dismatch = 0;
            for (int i = 0; i < len - 1; i++) {
                char c1 = s1.charAt(i);
                char c2 = s2.charAt(i);
                if (c1 != c2) {
                    dismatch = updateDismatch(charCount, dismatch, c1, c2);
                }

                if (dismatch == 0 && isScramble(s1.substring(0, i + 1), s2.substring(0, i + 1)) && isScramble(s1.substring(i + 1), s2.substring(i + 1))) {
                    return true;
                }
            }

            // 反向匹配
            charCount = new HashMap<>();
            dismatch = 0;
            for (int i = 0; i < len - 1; i++) {
                char c1 = s1.charAt(i);
                char c2 = s2.charAt(len - i - 1);
                if (c1 != c2) {
                    dismatch = updateDismatch(charCount, dismatch, c1, c2);
                }

                if (dismatch == 0 && isScramble(s1.substring(0, i + 1), s2.substring(len - i - 1)) && isScramble(s1.substring(i + 1), s2.substring(0, len - i - 1))) {
                    return true;
                }
            }
            return false;
        }

        private int updateDismatch(Map<Character, Integer> charCount, int dismatch, char c1, char c2) {
            if (charCount.getOrDefault(c1, 0) == 0) {
                dismatch++;
            }
            if (charCount.getOrDefault(c2, 0) == 0) {
                dismatch++;
            }

            charCount.merge(c1, 1, Integer::sum);
            charCount.merge(c2, -1, Integer::sum);
            if (charCount.get(c1) == 0) {
                dismatch--;
            }
            if (charCount.get(c2) == 0) {
                dismatch--;
            }
            return dismatch;
        }
    }

    /**
     * 在解法3的基础上增加了缓存，但是从提交的结果来看，似乎没有提升
     */
    public static class Solution4 {
        public boolean isScramble(String s1, String s2) {
            return doScramble(s1, s2, new HashMap<>(), new HashMap<>());
        }

        public boolean doScramble(String s1, String s2, Map<String, Set<String>> matchMap, Map<String, Set<String>> dismatchMap) {
            if (matchMap.containsKey(s1) && matchMap.get(s1).contains(s2)) {
                return true;
            }
            if (dismatchMap.containsKey(s1) && dismatchMap.get(s1).contains(s2)) {
                return false;
            }

            if (s1.equals(s2)) {
                putInMap(matchMap, s1, s2);
                return true;
            }
            int len = s1.length();
            if (len == 1) {
                return false;
            }
            // 正向匹配
            Map<Character, Integer> charCount = new HashMap<>();
            int dismatch = 0;
            for (int i = 0; i < len - 1; i++) {
                char c1 = s1.charAt(i);
                char c2 = s2.charAt(i);
                if (c1 != c2) {
                    dismatch = updateDismatch(charCount, dismatch, c1, c2);
                }

                if (dismatch == 0 && doScramble(s1.substring(0, i + 1), s2.substring(0, i + 1), matchMap, dismatchMap) && doScramble(s1.substring(i + 1), s2.substring(i + 1), matchMap, dismatchMap)) {
                    putInMap(matchMap, s1, s2);
                    return true;
                }
            }

            // 反向匹配
            charCount = new HashMap<>();
            dismatch = 0;
            for (int i = 0; i < len - 1; i++) {
                char c1 = s1.charAt(i);
                char c2 = s2.charAt(len - i - 1);
                if (c1 != c2) {
                    dismatch = updateDismatch(charCount, dismatch, c1, c2);
                }

                if (dismatch == 0 && doScramble(s1.substring(0, i + 1), s2.substring(len - i - 1), matchMap, dismatchMap) && doScramble(s1.substring(i + 1), s2.substring(0, len - i - 1), matchMap, dismatchMap)) {
                    putInMap(matchMap, s1, s2);
                    return true;
                }
            }
            putInMap(dismatchMap, s1, s2);
            return false;
        }

        private int updateDismatch(Map<Character, Integer> charCount, int dismatch, char c1, char c2) {
            if (charCount.getOrDefault(c1, 0) == 0) {
                dismatch++;
            }
            if (charCount.getOrDefault(c2, 0) == 0) {
                dismatch++;
            }

            charCount.merge(c1, 1, Integer::sum);
            charCount.merge(c2, -1, Integer::sum);
            if (charCount.get(c1) == 0) {
                dismatch--;
            }
            if (charCount.get(c2) == 0) {
                dismatch--;
            }
            return dismatch;
        }

        private void putInMap(Map<String, Set<String>> map, String s1, String s2) {
            if (!map.containsKey(s1)) {
                map.put(s1, new HashSet<>());
            }
            map.get(s1).add(s2);

            if (!map.containsKey(s2)) {
                map.put(s2, new HashSet<>());
            }
            map.get(s2).add(s1);
        }
    }

    /**
     * 动态规划法
     */
    public static class Solution5 {
        public boolean isScramble(String s1, String s2) {
            int n = s1.length();
            // dp[i][j][k]表示：从s1的i位置起，从s2的j位置起，分别截取一个长度为k+1的子字符串，这两个子字符串是否互为扰乱字符串
            boolean[][][] dp = new boolean[n][n][n];

            // 先计算长度为1的情形
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    dp[i][j][0] = s1.charAt(i) == s2.charAt(j);
                }
            }
            for (int i = 1; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    for (int k = 0; k < n; k++) {
                        // 超出字符串长度了，必然不匹配
                        if (j + i >= n || k + i >= n) {
                            continue;
                        }
                        for (int l = 0; l < i; l++) {
                            if ((dp[j][k][l] && dp[j + l + 1][k + l + 1][i - l - 1]) || (dp[j][k + i - l][l] && dp[j + l + 1][k][i - l - 1])) {
                                dp[j][k][i] = true;
                                break;
                            }
                        }
                    }
                }
            }

            return dp[0][0][n - 1];
        }
    }

    /**
     * 递归-使用数组缓存中间结果
     */
    public static class Solution6 {
        public boolean isScramble(String s1, String s2) {
            int n = s1.length();
            Boolean[][][] dp = new Boolean[n][n][n];
            return findScramble(s1, s2, dp, 0, 0, n - 1);
        }

        /**
         * 递归查找
         *
         * @param s1
         * @param s2
         * @param dp
         * @param i  s1起始下标
         * @param j  s2起始下标
         * @param k  k+1等于子串长度
         */
        private boolean findScramble(String s1, String s2, Boolean[][][] dp, int i, int j, int k) {
            if (dp[i][j][k] != null) {
                return dp[i][j][k];
            }

            if (k == 0) {
                dp[i][j][k] = s1.charAt(i) == s2.charAt(j);
            } else {
                for (int l = 0; l < k; l++) {
                    if ((findScramble(s1, s2, dp, i, j, l) && findScramble(s1, s2, dp, i + l + 1, j + l + 1, k - l - 1)) ||
                            (findScramble(s1, s2, dp, i, j + k - l, l) && findScramble(s1, s2, dp, i + l + 1, j, k - l - 1))) {
                        dp[i][j][k] = true;
                    }
                }
                if (dp[i][j][k] == null) {
                    dp[i][j][k] = false;
                }
            }
            return dp[i][j][k];
        }
    }

    /**
     * 与解法2本质相同，但是更加简洁的写法
     */
    public static class Solution7 {
        /**
         * 暴力法-递归
         *
         * @param s1
         * @param s2
         * @return
         */
        public boolean isScramble(String s1, String s2) {
            if (s1.equals(s2)) {
                return true;
            }
            int len = s1.length();
            // 先判断字符串类型和个数是否相等，不等的话直接返回false
            Map<Character, Integer> map = new HashMap<>();
            for (int i = 0; i < len; i++) {
                map.merge(s1.charAt(i), 1, Integer::sum);
                map.merge(s2.charAt(i), -1, Integer::sum);
            }
            for (Map.Entry<Character, Integer> entry : map.entrySet()) {
                if (entry.getValue() != 0) {
                    return false;
                }
            }

            // 按多种方式分割
            for (int i = 1; i < len; i++) {
                if ((isScramble(s1.substring(0, i), s2.substring(0, i)) && isScramble(s1.substring(i), s2.substring(i))) ||
                        (isScramble(s1.substring(0, i), s2.substring(len - i)) && isScramble(s1.substring(i), s2.substring(0, len - i)))) {
                    return true;
                }
            }
            return false;
        }
    }

    /**
     * 复制的LeetCode提交中最快的算法
     */
    public static class Solution8 {
        public boolean isScramble(String s1, String s2) {
            if (s1.length() != s2.length()) return false;
            return internalRecusive(s1.toCharArray(), 0, s2.toCharArray(), 0, s1.length(), new int[26]);
        }

        private boolean internalRecusive(char[] cs1, int from1, char[] cs2, int from2, int len, int[] count) {
            if (len == 0 || (len == 1 && cs1[from1] == cs2[from2])) return true;
            if (!isValid(cs1, from1, cs2, from2, len, count)) return false;
            for (int i = 1; i < len; i++) {
                if (internalRecusive(cs1, from1, cs2, from2 + len - i, i, count) &&
                        internalRecusive(cs1, from1 + i, cs2, from2, len - i, count)) return true;
                if (internalRecusive(cs1, from1, cs2, from2, i, count) &&
                        internalRecusive(cs1, from1 + i, cs2, from2 + i, len - i, count)) return true;
            }
            return false;
        }

        private boolean isValid(char[] cs1, int from1, char[] cs2, int from2, int len, int[] count) {
            Arrays.fill(count, 0);
            for (int i = 0; i < len; i++) {
                count[cs1[i + from1] - 'a']++;
                count[cs2[i + from2] - 'a']--;
            }
            for (int cnt : count)
                if (cnt != 0) return false;
            return true;
        }

    }


}
