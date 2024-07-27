package com.deerhunter.offer;

/**
 * 剑指 Offer 39. 数组中出现次数超过一半的数字
 * 数组中有一个数字出现的次数超过数组长度的一半，请找出这个数字。
 * <p>
 * <p>
 * <p>
 * 你可以假设数组是非空的，并且给定的数组总是存在多数元素。
 * <p>
 * <p>
 * <p>
 * 示例 1:
 * <p>
 * 输入: [1, 2, 3, 2, 2, 2, 5, 4, 2]
 * 输出: 2
 * <p>
 * <p>
 * 限制：
 * <p>
 * 1 <= 数组长度 <= 50000
 * <p>
 * <p>
 * <p>
 * 注意：本题与主站 169 题相同：https://leetcode-cn.com/problems/majority-element/
 * <p>
 * <p>
 * <p>
 * 通过次数226,717提交次数324,403
 * <p>
 * Copyright (c) @2022 deerhunter0837@gmail.com All Rights Reserved.
 *
 * @author xuejunc
 * @createTime 2022/5/2 下午7:00
 */
public class Offer039 {
    public int majorityElement(int[] nums) {
        int cur = 0, count = 0;
        for (int num : nums) {
            if (count == 0) {
                cur = num;
                count = 1;
            } else if (num == cur) {
                count++;
            } else {
                count--;
            }
        }
        return cur;
    }
}
