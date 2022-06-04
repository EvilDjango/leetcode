package com.deerhunter;

import java.util.HashSet;
import java.util.Set;

/**
 * 剑指 Offer 03. 数组中重复的数字
 * 找出数组中重复的数字。
 * <p>
 * <p>
 * 在一个长度为 n 的数组 nums 里的所有数字都在 0～n-1 的范围内。数组中某些数字是重复的，但不知道有几个数字重复了，也不知道每个数字重复了几次。请找出数组中任意一个重复的数字。
 * <p>
 * 示例 1：
 * <p>
 * 输入：
 * [2, 3, 1, 0, 2, 5, 3]
 * 输出：2 或 3
 * <p>
 * <p>
 * 限制：
 * <p>
 * 2 <= n <= 100000
 * <p>
 * 通过次数570,168提交次数839,358
 * <p>
 * Copyright (c) @2022 deerhunter0837@gmail.com All Rights Reserved.
 *
 * @author xuejunc
 * @createTime 2022/6/3 下午5:32
 */
public class Offer003 {
    /**
     * 所有元素都属于[0,n-1]，所以用数组来记录出现过的数字
     * @param nums
     * @return
     */
    public int findRepeatNumber(int[] nums) {
        boolean []seen=new boolean[nums.length];
        for (int num : nums) {
            if(seen[num]){
                return num;
            }
            seen[num]=true;
        }
        return 0;
    }

    /**
     * 空间复杂度为O(1)。充分利用了条件所有元素都属于[0,n-1]，因此可以利用原数组来保存已经出现过的数字。
     * 出现了数字k后，将nums[k]-n，那么nums[k]<0，当第二次出现数字k时，观察到nums[k]<0,那么数字k就是一个重复元素。
     * 而我们要去找 nums[i]原本的值也很简单，如果nums[i]大于0，那么显然我们没有修改过其值，如果小于0，那么加上n即为原值。
     *
     * @param nums
     * @return
     */
    public int findRepeatNumber2(int[] nums) {
        int n = nums.length;
        for (int num : nums) {
            if (num < 0) {
                num += n;
            }
            if(nums[num]<0){
                return num;
            }
            nums[num]-=n;
        }
        return -1;
    }
}
