package com.deerhunter.topic;

import java.util.HashMap;
import java.util.Map;

/**
 * 169. 多数元素
 * 给定一个大小为 n 的数组，找到其中的多数元素。多数元素是指在数组中出现次数大于 ⌊ n/2 ⌋ 的元素。
 * <p>
 * 你可以假设数组是非空的，并且给定的数组总是存在多数元素。
 * <p>
 * <p>
 * <p>
 * 示例 1:
 * <p>
 * 输入: [3,2,3]
 * 输出: 3
 * 示例 2:
 * <p>
 * 输入: [2,2,1,1,1,2,2]
 * 输出: 2
 * <p>
 * Copyright (c) 20120 deerhunter0837@gmail.com All Rights Reserved.
 *
 * @author xuejunc
 * @createTime 2020/12/25 11:29
 */
public class Topic169 {
    public static class Solution1 {
        public int majorityElement(int[] nums) {
            Map<Integer, Integer> count = new HashMap<>();
            for (int num : nums) {
                count.merge(num, 1, Integer::sum);
                if (count.get(num) > nums.length / 2) {
                    return num;
                }
            }
            return 0;
        }
    }

    /**
     * Boyer-Moore投票算法
     */
    public static class Solution2 {
        public int majorityElement(int[] nums) {
            int candidate = 0;
            int count = 0;
            for (int num : nums) {
                if (count == 0) {
                    candidate = num;
                }
                count += num == candidate ? 1 : -1;
            }
            return candidate;
        }
    }
}
