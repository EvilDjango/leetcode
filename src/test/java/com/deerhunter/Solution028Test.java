package com.deerhunter;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Oops, forgot to write comment. Good luck, bro.
 * <p>
 * Copyright (c) 2019, deerhunter0837@gmail.com All Rights Reserved.
 *
 * @author xuejunc
 * @date 2019-09-04
 */
class Solution028Test {

    @Test
    void strStr() {
        assertEquals(2, Solution028.strStr("hello", "ll"));
        assertEquals(-1, Solution028.strStr("aaaaa", "bba"));
        assertEquals(0, Solution028.strStr("aaaaa", ""));
        assertEquals(4, Solution028.strStr("mississippi", "issip"));
    }
}