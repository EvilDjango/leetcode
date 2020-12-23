package com.deerhunter;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * 设计一个支持 push ，pop ，top 操作，并能在常数时间内检索到最小元素的栈。
 * <p>
 * push(x) —— 将元素 x 推入栈中。
 * pop() —— 删除栈顶的元素。
 * top() —— 获取栈顶元素。
 * getMin() —— 检索栈中的最小元素。
 *  
 * <p>
 * 示例:
 * <p>
 * 输入：
 * ["MinStack","push","push","push","getMin","pop","top","getMin"]
 * [[],[-2],[0],[-3],[],[],[],[]]
 * <p>
 * 输出：
 * [null,null,null,null,-3,null,0,-2]
 * <p>
 * 解释：
 * MinStack minStack = new MinStack();
 * minStack.push(-2);
 * minStack.push(0);
 * minStack.push(-3);
 * minStack.getMin();   --> 返回 -3.
 * minStack.pop();
 * minStack.top();      --> 返回 0.
 * minStack.getMin();   --> 返回 -2.
 *  
 * <p>
 * 提示：
 * <p>
 * pop、top 和 getMin 操作总是在 非空栈 上调用。
 * 通过次数177,167提交次数318,277
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/min-stack
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * <p>
 * Copyright (c) 20120 deerhunter0837@gmail.com All Rights Reserved.
 *
 * @author xuejunc
 * @createTime 2020/11/16 17:11
 */
public class Topic155 {
    public static class MinStack {
        private Node naturalHead;
        private Node naturalTail;
        private Node sortedHead;
        private Node sortedTail;

        /**
         * initialize your data structure here.
         */
        public MinStack() {
            naturalHead = new Node(0);
            naturalTail = new Node(0);
            naturalHead.next = naturalTail;
            naturalTail.prev = naturalHead;
            sortedHead = new Node(0);
            sortedTail = new Node(0);
            sortedHead.after = sortedTail;
            sortedTail.before = sortedHead;
        }

        public void push(int x) {
            Node node = new Node(x);
            pushToNaturalList(node);
            pushToSortedList(node);
        }

        private void pushToSortedList(Node node) {
            Node cur = sortedHead.after;
            while (cur != sortedTail && node.val >= cur.val) {
                cur = cur.after;
            }
            node.after = cur;
            node.before = cur.before;
            cur.before.after = node;
            cur.before = node;
        }

        private void pushToNaturalList(Node node) {
            node.next = naturalTail;
            node.prev = naturalTail.prev;
            naturalTail.prev.next = node;
            naturalTail.prev = node;
        }

        public void pop() {
            Node node = removeNaturalTail();
            removeFromSortedList(node);
        }

        private void removeFromSortedList(Node node) {
            Node cur = sortedHead.after;
            while (cur != sortedTail && cur.val != node.val) {
                cur = cur.after;
            }
            node.before.after = node.after;
            node.after.before = node.before;
        }

        private Node removeNaturalTail() {
            Node node = naturalTail.prev;
            node.prev.next = naturalTail;
            naturalTail.prev = node.prev;
            return node;
        }

        public int top() {
            return naturalTail.prev.val;
        }

        public int getMin() {
            return sortedHead.after.val;
        }

        private static class Node {
            int val;
            /**
             * 链路1的前驱
             */
            Node prev;
            /**
             * 链路1的后继
             */
            Node next;
            /**
             * 链路2的前驱
             */
            Node before;
            /**
             * 链路2的后继
             */
            Node after;

            public Node(int val) {
                this.val = val;
            }
        }
    }

    /**
     * 参考官方题解
     */
    public static class MinStack2 {
        private Deque<Integer> xStack;
        private Deque<Integer> minStack;

        /**
         * initialize your data structure here.
         */
        public MinStack2() {
            xStack = new ArrayDeque<>();
            minStack = new ArrayDeque<>();
            minStack.push(Integer.MAX_VALUE);
        }

        public void push(int x) {
            xStack.push(x);
            minStack.push(Math.min(minStack.peek(), x));
        }

        public void pop() {
            xStack.pop();
            minStack.pop();
        }

        public int top() {
            return xStack.peek();
        }

        public int getMin() {
            return minStack.peek();
        }
    }
}
