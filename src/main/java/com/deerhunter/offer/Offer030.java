package com.deerhunter.offer;

/**
 * 剑指 Offer 30. 包含min函数的栈
 * 定义栈的数据结构，请在该类型中实现一个能够得到栈的最小元素的 min 函数在该栈中，调用 min、push 及 pop 的时间复杂度都是 O(1)。
 * <p>
 * <p>
 * <p>
 * 示例:
 * <p>
 * MinStack minStack = new MinStack();
 * minStack.push(-2);
 * minStack.push(0);
 * minStack.push(-3);
 * minStack.min();   --> 返回 -3.
 * minStack.pop();
 * minStack.top();      --> 返回 0.
 * minStack.min();   --> 返回 -2.
 * <p>
 * <p>
 * 提示：
 * <p>
 * 各函数的调用总次数不超过 20000 次
 * <p>
 * <p>
 * 注意：本题与主站 155 题相同：https://leetcode-cn.com/problems/min-stack/
 * <p>
 * 通过次数242,036提交次数438,114
 * <p>
 * Copyright (c) @2022 deerhunter0837@gmail.com All Rights Reserved.
 *
 * @author xuejunc
 * @createTime 2022/5/7 下午7:15
 */
public class Offer030 {
    class MinStack {
        private int capacity;
        private int size;
        private int[] nums;
        private int[] mins;

        /**
         * initialize your data structure here.
         */
        public MinStack() {
            capacity = 1 << 4;
            nums = new int[capacity];
            mins = new int[capacity];
        }

        public void push(int x) {
            growIfNeed();
            nums[size] = x;
            mins[size] = size == 0 ? x : Math.min(mins[size - 1], x);
            size++;
        }

        private void growIfNeed() {
            if (size == capacity) {
                int[] newNums = new int[capacity << 1];
                System.arraycopy(nums, 0, newNums, 0, size);
                int[] newMins = new int[capacity << 1];
                System.arraycopy(mins, 0, newMins, 0, size);
                nums = newNums;
                mins = newMins;
                capacity <<= 1;
            }
        }

        public void pop() {
            size--;
        }

        public int top() {
            return nums[size - 1];
        }

        public int min() {
            return mins[size - 1];
        }
    }
}
