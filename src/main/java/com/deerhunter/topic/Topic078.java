package com.deerhunter.topic;

import com.deerhunter.common.ListNode;

import java.util.ArrayList;
import java.util.List;

/**
 * 给定一组不含重复元素的整数数组 nums，返回该数组所有可能的子集（幂集）。
 * <p>
 * 说明：解集不能包含重复的子集。
 * <p>
 * 示例:
 * <p>
 * 输入: nums = [1,2,3]
 * 输出:
 * [
 * [3],
 *   [1],
 *   [2],
 *   [1,2,3],
 *   [1,3],
 *   [2,3],
 *   [1,2],
 *   []
 * ]
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/subsets
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * <p>
 * Copyright (c) 2019, deerhunter0837@gmail.com All Rights Reserved.
 *
 * @author xuejunc
 * @date 2020-03-29
 */
public class Topic078 {
    /**
     * 使用了和Topic77类似的思路：使用二级制掩码。实际上，这里按照不同的子集长度，分别去列举各种掩码的情况时，走了弯路。因为求幂集实际只
     * 需要穷尽所有掩码即可，无需分组来推导。参见Solution2
     */
    public static class Solution1 {
        public List<List<Integer>> subsets(int[] nums) {
            List<List<Integer>> subSets = new ArrayList<>();
            subSets.add(new ArrayList<>());
            for (int count = 1; count <= nums.length; count++) {
                findSubSets(nums, count, subSets);
            }
            return subSets;
        }

        private void findSubSets(int[] nums, int count, List<List<Integer>> results) {
            int[] indexes = new int[count + 1];
            for (int i = 0; i < count; i++) {
                indexes[i] = i;
            }
            indexes[count] = nums.length;
            int j = 0;
            while (j < count) {
                List<Integer> combine = new ArrayList<>();
                for (int i = 0; i < count; i++) {
                    combine.add(nums[indexes[i]]);
                }
                results.add(combine);

                j = 0;

                while (j < count && indexes[j] + 1 == indexes[j + 1]) {
                    indexes[j] = j++;
                }
                indexes[j]++;
            }
        }
    }

    /**
     * 位掩码算法
     */
    public static class Solution2 {
        public List<List<Integer>> subsets(int[] nums) {
            List<List<Integer>> subsets = new ArrayList<>();
            int n = nums.length;
            for (long i = 0; i < Math.pow(2, n); i++) {
                List<Integer> subset = new ArrayList<>();
                long bitMask = i;
                for (int j = 0; j < n; j++) {
                    if ((bitMask & 1) == 1) {
                        subset.add(nums[j]);
                    }
                    bitMask >>= 1;
                }
                subsets.add(subset);
            }
            return subsets;
        }
    }

    /**
     * 递归法
     */
    public static class Solution3 {
        public List<List<Integer>> subsets(int[] nums) {
            List<List<Integer>> subsets = new ArrayList<>();
            subsets.add(new ArrayList<>());
            for (int i = 0; i < nums.length; i++) {
                List<List<Integer>> newSubsets = new ArrayList<>();
                for (List<Integer> subset : subsets) {
                    ArrayList<Integer> newSubset = new ArrayList<>(subset);
                    newSubset.add(nums[i]);
                    newSubsets.add(newSubset);
                }
                subsets.addAll(newSubsets);
            }
            return subsets;
        }
    }

    /**
     * 回溯法
     */
    public static class Solution4 {
        public List<List<Integer>> subsets(int[] nums) {
            List<List<Integer>> subsets = new ArrayList<>();
            for (int i = 0; i <= nums.length; i++) {
                findSubsets(i, 0, nums, subsets, new ArrayList<>());
            }
            return subsets;
        }

        private void findSubsets(int count, int curr, int[] nums, List<List<Integer>> subsets, List<Integer> subset) {
            if (subset.size() == count) {
                subsets.add(new ArrayList<>(subset));
                return;
            }
            for (int i = curr; i < nums.length; i++) {
                subset.add(nums[i]);
                findSubsets(count, i + 1, nums, subsets, subset);
                subset.remove(subset.size() - 1);
            }
        }
    }
}
