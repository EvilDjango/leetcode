package com.deerhunter;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Oops, forgot to write comment. Good luck, bro.
 * <p>
 * Copyright (c) 2019, deerhunter0837@gmail.com All Rights Reserved.
 *
 * @author xuejunc
 * @date 2019-08-30
 */
class Solution016Test {
    private Solution016 solution = new Solution016();

    @Test
    void threeSumClosest1() {
        assertEquals(2, solution.threeSumClosest1(new int[]{-1,2,1, -4},1));
    }

    @Test
    void threeSumClosest() {
//        assertEquals(2, solution.threeSumClosest(new int[]{-1,2,1, -4},1));
        assertEquals(0, solution.threeSumClosest(new int[]{0,2,1,-3},1));
    }
}