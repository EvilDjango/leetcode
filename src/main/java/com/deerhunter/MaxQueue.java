package com.deerhunter;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Queue;

class MaxQueue {
    private Queue<Integer> queue = new ArrayDeque<>();
    /**
     * 非递增双向队列
     */
    private Deque<Integer> maxValues = new ArrayDeque<>();

    public MaxQueue() {
    }

    public int max_value() {
        if (maxValues.isEmpty()) {
            return -1;
        }
        return maxValues.peekFirst();
    }

    public void push_back(int value) {
        queue.add(value);
        while (!maxValues.isEmpty()&&maxValues.peekLast() < value) {
            maxValues.removeLast();
        }
        maxValues.add(value);
    }

    public int pop_front() {
        if (queue.isEmpty()) {
            return -1;
        }
        int val = queue.remove();
        if (maxValues.peekFirst() == val) {
            maxValues.removeFirst();
        }
        return val;
    }
}
