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
    private static final int[] nums2 = new int[]{5, 4, 3, 2, 2, 2, 1};

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

    @Test
    void reversedLowerBound() {
        assertEquals(5, Utils.reversedLowerBound(nums2, 2));
    }

    @Test
    void reversedUpperBound() {
        assertEquals(2, Utils.reversedUpperBound(nums2, 2, 0, nums.length));
        assertEquals(0, Utils.reversedUpperBound(new int[]{3}, 2,0, nums.length ));

    }

    @Test
    void factorial() {
        assertEquals(1,Utils.factorial(0));
        assertEquals(1,Utils.factorial(1));
        assertEquals(2,Utils.factorial(2));
        assertEquals(6,Utils.factorial(3));
    }

    @Test
    void getBinary() {
        assertEquals("0",Utils.getBinary(0));
        assertEquals("1",Utils.getBinary(1));
        assertEquals("11",Utils.getBinary(3));
        assertEquals("100",Utils.getBinary(4));
        assertEquals("101",Utils.getBinary(5));
        assertEquals("1010",Utils.getBinary(10));
    }

    @Test
    void getBinary2() {
        assertEquals("0",Utils.getBinary2(0));
        assertEquals("1",Utils.getBinary2(1));
        assertEquals("11",Utils.getBinary2(3));
        assertEquals("100",Utils.getBinary(4));
        assertEquals("101",Utils.getBinary2(5));
        assertEquals("1010",Utils.getBinary2(10));
    }
}
