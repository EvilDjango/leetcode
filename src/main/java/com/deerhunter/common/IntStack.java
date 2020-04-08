package com.deerhunter.common;

/**
 * int栈
 * <p>
 * Copyright (c) 2019, deerhunter0837@gmail.com All Rights Reserved.
 *
 * @author xuejunc
 * @date 2019-11-20
 */
public class IntStack {
    private int[] stack;
    private int capacity;
    private int top;

    public IntStack(int size) {
        this.stack = new int[size];
        capacity = stack.length;
        top = -1;
    }

    public void push(int i) {
        if (top == capacity - 1) {
            throw new IllegalStateException("Stack is full");
        }
        stack[++top] = i;
    }

    public int peek() {
        if (top < 0) {
            throw new IllegalStateException("Stack is empty");
        }
        return stack[top];
    }

    public int pop() {
        if (top < 0) {
            throw new IllegalStateException("Stack is empty");
        }
        top--;
        return stack[top + 1];
    }

    public boolean isEmpty() {
        return top < 0;
    }

    public int size() {
        return top + 1;
    }

    public int getCapacity() {
        return capacity;
    }
}
