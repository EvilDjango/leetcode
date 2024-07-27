package com.deerhunter.topic;

/**
 * 283. 移动零
 * 给定一个数组 nums，编写一个函数将所有 0 移动到数组的末尾，同时保持非零元素的相对顺序。
 * <p>
 * 示例:
 * <p>
 * 输入: [0,1,0,3,12]
 * 输出: [1,3,12,0,0]
 * 说明:
 * <p>
 * 必须在原数组上操作，不能拷贝额外的数组。
 * 尽量减少操作次数。
 * 通过次数408,828提交次数640,151
 * <p>
 * Copyright (c) @2021 deerhunter0837@gmail.com All Rights Reserved.
 *
 * @author xuejunc
 * @createTime 7/15/21 9:26 AM
 */
public class Topic283 {
    public static class Solution1 {
        public void moveZeroes(int[] nums) {
            int nonZeroIndex = 0;
            for (int i = 0; i < nums.length; i++) {
                if (nums[i] != 0) {
                    if (nonZeroIndex != i) {
                        nums[nonZeroIndex] = nums[i];
                        nums[i] = 0;
                    }
                    nonZeroIndex++;
                }
            }
        }
    }
}
