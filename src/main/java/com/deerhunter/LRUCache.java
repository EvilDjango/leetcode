package com.deerhunter;

import java.util.HashMap;
import java.util.Map;

/**
 * Oops, forgot to write comments. Good luck, bro.
 * <p>
 * Copyright (c) @2022 deerhunter0837@gmail.com All Rights Reserved.
 *
 * @author xuejunc
 * @createTime 2022/4/19 下午10:09
 */
public class LRUCache<K, V> {
    private int capacity;
    private Map<K, Node<K, V>> map;
    private Node<K, V> head, tail;

    /**
     * 默认的容量是100
     */
    public LRUCache() {
        this(100);
    }

    public LRUCache(int capacity) {
        this.capacity = capacity;
        map = new HashMap<>();
        head = new Node<>();
        tail = new Node<>();
        head.next = tail;
        tail.prev = head;
    }

    public void set(K key, V value) {
        if (!map.containsKey(key)) {
            if (capacity == map.size()) {
                Node<K, V> removed = removeLastNode();
                map.remove(removed.k);
            }
            Node<K, V> node = new Node<>(key, value);
            map.put(key, node);
            addHead(node);
            return;
        }
        Node<K, V> node = map.get(key);
        node.v = value;
        moveToHead(node);
    }

    public V get(K key) {
        if (!map.containsKey(key)) {
            return null;
        }
        Node<K, V> node = map.get(key);
        moveToHead(node);
        return node.v;
    }

    private void moveToHead(Node<K, V> node) {
        removeNode(node);
        addHead(node);
    }

    private void removeNode(Node<K, V> node) {
        node.prev.next = node.next;
        node.next.prev = node.prev;
    }

    private void addHead(Node<K, V> node) {
        node.next = head.next;
        node.next.prev = node;
        head.next = node;
        node.prev = head;
    }

    private Node<K, V> removeLastNode() {
        Node<K, V> last = tail.prev;
        removeNode(last);
        return last;
    }

    private class Node<K, V> {
        K k;
        V v;
        Node<K,V> prev, next;

        public Node() {
        }

        public Node(K k, V v) {
            this.k = k;
            this.v = v;
        }
    }
}
