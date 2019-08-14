package com.deerhunter;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Oops, forgot to write comment. Good luck, bro.
 * <p>
 * Copyright (c) 2019, deerhunter0837@gmail.com All Rights Reserved.
 *
 * @author xuejunc
 * @date 2019-08-13
 */
class Solution006Test {
    private Solution006 solutiion = new Solution006();

    @Test
    void convert() {
        assertEquals("LCIRETOESIIGEDHN", solutiion.convert("LEETCODEISHIRING", 3));
        assertEquals("LDREOEIIECIHNTSG", solutiion.convert("LEETCODEISHIRING", 4));
        assertEquals("ab", solutiion.convert("ab", 1));
    }
}