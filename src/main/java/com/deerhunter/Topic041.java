package com.deerhunter;

/**
 * 给定一个未排序的整数数组，找出其中没有出现的最小的正整数。
 * <p>
 * 示例 1:
 * <p>
 * 输入: [1,2,0]
 * 输出: 3
 * 示例 2:
 * <p>
 * 输入: [3,4,-1,1]
 * 输出: 2
 * 示例 3:
 * <p>
 * 输入: [7,8,9,11,12]
 * 输出: 1
 * 说明:
 * <p>
 * 你的算法的时间复杂度应为O(n)，并且只能使用常数级别的空间。
 * <p>
 * <p>
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/first-missing-positive
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * <p>
 * Copyright (c) 2019, deerhunter0837@gmail.com All Rights Reserved.
 *
 * @author xuejunc
 * @date 2019-11-19
 */
public class Topic041 {
    /**
     * 使用0(n)空间
     *
     * @param nums
     * @return
     */
    public int firstMissingPositive(int[] nums) {
        boolean[] arr = new boolean[nums.length + 1];
        for (int i = 0; i < nums.length; i++) {
            int num = nums[i];
            if (num > 0 && num < arr.length) {
                arr[num] = true;
            }
        }
        for (int i = 1; i < arr.length; i++) {
            if (!arr[i]) {
                return i;
            }
        }
        return nums.length + 1;
    }

    /**
     * 参考官方题解写的解法。
     * 区别在于这里利用了原数组来标记数字是否存在，使用了常数的空间
     *
     * @param nums
     * @return
     */
    public int firstMissingPositive2(int[] nums) {
        int N = nums.length;
        boolean containsOne = false;
        for (int num : nums) {
            if (num == 1) {
                containsOne = true;
            }
        }
        if (!containsOne) {
            return 1;
        }

        for (int i = 0; i < N; i++) {
            int num = nums[i];
            if (num <= 0 || num > N) {
                nums[i] = 1;
            }
        }
        for (int i = 0; i < N; i++) {
            int num = Math.abs(nums[i]);
            nums[num - 1] = -Math.abs(nums[num - 1]);
        }
        for (int i = 0; i < N; i++) {
            if (nums[i] > 0) {
                return i + 1;
            }
        }
        return N + 1;
    }
}
