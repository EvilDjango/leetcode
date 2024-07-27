package com.deerhunter.offer;

/**
 * 剑指 Offer 56 - I. 数组中数字出现的次数
 * 一个整型数组 nums 里除两个数字之外，其他数字都出现了两次。请写程序找出这两个只出现一次的数字。要求时间复杂度是O(n)，空间复杂度是O(1)。
 * <p>
 * <p>
 * <p>
 * 示例 1：
 * <p>
 * 输入：nums = [4,1,4,6]
 * 输出：[1,6] 或 [6,1]
 * 示例 2：
 * <p>
 * 输入：nums = [1,2,10,4,1,4,3,3]
 * 输出：[2,10] 或 [10,2]
 * <p>
 * <p>
 * 限制：
 * <p>
 * 2 <= nums.length <= 10000
 * <p>
 * <p>
 * 通过次数151,800提交次数219,128
 * <p>
 * Copyright (c) @2022 deerhunter0837@gmail.com All Rights Reserved.
 *
 * @author xuejunc
 * @createTime 2022/4/25 下午2:33
 */
public class Offer056A {
    /**
     * 分组异或
     *
     * @param nums
     * @return
     */
    public int[] singleNumbers(int[] nums) {
        // 异或结果
        int xor = 0;
        for (int num : nums) {
            xor ^= num;
        }
        // 异或结果的最低位的1
        int diff = (xor ^ (xor & xor - 1));
        int groupA = 0, groupB = 0;
        for (int num : nums) {
            if ((diff & num) > 0) {
                groupA ^= num;
            } else {
                groupB ^= num;
            }
        }
        return new int[]{groupA, groupB};
    }
}
