package com.deerhunter;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Oops, forgot to write comment. Good luck, bro.
 * <p>
 * Copyright (c) 2019, deerhunter0837@gmail.com All Rights Reserved.
 *
 * @author xuejunc
 * @date 2019-08-31
 */
class Solution020Test {
    @Test
    void isValid() {
        assertTrue(Solution020.isValid( "()"));
        assertTrue(Solution020.isValid(  "()[]{}"));
        assertTrue(Solution020.isValid( "{[]}"));
        assertFalse(Solution020.isValid( "(]"));
        assertFalse(Solution020.isValid( "([)]"));
    }
}