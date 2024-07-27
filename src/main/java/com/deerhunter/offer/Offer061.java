package com.deerhunter.offer;

import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;

/**
 * 剑指 Offer 61. 扑克牌中的顺子
 * 从若干副扑克牌中随机抽 5 张牌，判断是不是一个顺子，即这5张牌是不是连续的。2～10为数字本身，A为1，J为11，Q为12，K为13，而大、小王为 0 ，可以看成任意数字。A 不能视为 14。
 * <p>
 * <p>
 * <p>
 * 示例 1:
 * <p>
 * 输入: [1,2,3,4,5]
 * 输出: True
 * <p>
 * <p>
 * 示例 2:
 * <p>
 * 输入: [0,0,1,2,5]
 * 输出: True
 * <p>
 * <p>
 * 限制：
 * <p>
 * 数组长度为 5
 * <p>
 * 数组的数取值为 [0, 13] .
 * <p>
 * 通过次数150,433提交次数326,755
 * <p>
 * Copyright (c) @2022 deerhunter0837@gmail.com All Rights Reserved.
 *
 * @author xuejunc
 * @createTime 2022/4/21 下午2:27
 */
public class Offer061 {
    /**
     * 先排序
     *
     * @param nums
     * @return
     */
    public boolean isStraight(int[] nums) {
        Arrays.sort(nums);
        int zero = 0;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] == 0) {
                zero++;
                continue;
            }
            if (i == 0 || nums[i - 1] == 0) {
                continue;
            }
            if (nums[i] == nums[i - 1]) {
                return false;
            }
            zero -= nums[i] - nums[i - 1] - 1;
        }
        return zero >= 0;
    }

    /**
     * 参考优秀题解。在不重复的前提下，最大值和最小值小于5即可成为顺子
     *
     * @param nums
     * @return
     */
    public boolean isStraight2(int[] nums) {
        int[] count = new int[14];
        int max = 0;
        int min = 14;
        for (int num : nums) {
            if (num == 0) {
                continue;
            }
            if (count[num] > 0) {
                return false;
            }
            min = Math.min(min, num);
            max = Math.max(max, num);
            count[num]++;
        }
        return max - min < 5;
    }
}
