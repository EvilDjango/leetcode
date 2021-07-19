package com.deerhunter;

import java.util.ArrayList;
import java.util.List;

/**
 * 163. 缺失的区间
 * 给定一个排序的整数数组 nums ，其中元素的范围在 闭区间 [lower, upper] 当中，返回不包含在数组中的缺失区间。
 * <p>
 * 示例：
 * <p>
 * 输入: nums = [0, 1, 3, 50, 75], lower = 0 和 upper = 99,
 * 输出: ["2", "4->49", "51->74", "76->99"]
 * 通过次数7,771提交次数24,649
 * <p>
 * Copyright (c) @2021 deerhunter0837@gmail.com All Rights Reserved.
 *
 * @author xuejunc
 * @createTime 7/19/21 2:02 PM
 */
public class Topic163 {
    public static class Solution {
        public List<String> findMissingRanges(int[] nums, int lower, int upper) {
            List<String> ans = new ArrayList<>();
            int prev = lower - 1;
            for (int num : nums) {
                if (num != prev + 1) {
                    ans.add(buildInterval(prev, num));
                }
                prev = num;
            }
            if (upper + 1 > prev + 1) {
                ans.add(buildInterval(prev, upper + 1));
            }
            return ans;
        }

        private String buildInterval(int left, int right) {
            return left + 2 == right ? left + 1 + "" : left + 1 + "->" + (right - 1);
        }
    }
}
