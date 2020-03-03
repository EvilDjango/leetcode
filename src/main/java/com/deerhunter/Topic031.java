package com.deerhunter;

import com.deerhunter.common.Utils;

import java.util.Date;

/**
 * 实现获取下一个排列的函数，算法需要将给定数字序列重新排列成字典序中下一个更大的排列。
 * <p>
 * 如果不存在下一个更大的排列，则将数字重新排列成最小的排列（即升序排列）。
 * <p>
 * 必须原地修改，只允许使用额外常数空间。
 * <p>
 * 以下是一些例子，输入位于左侧列，其相应输出位于右侧列。
 * 1,2,3 → 1,3,2
 * 3,2,1 → 1,2,3
 * 1,1,5 → 1,5,1
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/next-permutation
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * <p>
 * Copyright (c) 2019, deerhunter0837@gmail.com All Rights Reserved.
 *
 * @author xuejunc
 * @date 2019-09-05
 */
public class Topic031 {
    /**
     * 看了官方题解后自己写的代码（没有特别明白题意，所以直接看题解了）
     *
     * @param nums
     */
    public static void nextPermutation(int[] nums) {
        if (nums == null || nums.length < 2) {
            return;
        }
        int index = -1;
        for (int i = nums.length - 2; i >= 0; i--) {
            if (nums[i] < nums[i + 1]) {
                index = i;
                break;
            }
        }
        if (index != -1) {
            int position = Utils.reversedUpperBound(nums, nums[index], index + 1, nums.length);
            Utils.swap(nums, index, position);
        }
        int l = index + 1;
        int r = nums.length - 1;
        while (l < r) {
            Utils.swap(nums, l, r);
            l++;
            r--;
        }
    }

    public static void main(String[] args) {
        System.out.println(new Date(1566751107012L));
    }
}
