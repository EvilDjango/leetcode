package com.deerhunter;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Oops, forgot to write comment. Good luck, bro.
 * <p>
 * Copyright (c) 2019, deerhunter0837@gmail.com All Rights Reserved.
 *
 * @author xuejunc
 * @date 2019-08-28
 */
class Topic011Test {
    Topic011 solution = new Topic011();

    @Test
    void maxArea() {
        assertEquals(49,solution.maxArea(new int[]{1,8,6,2,5,4,8,3,7}));
    }

    @Test
    void maxArea2() {
        assertEquals(49,solution.maxArea2(new int[]{1,8,6,2,5,4,8,3,7}));
    }
}
