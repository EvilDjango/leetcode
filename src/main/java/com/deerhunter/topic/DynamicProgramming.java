package com.deerhunter.topic;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * 一些动态规划的算法
 * <p>
 * Copyright (c) 2019, deerhunter0837@gmail.com All Rights Reserved.
 *
 * @author xuejunc
 * @date 2019-08-09
 */
public class DynamicProgramming {
    // 钞票问题：现在有1，5，11三种面额的钱，求总额X的钱至少需要多少张钞票
    // f(x)=min{f(x-1)+1,f(x-5}+1,f(x-11)+1}
    public static void money() {
        int max = 3;

        int[] f = new int[max + 1 + 11];
        for (int i = 0; i <= max; i++) {
            updateMin(f, i + 1, f[i] + 1);
            updateMin(f, i + 5, f[i] + 1);
            updateMin(f, i + 11, f[i] + 1);
        }
        print(f);
    }

    private static void updateMin(int[] f, int index, int newValue) {
        updateMin(f, 0, index, newValue);

    }

    private static void updateMin(int[] f, int defaultValue, int index, int newValue) {
        if (defaultValue == f[index]) {
            f[index] = newValue;
            return;
        }
        f[index] = Math.min(f[index], newValue);

    }


    // 最长子序列问题
    public static void lis() {
        int[] s = {1, 5, 3, 4, 6, 9, 7, 8};
        int[] f = new int[s.length];
        for (int i = 0; i < f.length; i++) {
            f[i] = 1;
        }
        for (int i = 0; i < s.length; i++) {
            for (int j = i + 1; j < s.length; j++) {
                if (s[j] > s[i] && f[j] < f[i] + 1) {
                    f[j] = f[i] + 1;
                }
            }
        }
        print(f);
    }

    // 最长子序列问题， 注意，这是错误解法
    public static void lis2() {
//        int[] s = {1, 5, 3, 4, 6, 9, 7, 8};
        int[] s = {1, 3, 7, 9, 2};
        int[] f = new int[s.length + 1];
        for (int i = 0; i < f.length; i++) {
            f[i] = Integer.MAX_VALUE;
        }
        int[] indexes = new int[s.length + 1];
        for (int i = 0; i < indexes.length; i++) {
            indexes[i] = -1;
        }
        int min = s[0];
        int minIdex = 0;
        for (int i = 1; i < s.length; i++) {
            if (s[i] < min) {
                min = s[i];
                minIdex = i;
            }
        }
        f[1] = min;
        indexes[1] = minIdex;
        for (int len = 2; len <= s.length; len++) {
            if (-1 == indexes[len - 1]) {
                break;
            }
            for (int j = indexes[len - 1] + 1; j < s.length; j++) {
                if (s[j] > f[len - 1] && s[j] < f[len]) {
                    f[len] = s[j];
                    indexes[len] = j;
                }
            }
        }
        print(indexes);
        print(f);
    }

    // f(x）表示长度为x的子序列的最小末尾元素
    public static void lis3() {
//        int[] s = {1, 5, 3, 4, 6, 9, 7, 8};
        int[] s = {1, 3, 7, 9, 2};
//        int[] s = {1, 2, 3, 4};
        int[] f = new int[s.length + 1];
        for (int i = 0; i < f.length; i++) {
            f[i] = Integer.MAX_VALUE;
        }
        f[1] = s[0];
        int len = 1;
        for (int i = 1; i < s.length; i++) {
            if (s[i] > f[len]) {
                len++;
                f[len] = s[i];
            } else {
                int l = 1, r = len, mid;
                while (l < r) {
                    mid = (l + r) / 2;
                    if (f[mid] > s[i]) {
                        r = mid;
                    } else {
                        l = mid + 1;
                    }
                }
                f[l] = s[i];
            }

        }
        System.out.println(len);
        print(f);
    }


    public static void lcs() {
        int[] a = {1, 10, 20, 30, 4, 5, 6, 7};
        int[] b = {1, 4, 5, 10, 20, 30, 1, 6, 7};
        int[][] f = new int[a.length][b.length];
        int[] lcs = new int[Math.max(a.length, b.length)];
        for (int i = 0; i < a.length; i++) {
            for (int j = 0; j < b.length; j++) {
                if (i == 0 || j == 0) {
                    f[i][j] = 1;
                } else if (a[i] == b[j]) {
                    f[i][j] = f[i - 1][j - 1] + 1;
                    lcs[f[i][j] - 1] = a[i];
                    print(lcs);
                } else {
                    f[i][j] = Math.max(f[i - 1][j], f[i][j - 1]);
                }
            }
        }
        print(f);
        print(lcs);

    }

    public static void lcs2() {
//        int[] a = {1, 10, 20, 30, 4, 5, 6, 7};
//        int[] b = {1, 4, 5, 10, 20, 30, 6, 7};
        int[] a = {1, 2, 1};
        int[] b = {1, 2, 1, 2};
        int[] f = new int[b.length];
        for (int i = 0; i < f.length; i++) {
            f[i] = Integer.MAX_VALUE;
        }
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < a.length; i++) {
            map.put(a[i], i);
        }
        int len = 1;
        f[1] = map.get(b[0]);
        for (int i = 1; i < b.length; i++) {
            if (map.get(b[i]) > f[len]) {
                f[++len] = map.get(b[i]);
            } else {
                int l = 0, r = len, mid;
                while (l < r) {
                    mid = (l + r) / 2;
                    if (f[mid] > map.get(b[i])) {
                        r = mid;
                    } else {
                        l = mid + 1;
                    }
                }
                f[l] = map.get(b[i]);
            }

        }
        System.out.println(len);
        print(f);

    }

    private static void print(int[] lcs) {
        System.out.println(Arrays.toString(lcs));
    }

    private static void print(int[][] f) {
        System.out.println("=============");
        for (int i = 0; i < f.length; i++) {
            print(f[i]);
        }
        System.out.println("=============");
    }

    public static void main(String[] args) {
//        money();
//        lcs();
        lcs2();
//        lis3();
    }

}
