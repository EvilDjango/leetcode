package com.deerhunter.common;

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
class ListNodeTest {

    @Test
    void equals1() {
        ListNode list1 = Utils.createLinkList(1, 2, 3);
        ListNode list2 = Utils.createLinkList(1, 2, 3, 4, 5);
        assertNotEquals(list1, list2);
        assertNotEquals(list2, list1);
    }
}
