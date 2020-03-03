package com.deerhunter;

import com.deerhunter.common.Utils;

/**
 * Oops, forgot to write comment. Good luck, bro.
 * <p>
 * Copyright (c) 2019, deerhunter0837@gmail.com All Rights Reserved.
 *
 * @author xuejunc
 * @date 2019-09-17
 */
public class Topic034 {
    public static int[] searchRange(int[] nums, int target) {
        if (nums == null || nums.length == 0) {
            return new int[]{-1, -1};
        }

        int lowerBound = Utils.lowerBound(nums, target);
        int upperBound = Utils.upperBound(nums, target);
        int first = lowerBound < nums.length && nums[lowerBound] == target ? lowerBound : -1;
        int last = upperBound - 1 >= 0 && nums[upperBound - 1] == target ? upperBound - 1 : -1;
        return new int[]{first, last};
    }
}
