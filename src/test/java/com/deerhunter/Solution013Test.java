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
class Solution013Test {
    Solution013 solution = new Solution013();

    @Test
    void romanToInt() {
        assertEquals(3,solution.romanToInt("III"));
        assertEquals(4,solution.romanToInt("IV"));
        assertEquals(9,solution.romanToInt("IX"));
        assertEquals(58,solution.romanToInt("LVIII"));
        assertEquals(1994,solution.romanToInt("MCMXCIV"));
    }
}