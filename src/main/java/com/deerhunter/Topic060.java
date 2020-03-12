package com.deerhunter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.IntStream;

import static com.deerhunter.common.Utils.factorial;

/**
 * 给出集合 [1,2,3,…,n]，其所有元素共有 n! 种排列。
 * <p>
 * 按大小顺序列出所有排列情况，并一一标记，当 n = 3 时, 所有排列如下：
 * <p>
 * "123"
 * "132"
 * "213"
 * "231"
 * "312"
 * "321"
 * 给定 n 和 k，返回第 k 个排列。
 * <p>
 * 说明：
 * <p>
 * 给定 n 的范围是 [1, 9]。
 * 给定 k 的范围是[1,  n!]。
 * 示例 1:
 * <p>
 * 输入: n = 3, k = 3
 * 输出: "213"
 * 示例 2:
 * <p>
 * 输入: n = 4, k = 9
 * 输出: "2314"
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/permutation-sequence
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * <p>
 * Copyright (c) 2019, deerhunter0837@gmail.com All Rights Reserved.
 *
 * @author xuejunc
 * @date 2020-03-12
 */
public class Topic060 {
    public static class Solution1 {
        public String getPermutation(int n, int k) {
            return searchPermutations(n, k, new boolean[n], new int[n], 0, new int[1]);
        }

        /**
         * 递归查找排列
         *
         * @param n         总数
         * @param k         需要查找的排列序号
         * @param used      标记数字使用情况，used[i]==true表示 数组i+1已经被使用，否则表示i+1还未被使用
         * @param temp      保存当前的排列
         * @param tempIndex temp数组当前需要处理的下标
         * @param count     当前已经获取的排列总数
         * @return
         */
        private String searchPermutations(int n, int k, boolean[] used, int[] temp, int tempIndex, int[] count) {
            if (tempIndex == n) {
                count[0]++;
                if (count[0] == k) {
                    StringBuilder sb = new StringBuilder();
                    for (int i : temp) {
                        sb.append(i);
                    }
                    return sb.toString();
                }
                return "";
            }
            for (int i = 0; i < used.length; i++) {
                if (!used[i]) {
                    temp[tempIndex] = i + 1;
                    used[i] = true;
                    String s = searchPermutations(n, k, used, temp, tempIndex + 1, count);
                    if (s.length() > 0) {
                        return s;
                    }
                    used[i] = false;
                }
            }
            return "";
        }

    }

    /**
     * 首先将k转换为阶乘数（从低位到高位转码），然后把阶乘数映射到具体的排列
     */
    public static class Solution2 {
        public String getPermutation(int n, int k) {
            // 阶乘数
            int[] factorialNum = new int[n];
            // 把k转换为从0开始的下标
            k = k - 1;
            // 阶乘数的最低位必然为0
            factorialNum[n - 1] = 0;
            for (int i = 1; i < n; i++) {
                factorialNum[n - i - 1] = k % (i + 1);
                k /= i + 1;
            }
            // 从1到n的全部数字
            List<Integer> nums = new ArrayList<>();
            for (int i = 0; i < n; i++) {
                nums.add(i + 1);
            }

            // 把阶乘数转换为具体的排列
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < n; i++) {
                // 阶乘数的权重即为应当选取的数字的下标
                sb.append(nums.get(factorialNum[i]));
                // 移除已经用过的数字
                nums.remove(factorialNum[i]);
            }

            return sb.toString();
        }

    }

    /**
     * 思路同Solution2，区别在于这里转换阶乘数时，是从高位到低位
     */
    public static class Solution3 {
        public String getPermutation(int n, int k) {
            // 阶乘 factorial[i]表示 i的阶乘
            int[] factorial = new int[n];
            factorial[0] = 1;
            for (int i = 1; i < n; i++) {
                factorial[i] = i * factorial[i - 1];
            }

            // 从1到n的数字集合
            List<Integer> nums = new ArrayList<>();
            for (int i = 0; i < n; i++) {
                nums.add(i + 1);
            }

            // 将k转换为从0开始的序数
            k = k - 1;
            StringBuilder sb = new StringBuilder();
            for (int i = n; i >= 1; i--) {
                int factorialNum = k / factorial[i - 1];
                sb.append(nums.get(factorialNum));
                nums.remove(factorialNum);
                k = k - factorialNum * factorial[i - 1];
            }
            return sb.toString();
        }

    }
}
