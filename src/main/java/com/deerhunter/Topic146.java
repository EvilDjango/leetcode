package com.deerhunter;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.HashMap;
import java.util.Map;

/**
 * 146. LRU缓存机制
 * 运用你所掌握的数据结构，设计和实现一个  LRU (最近最少使用) 缓存机制。它应该支持以下操作： 获取数据 get 和 写入数据 put 。
 * <p>
 * 获取数据 get(key) - 如果关键字 (key) 存在于缓存中，则获取关键字的值（总是正数），否则返回 -1。
 * 写入数据 put(key, value) - 如果关键字已经存在，则变更其数据值；如果关键字不存在，则插入该组「关键字/值」。当缓存容量达到上限时，它应该在写入新数据之前删除最久未使用的数据值，从而为新的数据值留出空间。
 * <p>
 * <p>
 * <p>
 * 进阶:
 * <p>
 * 你是否可以在 O(1) 时间复杂度内完成这两种操作？
 * <p>
 * <p>
 * <p>
 * 示例:
 * <p>
 * LRUCache cache = new LRUCache( 2 /* 缓存容量
 * <p>
 * cache.put(1,1);
 * cache.put(2,2);
 * cache.get(1);       // 返回  1
 * cache.put(3,3);    // 该操作会使得关键字 2 作废
 * cache.get(2);       // 返回 -1 (未找到)
 * cache.put(4,4);    // 该操作会使得关键字 1 作废
 * cache.get(1);       // 返回 -1 (未找到)
 * cache.get(3);       // 返回  3
 * cache.get(4);       // 返回  4
 * <p>
 * Copyright (c) 20120 deerhunter0837@gmail.com All Rights Reserved.
 *
 * @author xuejunc
 * @createTime 2020/11/4 21:17
 */
public class Topic146 {
    public static interface ILRUCache {
        public int get(int key);

        public void put(int key, int value);
    }

    /**
     * 朴素解法，存取的时间复杂度都为O(n)
     */
    public static class LRUCache implements ILRUCache {
        private Deque<Integer> latest = new ArrayDeque<>();
        private Map<Integer, Integer> dict = new HashMap<>();
        private int capacity;

        public LRUCache(int capacity) {
            this.capacity = capacity;
        }

        public int get(int key) {
            if (!dict.containsKey(key)) {
                return -1;
            }
            latest.remove(key);
            latest.addLast(key);
            return dict.get(key);
        }

        public void put(int key, int value) {
            if (!dict.containsKey(key) && capacity == latest.size()) {
                int toRemove = latest.removeFirst();
                dict.remove(toRemove);
            }
            latest.remove(key);
            latest.addLast(key);
            dict.put(key, value);
        }
    }

    /**
     * 时间复杂度为O(1)的解法
     */
    public static class LRUCache2 implements ILRUCache {
        private Map<Integer, Node> nodes = new HashMap<>();
        private Node head = new Node(-1, -1);
        private Node tail = new Node(0, 0);
        private int capacity;
        private int size;

        private static class Node {
            int key;
            int val;
            Node next;
            Node prev;

            public Node(int key, int val) {
                this.key = key;
                this.val = val;
            }
        }

        public LRUCache2(int capacity) {
            if (capacity <= 0) {
                throw new IllegalArgumentException("Capacity should be larger than 0");
            }
            this.capacity = capacity;
            this.size = 0;
            head.next = tail;
            tail.prev = head;
        }

        public int get(int key) {
            if (!nodes.containsKey(key)) {
                return -1;
            }
            moveToLast(nodes.get(key));
            return nodes.get(key).val;
        }

        public void put(int key, int value) {
            if (nodes.containsKey(key)) {
                nodes.get(key).val = value;
                moveToLast(nodes.get(key));
                return;
            }

            if (size == capacity) {
                removeFirstNode();
            }

            addNode(key, value);
        }

        private void removeFirstNode() {
            Node toRemove = head.next;
            remove(toRemove);
            nodes.remove(toRemove.key);
            size--;
        }

        private void addNode(int key, int value) {
            Node node = new Node(key, value);
            nodes.put(key, node);
            addAfter(node, tail.prev);
            size++;
        }

        private void moveToLast(Node node) {
            Node last = tail.prev;
            if (last == node) {
                return;
            }
            remove(node);
            addAfter(node, last);

        }

        private void addAfter(Node node, Node prev) {
            node.next = prev.next;
            node.prev = prev;
            prev.next = node;
            node.next.prev = node;
        }

        private void remove(Node node) {
            node.next.prev = node.prev;
            node.prev.next = node.next;
        }
    }
}
