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
        while (cur1 != null || cur2 != null) {
            if (cur1 == null || cur2 == null || cur1.val != cur2.val) {
                return false;
            }
            cur1 = cur1.next;
            cur2 = cur2.next;
        }
        return true;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("ListNode[ " + this.val);
        ListNode cur = this;
        while (cur.next != null) {
            sb.append(" -> ").append(cur.next.val);
            cur = cur.next;
        }
        sb.append("]");
        return sb.toString();
    }
}
