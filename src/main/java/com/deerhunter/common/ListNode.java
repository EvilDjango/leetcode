package com.deerhunter.common;

public class ListNode {
    public int val;
    public ListNode next;

    public ListNode(int x) {
        val = x;
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof ListNode)) {
            return false;
        }
        ListNode node = (ListNode) obj;
        ListNode cur1 = this;
        ListNode cur2 = node;
        while (cur1 != null) {
            if (cur2 == null || cur1.val != cur2.val) {
                return false;
            }
            cur1 = cur1.next;
            cur2 = cur2.next;
        }
        return true;
    }
}