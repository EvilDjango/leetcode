package com.deerhunter;

import com.deerhunter.topic.Topic041;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Oops, forgot to write comment. Good luck, bro.
 * <p>
 * Copyright (c) 2019, deerhunter0837@gmail.com All Rights Reserved.
 *
 * @author xuejunc
 * @date 2019-11-19
 */
class Topic041Test {

    @Test
    void firstMissingPositive() {
        Topic041 solution = new Topic041();
        assertEquals(3, solution.firstMissingPositive(new int[]{1, 2, 0}));
        assertEquals(2, solution.firstMissingPositive(new int[]{3,4,-1,1}));
        assertEquals(1, solution.firstMissingPositive(new int[]{7,8,9,11,12}));
        assertEquals(2, solution.firstMissingPositive(new int[]{1}));

    }

    @Test
    void firstMissingPositive2() {
        Topic041 solution = new Topic041();
        assertEquals(3, solution.firstMissingPositive2(new int[]{1, 2, 0}));
        assertEquals(2, solution.firstMissingPositive2(new int[]{3,4,-1,1}));
        assertEquals(1, solution.firstMissingPositive2(new int[]{7,8,9,11,12}));
        assertEquals(2, solution.firstMissingPositive2(new int[]{1}));

    }
}
