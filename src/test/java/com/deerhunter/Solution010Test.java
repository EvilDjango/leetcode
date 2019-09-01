package com.deerhunter;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Oops, forgot to write comment. Good luck, bro.
 * <p>
 * Copyright (c) 2019, deerhunter0837@gmail.com All Rights Reserved.
 *
 * @author xuejunc
 * @date 2019-08-21
 */
class Solution010Test {
    Solution010 solution = new Solution010();

    @Test
    void isMatch() {
        assertTrue(solution.isMatch("a", "ab*"));
        assertTrue(solution.isMatch("", ""));

        assertTrue(solution.isMatch("", ".*"));
        assertTrue(solution.isMatch("ab", ".*"));
        assertTrue(solution.isMatch("aab", "a.*"));
        assertTrue(solution.isMatch("aa", "a*"));
        assertTrue(solution.isMatch("aab", "c*a*b"));
        assertFalse(solution.isMatch("aa", "a"));
        assertFalse(solution.isMatch("mississippi", "mis*is*p*."));
    }

    @Test
    void isMatch2() {
        assertTrue(solution.isMatch2("a", "ab*"));
        assertTrue(solution.isMatch2("", ""));

        assertTrue(solution.isMatch2("", ".*"));
        assertTrue(solution.isMatch2("ab", ".*"));
        assertTrue(solution.isMatch2("aab", "a.*"));
        assertTrue(solution.isMatch2("aa", "a*"));
        assertTrue(solution.isMatch2("aab", "c*a*b"));
        assertFalse(solution.isMatch2("aa", "a"));
        assertFalse(solution.isMatch2("mississippi", "mis*is*p*."));
    }

    @Test
    void isMatch3() {
        assertTrue(solution.isMatch3("a", "ab*"));
        assertTrue(solution.isMatch3("", ""));

        assertTrue(solution.isMatch3("", ".*"));
        assertTrue(solution.isMatch3("ab", ".*"));
        assertTrue(solution.isMatch3("aab", "a.*"));
        assertTrue(solution.isMatch3("aa", "a*"));
        assertTrue(solution.isMatch3("aab", "c*a*b"));
        assertFalse(solution.isMatch3("aa", "a"));
        assertFalse(solution.isMatch3("mississippi", "mis*is*p*."));
    }

    @Test
    void isMatch4() {
        assertTrue(solution.isMatch4("a", "ab*"));
        assertTrue(solution.isMatch4("", ""));

        assertTrue(solution.isMatch4("", ".*"));
        assertTrue(solution.isMatch4("ab", ".*"));
        assertTrue(solution.isMatch4("aab", "a.*"));
        assertTrue(solution.isMatch4("aa", "a*"));
        assertTrue(solution.isMatch4("aab", "c*a*b"));
        assertFalse(solution.isMatch4("aa", "a"));
        assertFalse(solution.isMatch4("mississippi", "mis*is*p*."));
    }
}