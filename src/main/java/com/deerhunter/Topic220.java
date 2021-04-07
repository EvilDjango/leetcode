package com.deerhunter;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

/**
 * 220. 存在重复元素 III
 * 在整数数组 nums 中，是否存在两个下标 i 和 j，使得 nums [i] 和 nums [j] 的差的绝对值小于等于 t ，且满足 i 和 j 的差的绝对值也小于等于 ķ 。
 * <p>
 * 如果存在则返回 true，不存在返回 false。
 * <p>
 * <p>
 * <p>
 * 示例 1:
 * <p>
 * 输入: nums = [1,2,3,1], k = 3, t = 0
 * 输出: true
 * 示例 2:
 * <p>
 * 输入: nums = [1,0,1,1], k = 1, t = 2
 * 输出: true
 * 示例 3:
 * <p>
 * 输入: nums = [1,5,9,1,5,9], k = 2, t = 3
 * 输出: false
 * <p>
 * Copyright (c) 20120 deerhunter0837@gmail.com All Rights Reserved.
 *
 * @author xuejunc
 * @createTime 2021/4/6 16:58
 */
public class Topic220 {
    public static class Solution1 {
        public boolean containsNearbyAlmostDuplicate(int[] nums, int k, int t) {
            if (t < k) {
                return hash(nums, k, t);

            }
            return method2(nums, k, t);
        }

        /**
         * 滑动窗口依次比较
         *
         * @param nums
         * @param k
         * @param t
         * @return
         */
        private boolean method2(int[] nums, int k, int t) {
            for (int i = 0; i < nums.length; i++) {
                for (int j = Math.max(0, i - k); j < i; j++) {
                    if (Math.abs((long) nums[i] - nums[j]) <= t) {
                        return true;
                    }
                }
            }
            return false;
        }

        /**
         * 使用哈希表
         *
         * @param nums
         * @param k
         * @param t
         * @return
         */
        private boolean hash(int[] nums, int k, int t) {
            Map<Long, Integer> count = new HashMap<>();
            int span = 0;
            for (int i = 0; i < nums.length; i++) {
                if (count.containsKey((long) nums[i])) {
                    return true;
                }
                for (long j = (long) nums[i] - t; j <= (long) nums[i] + t; j++) {
                    count.merge(j, 1, Integer::sum);
                }
                span++;
                if (span > k) {
                    for (long j = (long) nums[i - k] - t; j <= (long) nums[i - k] + t; j++) {
                        count.merge(j, -1, Integer::sum);
                        if (count.get(j) == 0) {
                            count.remove(j);
                        }
                    }
                }
            }
            return false;
        }
    }

    /**
     * 平衡二叉搜索树
     */
    public static class Solution2 {
        public boolean containsNearbyAlmostDuplicate(int[] nums, int k, int t) {
            TreeSet<Integer> set = new TreeSet<>();
            for (int i = 0; i < nums.length; i++) {
                Integer next = set.ceiling(nums[i]);
                if (next != null && next <= (long) nums[i] + t) {
                    return true;
                }
                Integer prev = set.floor(nums[i]);
                if (prev != null && prev >= (long) nums[i] - t) {
                    return true;
                }
                set.add(nums[i]);
                if (set.size() > k) {
                    set.remove(nums[i - k]);
                }
            }
            return false;
        }
    }

    /**
     * 桶
     */
    public static class Solution3 {
        public boolean containsNearbyAlmostDuplicate(int[] nums, int k, int t) {
            if (t < 0) {
                return false;
            }
            Map<Long, Integer> buckets = new HashMap<>();
            for (int i = 0; i < nums.length; i++) {
                long id = getBucketId(nums[i], (long) t + 1);
                if (buckets.containsKey(id)) {
                    return true;
                } else if (buckets.containsKey(id - 1) && Math.abs((long) buckets.get(id - 1) - nums[i]) <= t) {
                    return true;
                } else if (buckets.containsKey(id + 1) && Math.abs((long) buckets.get(id + 1) - nums[i]) <= t) {
                    return true;
                }
                buckets.put(id, nums[i]);
                if (buckets.size() > k) {
                    buckets.remove(getBucketId(nums[i - k], t + 1));
                }
            }
            return false;
        }

        private long getBucketId(long num, long step) {
            return num < 0 ? (num + 1) / step - 1 : num / step;
        }
    }
}
