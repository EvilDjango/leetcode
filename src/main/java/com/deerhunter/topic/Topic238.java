package com.deerhunter.topic;

/**
 * 238. 除自身以外数组的乘积
 * 给你一个长度为 n 的整数数组 nums，其中 n > 1，返回输出数组 output ，其中 output[i] 等于 nums 中除 nums[i] 之外其余各元素的乘积。
 * <p>
 * <p>
 * <p>
 * 示例:
 * <p>
 * 输入: [1,2,3,4]
 * 输出: [24,12,8,6]
 * <p>
 * <p>
 * 提示：题目数据保证数组之中任意元素的全部前缀元素和后缀（甚至是整个数组）的乘积都在 32 位整数范围内。
 * <p>
 * 说明: 请不要使用除法，且在 O(n) 时间复杂度内完成此题。
 * <p>
 * 进阶：
 * 你可以在常数空间复杂度内完成这个题目吗？（ 出于对空间复杂度分析的目的，输出数组不被视为额外空间。）
 * <p>
 * 通过次数120,169提交次数166,945
 * <p>
 * Copyright (c) @2021 deerhunter0837@gmail.com All Rights Reserved.
 *
 * @author xuejunc
 * @createTime 7/1/21 3:47 PM
 */
public class Topic238 {
    public static class Solution1 {
        public int[] productExceptSelf(int[] nums) {
            int n = nums.length;
            int[] product = new int[n];
            product[0] = 1;
            for (int i = 0; i < n - 1; i++) {
                product[i + 1] = nums[i] * product[i];
            }
            int[] reverseProduct = new int[n];
            reverseProduct[n - 1] = 1;
            for (int i = n - 1; i > 0; i--) {
                reverseProduct[i - 1] = reverseProduct[i] * nums[i];
            }
            int[] ans = new int[n];
            for (int i = 0; i < n; i++) {
                ans[i] = product[i] * reverseProduct[i];
            }
            return ans;
        }
    }

    /**
     * O(1)空间复杂度
     */
    public static class Solution2 {
        public int[] productExceptSelf(int[] nums) {
            int n = nums.length;
            int[] product = new int[n];
            product[0] = 1;
            for (int i = 0; i < n - 1; i++) {
                product[i + 1] = nums[i] * product[i];
            }
            int base = 1;
            for (int i = n - 1; i >= 0; i--) {
                product[i] = product[i] * base;
                base *= nums[i];
            }
            return product;
        }
    }
}
