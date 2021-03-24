package com.deerhunter;

/**
 * kmp算法，参考链接：
 * <p>
 * 如何更好地理解和掌握 KMP 算法? - 海纳的回答 - 知乎
 * https://www.zhihu.com/question/21923021/answer/281346746
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
        while (i < target.length() - 1) {
            if (j == -1 || target.charAt(i) == target.charAt(j)) {
                next[++i] = ++j;
            } else {
                j = next[j];
            }
        }
        return next;
    }

    /**
     * kmp算法的常规写法，与上面的写法区别在于没有使用将pmt表右移一位来简化操作
     *
     * @param source
     * @param target
     * @return
     */
    public static int kmp2(String source, String target) {
        int[] pmt = getPmt(target);
        int i = 0;
        int j = 0;
        while (i < source.length() && j < target.length()) {
            if (source.charAt(i) == target.charAt(j)) {
                i++;
                j++;
            } else if (j == 0) {
                i++;
            } else {
                j = pmt[j - 1];
            }
        }
        if (j == target.length()) {
            return i - target.length();
        }
        return -1;

    }

    private static int[] getPmt(String s) {
        int[] pmt = new int[s.length()];
        int i = 1;
        int j = 0;
        while (i < s.length()) {
            if (s.charAt(i) == s.charAt(j)) {
                pmt[i++] = ++j;
            } else if (j == 0) {
                pmt[i] = 0;
                i++;
            } else {
                j = pmt[j - 1];
            }
        }
        return pmt;
    }

}
