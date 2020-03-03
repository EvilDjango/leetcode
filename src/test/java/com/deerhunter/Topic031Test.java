package com.deerhunter;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Oops, forgot to write comment. Good luck, bro.
 * <p>
 * Copyright (c) 2019, deerhunter0837@gmail.com All Rights Reserved.
 *
 * @author xuejunc
 * @date 2019-09-05
 */
class Topic031Test {

    @Test
    void nextPermutation() {
        int[] nums1 = {1, 2, 3};
        Topic031.nextPermutation(nums1);
        assertArrayEquals(new int[]{1, 3, 2}, nums1);

        int[] nums2 = {3, 2, 1};
        Topic031.nextPermutation(nums2);
        assertArrayEquals(new int[]{1, 2, 3}, nums2);
        int[] nums3 = {1, 1, 5};
        Topic031.nextPermutation(nums3);
        assertArrayEquals(new int[]{1, 5, 1}, nums3);

    }
}
