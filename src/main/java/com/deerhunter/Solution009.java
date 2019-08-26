package com.deerhunter;

import java.util.regex.Matcher;

/**
 * Oops, forgot to write comment. Good luck, bro.
 * <p>
 * Copyright (c) 2019, deerhunter0837@gmail.com All Rights Reserved.
 *
 * @author xuejunc
 * @date 2019-08-14
 */
public class Solution009 {
    public boolean isPalindrome(int x) {
        String s = x + "";
        int left = 0;
        int right = s.length() - 1;
        while (left <= right) {
            if (s.charAt(left) != s.charAt(right)) {
                return false;
            }
            left++;
            right--;
        }
        return true;
    }

    public boolean isPalindrome2(int x) {
        if (x < 0) {
            return false;
        }
        // 高位
        int high = getBits(x) - 1;
        // 低位
        int low = 0;
        while (high >= low) {
            if (getNumByBit(x, high) != getNumByBit(x, low)) {
                return false;
            }
            high--;
            low++;
        }
        return true;
    }


    /**
     * 获取x右起第i位的数字（i从0开始计）
     *
     * @param x
     * @param i
     * @return
     */
    public static int getNumByBit(int x, int i) {
        if (i < 0) {
            return 0;
        }
        // 去除高位
        x %= Math.pow(10, i + 1);
        if (i > 0) {
            // 去除低位
            x /= Math.pow(10, i);
        }
        return x;
    }

    public static int getBits(int x) {
        int bits = 1;
        while ((x /= 10) != 0) {
            bits++;
        }
        return bits;
    }


}