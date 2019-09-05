package com.deerhunter;

import com.deerhunter.common.ListNode;
import com.deerhunter.common.Utils;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Oops, forgot to write comment. Good luck, bro.
 * <p>
 * Copyright (c) 2019, deerhunter0837@gmail.com All Rights Reserved.
 *
 * @author xuejunc
 * @date 2019-09-03
 */
class Solution024Test {

    @Test
    void swapPairs() {
        assertEquals(Utils.createLinkList(1, 2, 3, 4), Solution024.swapPairs(Utils.createLinkList(2, 1, 4, 3)));
        assertEquals(Utils.createLinkList(1), Solution024.swapPairs(Utils.createLinkList(1)));
    }

    @Test
    void swapPairs2() {
        ListNode actual = Solution024.swapPairs2(Utils.createLinkList(2, 1, 4, 3));
        assertEquals(Utils.createLinkList(1, 2, 3, 4), actual);
        assertEquals(Utils.createLinkList(1), Solution024.swapPairs2(Utils.createLinkList(1)));
    }
}