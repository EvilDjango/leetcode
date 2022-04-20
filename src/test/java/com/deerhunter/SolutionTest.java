package com.deerhunter;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Oops, forgot to write comments. Good luck, bro.
 * <p>
 * Copyright (c) @2022 deerhunter0837@gmail.com All Rights Reserved.
 *
 * @author xuejunc
 * @createTime 2022/4/17 下午9:47
 */
class SolutionTest {

    @Test
    void name() {
        assertEquals(6,Solution.countNum(2,22,2));
        assertEquals(1,Solution.countNum(2,2));
        assertEquals(6,Solution.countNum(22,2));
        assertEquals(23,Solution.countNum(131,7));
    }
}
