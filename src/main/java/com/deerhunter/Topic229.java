package com.deerhunter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 229. 求众数 II
 * 给定一个大小为 n 的整数数组，找出其中所有出现超过 ⌊ n/3 ⌋ 次的元素。
 * <p>
 * 进阶：尝试设计时间复杂度为 O(n)、空间复杂度为 O(1)的算法解决此问题。
 * <p>
 * <p>
 * <p>
 * 示例 1：
 * <p>
 * 输入：[3,2,3]
 * 输出：[3]
 * 示例 2：
 * <p>
 * 输入：nums = [1]
 * 输出：[1]
 * 示例 3：
 * <p>
 * 输入：[1,1,1,3,3,2,2,2]
 * 输出：[1,2]
 * <p>
 * <p>
 * 提示：
 * <p>
 * 1 <= nums.length <= 5 * 104
 * -109 <= nums[i] <= 109
 * 通过次数31,793提交次数69,220
 * <p>
 * Copyright (c) @2021 deerhunter0837@gmail.com All Rights Reserved.
 *
 * @author xuejunc
 * @createTime 6/29/21 10:52 AM
 */
public class Topic229 {
    /**
     * 傻瓜算法
     */
    public static class Solution1 {
        public List<Integer> majorityElement(int[] nums) {
            int n = nums.length;
            Map<Integer, Integer> count = new HashMap<>();
            for (int num : nums) {
                count.merge(num, 1, Integer::sum);
            }
            List<Integer> ans = new ArrayList<>();
            for (Map.Entry<Integer, Integer> entry : count.entrySet()) {
                if (entry.getValue() > n / 3) {
                    ans.add(entry.getKey());
                }
            }
            return ans;
        }
    }

    /**
     * 摩尔投票法
     */
    public static class Solution2 {
        public List<Integer> majorityElement(int[] nums) {
            List<Integer> ans = new ArrayList<>();
            int n = nums.length;
            if (n < 1) {
                return ans;
            }
            int[][] candidates = new int[2][2];
            for (int num : nums) {
                boolean update = false;
                for (int[] candidate : candidates) {
                    if (candidate[0] == num) {
                        candidate[1]++;
                        update = true;
                        break;
                    }
                }
                if (!update) {
                    for (int[] candidate : candidates) {
                        if (candidate[1] == 0) {
                            candidate[0] = num;
                            candidate[1]++;
                            update = true;
                            break;
                        }
                    }
                }
                if (!update) {
                    for (int[] candidate : candidates) {
                        candidate[1]--;
                    }
                }
            }
            boolean hasCandidates = false;
            for (int[] candidate : candidates) {
                if (candidate[1] != 0) {
                    hasCandidates = true;
                    candidate[1] = 0;
                }
            }
            if (!hasCandidates) {
                return ans;
            }
            for (int num : nums) {
                for (int[] candidate : candidates) {
                    if (candidate[0] == num) {
                        candidate[1]++;
                        break;
                    }
                }
            }
            for (int[] candidate : candidates) {
                if (candidate[1] > n / 3) {
                    ans.add(candidate[0]);
                }
            }
            return ans;
        }
    }
}
