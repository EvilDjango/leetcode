package com.deerhunter;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Oops, forgot to write comment. Good luck, bro.
 * <p>
 * Copyright (c) 2019, deerhunter0837@gmail.com All Rights Reserved.
 *
 * @author xuejunc
 * @date 2019-11-21
 */
class Solution043Test {

    @Test
    void multiply() {
        Solution043 solution = new Solution043();
        assertEquals("6", solution.multiply("2", "3"));
        assertEquals("132", solution.multiply("11", "12"));
        assertEquals("8", solution.multiply("2", "4"));
        assertEquals("256", solution.multiply("16", "16"));
        assertEquals("1024", solution.multiply("32", "32"));
    }

    @Test
    void multiply2() {
        Solution043.ReferenceSolution solution = new Solution043.ReferenceSolution();
        assertEquals("6", solution.multiply("2", "3"));
        assertEquals("132", solution.multiply("11", "12"));
        assertEquals("8", solution.multiply("2", "4"));
        assertEquals("256", solution.multiply("16", "16"));
        assertEquals("1024", solution.multiply("32", "32"));
        assertEquals("56088", solution.multiply("123", "456"));
    }
}