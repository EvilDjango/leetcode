package com.deerhunter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 给定一个链表，每个节点包含一个额外增加的随机指针，该指针可以指向链表中的任何节点或空节点。
 * <p>
 * 要求返回这个链表的 深拷贝。 
 * <p>
 * 我们用一个由 n 个节点组成的链表来表示输入/输出中的链表。每个节点用一个 [val, random_index] 表示：
 * <p>
 * val：一个表示 Node.val 的整数。
 * random_index：随机指针指向的节点索引（范围从 0 到 n-1）；如果不指向任何节点，则为  null 。
 *  
 * <p>
 * 示例 1：
 * <p>
 * <p>
 * <p>
 * 输入：head = [[7,null],[13,0],[11,4],[10,2],[1,0]]
 * 输出：[[7,null],[13,0],[11,4],[10,2],[1,0]]
 * 示例 2：
 * <p>
 * <p>
 * <p>
 * 输入：head = [[1,1],[2,1]]
 * 输出：[[1,1],[2,1]]
 * 示例 3：
 * <p>
 * <p>
 * <p>
 * 输入：head = [[3,null],[3,0],[3,null]]
 * 输出：[[3,null],[3,0],[3,null]]
 * 示例 4：
 * <p>
 * 输入：head = []
 * 输出：[]
 * 解释：给定的链表为空（空指针），因此返回 null。
 *  
 * <p>
 * 提示：
 * <p>
 * -10000 <= Node.val <= 10000
 * Node.random 为空（null）或指向链表中的节点。
 * 节点数目不超过 1000 。
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/copy-list-with-random-pointer
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * <p>
 * Copyright (c) 20120 deerhunter0837@gmail.com All Rights Reserved.
 *
 * @author xuejunc
 * @createTime 2020/10/23 13:47
 */
public class Topic138 {
    /**
     * 笨办法
     */
    public static class Solution1 {
        public Node copyRandomList(Node head) {
            Node dummy = new Node(0);
            Node newCur = dummy;
            Node cur = head;
            while (cur != null) {
                newCur.next = new Node(cur.val);
                newCur = newCur.next;
                cur = cur.next;
            }

            newCur = dummy.next;
            cur = head;
            while (cur != null) {
                if (cur.random != null) {
                    Node temp = head;
                    Node newTemp = dummy.next;
                    while (temp != cur.random) {
                        temp = temp.next;
                        newTemp = newTemp.next;
                    }
                    newCur.random = newTemp;
                }
                newCur = newCur.next;
                cur = cur.next;
            }
            return dummy.next;
        }
    }

    /**
     * 使用哈希表加快查找
     */
    public static class Solution2 {
        private Map<Node, Node> cloneByOrigin;

        public Node copyRandomList(Node head) {
            if (head == null) {
                return null;
            }
            cloneByOrigin = new HashMap<>();
            Node clonedHead = new Node(head.val);
            cloneByOrigin.put(head, clonedHead);
            Node cur = clonedHead;
            while (head != null) {
                cur.next = getCloneNode(head.next);
                cur.random = getCloneNode(head.random);
                head = head.next;
                cur = cur.next;
            }
            return clonedHead;
        }

        private Node getCloneNode(Node node) {
            if (node == null) {
                return null;
            }
            if (!cloneByOrigin.containsKey(node)) {
                cloneByOrigin.put(node, new Node(node.val));
            }
            return cloneByOrigin.get(node);
        }
    }


    /**
     * 空间复杂度O(1)的算法，参考题解
     */
    public static class Solution3 {
        public Node copyRandomList(Node head) {
            if (head == null) {
                return null;
            }
            Node cur = head;
            while (cur != null) {
                Node next = cur.next;
                cur.next = new Node(cur.val);
                cur.next.next = next;
                cur = next;
            }
            cur = head;
            while (cur != null) {
                if (cur.random != null) {
                    cur.next.random = cur.random.next;
                }
                cur = cur.next.next;
            }
            cur = head;
            Node clonedHead = head.next;
            while (cur != null) {
                Node cloned = cur.next;
                cur.next = cloned.next;
                if (cur.next != null) {
                    cloned.next = cur.next.next;
                }
                cur = cur.next;
            }
            return clonedHead;
        }
    }

    public static class Node {
        int val;
        Node next;
        Node random;

        public Node(int val) {
            this.val = val;
            this.next = null;
            this.random = null;
        }
    }
}
