package com.deerhunter;

import java.util.ArrayDeque;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Queue;

/**
 * 284. 顶端迭代器
 * 给定一个迭代器类的接口，接口包含两个方法： next() 和 hasNext()。设计并实现一个支持 peek() 操作的顶端迭代器 -- 其本质就是把原本应由 next() 方法返回的元素 peek() 出来。
 * <p>
 * 示例:
 * <p>
 * 假设迭代器被初始化为列表 [1,2,3]。
 * <p>
 * 调用 next() 返回 1，得到列表中的第一个元素。
 * 现在调用 peek() 返回 2，下一个元素。在此之后调用 next() 仍然返回 2。
 * 最后一次调用 next() 返回 3，末尾元素。在此之后调用 hasNext() 应该返回 false。
 * 进阶：你将如何拓展你的设计？使之变得通用化，从而适应所有的类型，而不只是整数型？
 * <p>
 * 通过次数9,295提交次数12,669
 * <p>
 * Copyright (c) @2021 deerhunter0837@gmail.com All Rights Reserved.
 *
 * @author xuejunc
 * @createTime 7/15/21 9:55 AM
 */
public class Topic284 {
    class PeekingIterator implements Iterator<Integer> {
        private Iterator<Integer> it;
        private Queue<Integer> queue;

        public PeekingIterator(Iterator<Integer> iterator) {
            it = iterator;
            queue = new ArrayDeque<>();
        }

        // Returns the next element in the iteration without advancing the iterator.
        public Integer peek() {
            if (queue.isEmpty()) {
                queue.add(it.next());
            }
            return queue.peek();

        }

        // hasNext() and next() should behave the same as in the Iterator interface.
        // Override them if needed.
        @Override
        public Integer next() {
            if (!queue.isEmpty()) {
                return queue.remove();
            }
            return it.next();
        }

        @Override
        public boolean hasNext() {
            return queue.isEmpty() && it.hasNext();
        }
    }
}
