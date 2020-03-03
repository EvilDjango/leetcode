package com.deerhunter;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

/**
 * 给定一个包含 n 个整数的数组 nums 和一个目标值 target，判断 nums 中是否存在四个元素 a，b，c 和 d ，使得 a + b + c + d 的值与 target 相等？找出所有满足条件且不重复的四元组。
 * <p>
 * 注意：
 * <p>
 * 答案中不可以包含重复的四元组。
 * <p>
 * 示例：
 * <p>
 * 给定数组 nums = [1, 0, -1, 0, -2, 2]，和 target = 0。
 * <p>
 * 满足要求的四元组集合为：
 * [
 * [-1,  0, 0, 1],
 * [-2, -1, 1, 2],
 * [-2,  0, 0, 2]
 * ]
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/4sum
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * <p>
 * Copyright (c) 2019, deerhunter0837@gmail.com All Rights Reserved.
 *
 * @author xuejunc
 * @date 2019-08-30
 */
public class Topic018 {
    /**
     * 递归解法，参考了别人的题解
     *
     * @param nums
     * @param target
     * @return
     */
    public List<List<Integer>> fourSum3(int[] nums, int target) {
        return KSum.kSum(nums, 4, target);
    }

    /**
     * 参考三数之和解法，复杂度为n3
     * 先确定第一个数，剩下的就是三数之和问题了。基本上可以说是照搬。
     *
     * @param nums
     * @param target
     * @return
     */
    public List<List<Integer>> fourSum2(int[] nums, int target) {
        List<List<Integer>> ret = new LinkedList<>();
        if (nums == null || nums.length < 4) {
            return ret;
        }
        Arrays.sort(nums);
        int len = nums.length;
        for (int i = 0; i < len; i++) {
            if (i > 0 && nums[i - 1] == nums[i]) {
                continue;
            }
            int complement = target - nums[i];
            for (int j = i + 1; j < len; j++) {
                if (j > i + 1 && nums[j - 1] == nums[j]) {
                    continue;
                }
                int left = j + 1;
                int right = len - 1;
                while (left < right) {
                    if (left > j + 1 && nums[left - 1] == nums[left]) {
                        left++;
                        continue;
                    }
                    if (right + 1 < len && nums[right + 1] == nums[right]) {
                        right--;
                        continue;
                    }
                    int sum = nums[j] + nums[left] + nums[right];
                    if (sum > complement) {
                        right--;
                    } else if (sum < complement) {
                        left++;
                    } else {
                        ret.add(Arrays.asList(nums[i], nums[j], nums[left], nums[right]));
                        left++;
                        right--;
                    }
                }
            }
        }
        return ret;
    }

    /**
     * 自己写的算法，复杂度为logn*n3
     *
     * @param nums
     * @param target
     * @return
     */
    public List<List<Integer>> fourSum(int[] nums, int target) {
        List<List<Integer>> ret = new LinkedList<>();
        if (nums == null || nums.length < 4) {
            return ret;
        }
        Arrays.sort(nums);
        int len = nums.length;
        for (int i = 0; i < len; i++) {
            // 去重
            if (i > 0 && nums[i - 1] == nums[i]) {
                continue;
            }
            for (int j = i + 1; j < len; j++) {
                if (j > i + 1 && nums[j - 1] == nums[j]) {
                    continue;
                }
                for (int k = j + 2; k < len; k++) {
                    if (k + 1 < len && nums[k + 1] == nums[k]) {
                        continue;
                    }
                    int complement = target - nums[i] - nums[j] - nums[k];
                    int mid = binarySearch(nums, j, k, complement);
                    if (mid != -1) {
                        ret.add(Arrays.asList(nums[i], nums[j], nums[mid], nums[k]));
                    }
                }
            }
        }
        return ret;
    }

    /**
     * 二分查找
     *
     * @param nums
     * @param left   左边界（不包含）
     * @param right  右边界（不包含）
     * @param target
     * @return
     */
    private int binarySearch(int[] nums, int left, int right, int target) {
        int l = left + 1;
        int r = right - 1;
        while (l <= r) {
            int mid = (l + r) / 2;
            if (nums[mid] > target) {
                r = mid - 1;
            } else if (nums[mid] < target) {
                l = mid + 1;
            } else {
                return mid;
            }
        }
        return -1;
    }
}
