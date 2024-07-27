package com.deerhunter.offer;

/**
 * 剑指 Offer 17. 打印从1到最大的n位数
 * 输入数字 n，按顺序打印出从 1 到最大的 n 位十进制数。比如输入 3，则打印出 1、2、3 一直到最大的 3 位数 999。
 * <p>
 * 示例 1:
 * <p>
 * 输入: n = 1
 * 输出: [1,2,3,4,5,6,7,8,9]
 * <p>
 * <p>
 * 说明：
 * <p>
 * 用返回一个整数列表来代替打印
 * n 为正整数
 * 通过次数198,629提交次数255,143
 * <p>
 * Copyright (c) @2022 deerhunter0837@gmail.com All Rights Reserved.
 *
 * @author xuejunc
 * @createTime 2022/5/17 下午1:24
 */
public class Offer017 {
    public int[] printNumbers(int n) {
        int bound = (int) Math.pow(10, n);
        int[] nums = new int[bound - 1];
        for (int i = 0; i < bound - 1; i++) {
            nums[i] = i + 1;
        }
        return nums;
    }

    /**
     * 进阶版题目，可以打印无穷大的大数字
     *
     * @param n
     * @return
     */
    public String printNumbersToString(int n) {
        StringBuilder sb = new StringBuilder();
        dfs(new int[n], 0, -1, sb);
        sb.deleteCharAt(sb.length() - 1);
        return sb.toString();
    }

    private void dfs(int[] bits, int index, int nonZeroStart, StringBuilder sb) {
        if (index == bits.length) {
            if (nonZeroStart == -1) {
                nonZeroStart = bits.length - 1;
            }
            for (int i = nonZeroStart; i < bits.length; i++) {
                sb.append(bits[i]);
            }
            sb.append(",");
            return;
        }
        for (int i = 0; i < 10; i++) {
            bits[index] = i;
            int newNonZeroStart = nonZeroStart == -1 && i != 0 ? index : nonZeroStart;
            dfs(bits, index + 1, newNonZeroStart, sb);
        }
    }

    public static void main(String[] args) {
        for (int i = 0; i < 100; i++) {
            System.out.print(i + ",");
        }
    }
}
