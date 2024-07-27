package com.deerhunter.topic;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 给定一个可能包含重复元素的整数数组 nums，返回该数组所有可能的子集（幂集）。
 * <p>
 * 说明：解集不能包含重复的子集。
 * <p>
 * 示例:
 * <p>
 * 输入: [1,2,2]
 * 输出:
 * [
 * [2],
 * [1],
 * [1,2,2],
 * [2,2],
 * [1,2],
 * []
 * ]
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/subsets-ii
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * <p>
 * Copyright (c) 2019, deerhunter0837@gmail.com All Rights Reserved.
 *
 * @author xuejunc
 * @createTime 2020/4/13 17:19
 */
public class Topic090 {
    public static class Solution1 {
        public List<List<Integer>> subsetsWithDup(int[] nums) {
            Arrays.sort(nums);
            List<List<Integer>> ans = new ArrayList<>();
            findSubsets(nums, 0, new boolean[nums.length], ans);
            return ans;
        }

        private void findSubsets(int[] nums, int i, boolean[] used, List<List<Integer>> ans) {
            if (i == nums.length) {
                List<Integer> subset = new ArrayList<>();
                for (int j = 0; j < used.length; j++) {
                    if (used[j]) {
                        subset.add(nums[j]);
                    }
                }
                ans.add(subset);
                return;
            }

            // 如果当前元素和前面的元素重复，并且前面的元素没有选择的时候，当前元素也不能选择，否则会导致重复
            if (i == 0 || nums[i] != nums[i - 1] || used[i - 1]) {
                used[i] = true;
                findSubsets(nums, i + 1, used, ans);
                used[i] = false;
            }
            findSubsets(nums, i + 1, used, ans);
        }
    }
}
