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
class Solution012Test {
    Solution012 solution = new Solution012();

    @Test
    void intToRoman() {
        assertEquals("III", solution.intToRoman(3));
        assertEquals("IV", solution.intToRoman(4));
        assertEquals("IX", solution.intToRoman(9));
        assertEquals("LVIII", solution.intToRoman(58));
        assertEquals("MCMXCIV", solution.intToRoman(1994));
    }
}