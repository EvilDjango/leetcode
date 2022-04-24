package com.deerhunter;

import java.util.HashMap;
import java.util.Map;

/**
 * 剑指 Offer 56 - II. 数组中数字出现的次数 II
 * 在一个数组 nums 中除一个数字只出现一次之外，其他数字都出现了三次。请找出那个只出现一次的数字。
 * <p>
 * <p>
 * <p>
 * 示例 1：
 * <p>
 * 输入：nums = [3,4,3,3]
 * 输出：4
 * 示例 2：
 * <p>
 * 输入：nums = [9,1,7,9,7,9,7]
 * 输出：1
 * <p>
 * <p>
 * 限制：
 * <p>
 * 1 <= nums.length <= 10000
 * 1 <= nums[i] < 2^31
 * <p>
 * <p>
 * 通过次数123,541提交次数153,488
 * <p>
 * Copyright (c) @2022 deerhunter0837@gmail.com All Rights Reserved.
 *
 * @author xuejunc
 * @createTime 2022/4/24 下午10:53
 */
public class Offer056B {
    /**
     * 暴力法，直接用字典
     *
     * @param nums
     * @return
     */
    public int singleNumber(int[] nums) {
        Map<Integer, Integer> count = new HashMap<>();
        for (int num : nums) {
            count.merge(num, 1, Integer::sum);
        }
        for (Map.Entry<Integer, Integer> entry : count.entrySet()) {
            if (entry.getValue() == 1) {
                return entry.getKey();
            }
        }
        return 0;
    }

    /**
     * 位运算，分别统计每一个二进制位的1的数量n，n%3==0那么目标数在这意味的值为0，否则为1.
     *
     * @param nums
     * @return
     */
    public int singleNumber2(int[] nums) {
        int target = 0;
        int base = 1;
        for (int i = 0; i < 31; i++) {
            int count = 0;
            for (int num : nums) {
                if ((base & num) > 0) {
                    count++;
                }
            }
            if (count % 3 != 0) {
                target |= base;
            }
            base <<= 1;
        }
        return target;
    }

    /**
     * 状态机，参考题解 https://leetcode-cn.com/problems/shu-zu-zhong-shu-zi-chu-xian-de-ci-shu-ii-lcof/solution/mian-shi-ti-56-ii-shu-zu-zhong-shu-zi-chu-xian-d-4/
     *
     * @param nums
     * @return
     */
    public int singleNumber3(int[] nums) {
        int ones = 0, twos = 0;
        for (int num : nums) {
            ones = ones ^ num & ~twos;
            twos = twos ^ num & ~ones;
        }
        return ones;
    }

}
