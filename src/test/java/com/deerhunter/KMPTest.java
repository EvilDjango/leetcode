package com.deerhunter;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Oops, forgot to write comment. Good luck, bro.
 * <p>
 * Copyright (c) 2019, deerhunter0837@gmail.com All Rights Reserved.
 *
 * @author xuejunc
 * @date 2019-09-19
 */
class KMPTest {

    @Test
    void kmp() {
        assertEquals(0, KMP.kmp("ab", "ab"));
        assertEquals(-1, KMP.kmp("ab", "cab"));
        assertEquals(1, KMP.kmp("cab", "ab"));
    }
}