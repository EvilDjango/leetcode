package com.deerhunter;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Oops, forgot to write comment. Good luck, bro.
 * <p>
 * Copyright (c) 2019, deerhunter0837@gmail.com All Rights Reserved.
 *
 * @author xuejunc
 * @date 2019-08-28
 */
class Solution014Test {
    Solution014 solution = new Solution014();

    @Test
    void longestCommonPrefix() {
        assertEquals("fl", solution.longestCommonPrefix(new String[]{"flower", "flow", "flight"}));
        assertEquals("", solution.longestCommonPrefix(new String[]{"dog", "racecar", "car"}));
    }
}