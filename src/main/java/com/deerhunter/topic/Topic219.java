package com.deerhunter.topic;

import java.util.HashMap;
import java.util.Map;

/**
 * 219. 存在重复元素 II
 * 给定一个整数数组和一个整数 k，判断数组中是否存在两个不同的索引 i 和 j，使得 nums [i] = nums [j]，并且 i 和 j 的差的 绝对值 至多为 k。
 * <p>
 * <p>
 * <p>
 * 示例 1:
 * <p>
 * 输入: nums = [1,2,3,1], k = 3
 * 输出: true
 * 示例 2:
 * <p>
 * 输入: nums = [1,0,1,1], k = 1
 * 输出: true
 * 示例 3:
 * <p>
 * 输入: nums = [1,2,3,1,2,3], k = 2
 * 输出: false
 * <p>
 * Copyright (c) 20120 deerhunter0837@gmail.com All Rights Reserved.
 *
 * @author xuejunc
 * @createTime 2021/4/6 15:32
 */
public class Topic219 {
    public static class Solution {
        public boolean containsNearbyDuplicate(int[] nums, int k) {
            Map<Integer, Integer> count = new HashMap<>();
            int span = 0;
            for (int i = 0; i < nums.length; i++) {
                if (span >= k + 1) {
                    count.merge(nums[i - k - 1], -1, Integer::sum);
                }
                span++;
                count.merge(nums[i], 1, Integer::sum);
                if (count.get(nums[i]) > 1) {
                    return true;
                }
            }
            return false;
        }
    }
}
