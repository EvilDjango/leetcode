package com.deerhunter;

/**
 * 给定一个非空整数数组，除了某个元素只出现一次以外，其余每个元素均出现了三次。找出那个只出现了一次的元素。
 * <p>
 * 说明：
 * <p>
 * 你的算法应该具有线性时间复杂度。 你可以不使用额外空间来实现吗？
 * <p>
 * 示例 1:
 * <p>
 * 输入: [2,2,3,2]
 * 输出: 3
 * 示例 2:
 * <p>
 * 输入: [0,1,0,1,0,1,99]
 * 输出: 99
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/single-number-ii
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * <p>
 * Copyright (c) 20120 deerhunter0837@gmail.com All Rights Reserved.
 *
 * @author xuejunc
 * @createTime 2020/10/22 17:47
 */
public class Topic137 {
    /**
     * 有限状态自动机
     */
    public static class Solution1 {
        public int singleNumber(int[] nums) {
            int seenOnce = 0, seenTwice = 0;

            for (int num : nums) {
                seenOnce = ~seenTwice & (seenOnce ^ num);
                seenTwice = ~seenOnce & (seenTwice ^ num);
            }

            return seenOnce;
        }
    }

    /**
     * 按位统计
     */
    public static class Solution2 {
        public int singleNumber(int[] nums) {
            int[] counts = new int[32];
            for (int num : nums) {
                for (int i = 0; i < 32; i++) {
                    counts[i] += num & 1;
                    num >>= 1;
                }
            }
            int ans = 0;
            for (int i = 31; i >= 0; i--) {
                ans <<= 1;
                ans += counts[i] % 3;
            }

            return ans;
        }
    }
}
