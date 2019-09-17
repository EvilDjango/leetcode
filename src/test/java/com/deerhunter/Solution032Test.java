package com.deerhunter;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Oops, forgot to write comment. Good luck, bro.
 * <p>
 * Copyright (c) 2019, deerhunter0837@gmail.com All Rights Reserved.
 *
 * @author xuejunc
 * @date 2019-09-17
 */
class Solution032Test {

    @Test
    void longestValidParentheses() {
        assertEquals(2, Solution032.longestValidParentheses("()"));
        assertEquals(4, Solution032.longestValidParentheses("(())"));
        assertEquals(4, Solution032.longestValidParentheses("()()"));
        assertEquals(4, Solution032.longestValidParentheses("()())(()"));
        assertEquals(4, Solution032.longestValidParentheses(")()())"));
        assertEquals(4, Solution032.longestValidParentheses("(()()((((("));
        assertEquals(2, Solution032.longestValidParentheses("(()"));
        assertEquals(6, Solution032.longestValidParentheses("()(())"));
    }

    @Test
    void longestValidParentheses2() {
        assertEquals(2, Solution032.longestValidParentheses2("()"));
        assertEquals(4, Solution032.longestValidParentheses2("(())"));
        assertEquals(4, Solution032.longestValidParentheses2("()()"));
        assertEquals(4, Solution032.longestValidParentheses2("()())(()"));
        assertEquals(4, Solution032.longestValidParentheses2(")()())"));
        assertEquals(4, Solution032.longestValidParentheses2("(()()((((("));
        assertEquals(2, Solution032.longestValidParentheses2("(()"));
        assertEquals(6, Solution032.longestValidParentheses2("()(())"));
    }
}