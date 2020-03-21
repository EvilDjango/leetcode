package com.deerhunter;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Oops, forgot to write comment. Good luck, bro.
 * <p>
 * Copyright (c) 2019, deerhunter0837@gmail.com All Rights Reserved.
 *
 * @author xuejunc
 * @date 2020-03-21
 */
class Topic067Test {
    @Test
    void solution1() {
        Topic067.Solution1 instance = new Topic067.Solution1();
        assertEquals("111", instance.addBinary("100", "11"));
        assertEquals("10", instance.addBinary("1", "1"));
        assertEquals("1001", instance.addBinary("110", "11"));
        assertEquals("10101", instance.addBinary("1010", "1011"));
    }
}
