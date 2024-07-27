package com.deerhunter.offer;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 剑指 Offer 38. 字符串的排列
 * 输入一个字符串，打印出该字符串中字符的所有排列。
 * <p>
 * <p>
 * <p>
 * 你可以以任意顺序返回这个字符串数组，但里面不能有重复元素。
 * <p>
 * <p>
 * <p>
 * 示例:
 * <p>
 * 输入：s = "abc"
 * 输出：["abc","acb","bac","bca","cab","cba"]
 * <p>
 * <p>
 * 限制：
 * <p>
 * 1 <= s 的长度 <= 8
 * <p>
 * 通过次数206,085提交次数355,367
 * <p>
 * Copyright (c) @2022 deerhunter0837@gmail.com All Rights Reserved.
 *
 * @author xuejunc
 * @createTime 2022/5/2 下午7:15
 */
public class Offer038 {
    /**
     * 回溯
     *
     * @param s
     * @return
     */
    public String[] permutation(String s) {
        List<String> permutations = new ArrayList<>();
        boolean[] used = new boolean[s.length()];
        StringBuilder sb = new StringBuilder();
        char[] chars = s.toCharArray();
        Arrays.sort(chars);
        backTrack(permutations, chars, used, sb);
        return permutations.toArray(new String[0]);
    }

    private void backTrack(List<String> permutations, char[] chars, boolean[] used, StringBuilder sb) {
        if (sb.length() == chars.length) {
            permutations.add(sb.toString());
            return;
        }
        for (int i = 0; i < chars.length; i++) {
            if (used[i] || i > 0 && chars[i - 1] == chars[i] && !used[i - 1]) {
                continue;
            }
            sb.append(chars[i]);
            used[i] = true;
            backTrack(permutations, chars, used, sb);
            used[i] = false;
            sb.deleteCharAt(sb.length() - 1);
        }
    }

    /**
     * 下一个排列
     *
     * @param s
     * @return
     */
    public String[] permutation2(String s) {
        char[] chars = s.toCharArray();
        Arrays.sort(chars);
        List<String> res = new ArrayList<>();
        res.add(new String(chars));
        while (nextPermutation(chars) != -1) {
            res.add(new String(chars));
        }
        return res.toArray(new String[0]);
    }

    private int nextPermutation(char[] chars) {
        int n = chars.length;
        int i = n - 2;
        while (i >= 0 && chars[i] >= chars[i + 1]) {
            i--;
        }
        if (i >= 0) {
            int j = n - 1;
            while (chars[j] <= chars[i]) {
                j--;
            }
            swap(chars, i, j);
        }
        reverse(chars, i + 1);
        return i;
    }

    private void reverse(char[] chars, int i) {
        int l = i, r = chars.length - 1;
        while (l < r) {
            swap(chars, l++, r--);
        }
    }

    private void swap(char[] chars, int i, int j) {
        char temp = chars[i];
        chars[i] = chars[j];
        chars[j] = temp;
    }
}
