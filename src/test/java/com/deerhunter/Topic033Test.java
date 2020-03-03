package com.deerhunter;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Oops, forgot to write comment. Good luck, bro.
 * <p>
 * Copyright (c) 2019, deerhunter0837@gmail.com All Rights Reserved.
 *
 * @author xuejunc
 * @date 2019-09-17
 */
class Topic033Test {

    @Test
    void search() {
        int[] nums = {4, 5, 6, 7, 0, 1, 2};
        assertEquals(4, Topic033.search(nums, 0));
        assertEquals(-1, Topic033.search(nums, 3));
        nums = new int[]{1, 3, 5};
        assertEquals(0, Topic033.search(nums, 1));
        nums = new int[]{3, 5, 1};
        assertEquals(0, Topic033.search(nums, 3));
        nums = new int[]{5, 1, 3};
        assertEquals(0, Topic033.search(nums, 5));
        nums = new int[]{4,5,6,7,8,1,2,3};
        assertEquals(4, Topic033.search(nums, 8));
    }
    @Test
    void search2() {
        int[] nums = {4, 5, 6, 7, 0, 1, 2};
        assertEquals(4, Topic033.search2(nums, 0));
        assertEquals(-1, Topic033.search2(nums, 3));
        nums = new int[]{1, 3, 5};
        assertEquals(0, Topic033.search2(nums, 1));
        nums = new int[]{3, 5, 1};
        assertEquals(0, Topic033.search2(nums, 3));
        nums = new int[]{5, 1, 3};
        assertEquals(0, Topic033.search2(nums, 5));
        nums = new int[]{4,5,6,7,8,1,2,3};
        assertEquals(4, Topic033.search2(nums, 8));
    }

    @Test
    void findPivot() {
        int[] nums = {4, 5, 6, 7, 0, 1, 2};
        assertEquals(4, Topic033.findPivot(nums));

        nums = new int[]{5, 1, 3};
        assertEquals(1, Topic033.findPivot(nums));
    }
}
