package com.deerhunter.common;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Oops, forgot to write comment. Good luck, bro.
 * <p>
 * Copyright (c) 2019, deerhunter0837@gmail.com All Rights Reserved.
 *
 * @author xuejunc
 * @date 2019-09-01
 */
class UtilsTest {
    private static final int[] nums = new int[]{1, 2, 2, 2, 3, 4, 5};

    @Test
    void createLinkList() {
    }

    @Test
    void lowerBound() {
        assertEquals(1, Utils.lowerBound(nums, 2));

    }

    @Test
    void upperBound() {
        assertEquals(4, Utils.upperBound(nums, 2));
    }

    @Test
    void binarySearch() {
        assertEquals(1, Utils.binarySearch(nums, 2));
        assertEquals(-1, Utils.binarySearch(nums, 0));
        assertEquals(-1, Utils.binarySearch(nums, 10));
        assertEquals(0, Utils.binarySearch(nums, 1));
    }
}