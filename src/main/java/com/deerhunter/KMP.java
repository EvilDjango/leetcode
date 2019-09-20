package com.deerhunter;

/**
 * Oops, forgot to write comment. Good luck, bro.
 * <p>
 * Copyright (c) 2019, deerhunter0837@gmail.com All Rights Reserved.
 *
 * @author xuejunc
 * @date 2019-09-19
 */
public class KMP {
    public static int kmp(String s, String target) {
        int[] next = getNext(target);
        int i = 0;
        int j = 0;
        while (i < s.length() && j < target.length()) {
            if (j == -1 || s.charAt(i) == target.charAt(j)) {
                i++;
                j++;
            } else {
                j = next[j];
            }
        }
        if (j == target.length()) {
            return i - j;
        } else {
            return -1;
        }
    }

    private static int[] getNext(String target) {
        int[] next = new int[target.length()];
        next[0] = -1;
        int i = 0;
        int j = -1;
        while (i < target.length()-1) {
            if (j == -1 || target.charAt(i) == target.charAt(j)) {
                next[++i] = ++j;
            } else {
                j = next[j];
            }
        }
        return next;
    }
}