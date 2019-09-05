package com.deerhunter.common;

import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Oops, forgot to write comment. Good luck, bro.
 * <p>
 * Copyright (c) 2019, deerhunter0837@gmail.com All Rights Reserved.
 *
 * @author xuejunc
 * @date 2019-09-04
 */
class Solution026Test {

    @Test
    void removeDuplicates() {
        int[] nums = {1, 1, 2};
        int len = Solution026.removeDuplicates(nums);
        assertTrue(Arrays.equals(new int[]{1, 2}, Arrays.copyOf(nums, len)));

        nums = new int[]{0, 0, 1, 1, 1, 2, 2, 3, 3, 4};
        len = Solution026.removeDuplicates(nums);
        assertTrue(Arrays.equals(new int[]{0, 1, 2, 3, 4}, Arrays.copyOf(nums, len)));

    }

    @Test
    void removeDuplicates2() {
        int[] nums = {1, 1, 2};
        int len = Solution026.removeDuplicates2(nums);
        assertTrue(Arrays.equals(new int[]{1, 2}, Arrays.copyOf(nums, len)));

        nums = new int[]{0, 0, 1, 1, 1, 2, 2, 3, 3, 4};
        len = Solution026.removeDuplicates2(nums);
        assertTrue(Arrays.equals(new int[]{0, 1, 2, 3, 4}, Arrays.copyOf(nums, len)));

    }
}