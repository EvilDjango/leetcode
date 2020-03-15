package com.deerhunter;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Oops, forgot to write comment. Good luck, bro.
 * <p>
 * Copyright (c) 2019, deerhunter0837@gmail.com All Rights Reserved.
 *
 * @author xuejunc
 * @date 2020-03-15
 */
class Topic062Test {
    @Test
    void solution1() {
        Topic062.Solution instance = new Topic062.Solution();
        assertEquals(3, instance.uniquePaths(3, 2));
        assertEquals(28, instance.uniquePaths(7, 3));
    }
}
