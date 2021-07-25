package com.deerhunter;

import java.util.NoSuchElementException;

/**
 * 请设计并实现一个能够展开二维向量的迭代器。该迭代器需要支持 next 和 hasNext 两种操作。
 * <p>
 *  
 * <p>
 * 示例：
 * <p>
 * Vector2D iterator = new Vector2D([[1,2],[3],[4]]);
 * <p>
 * iterator.next(); // 返回 1
 * iterator.next(); // 返回 2
 * iterator.next(); // 返回 3
 * iterator.hasNext(); // 返回 true
 * iterator.hasNext(); // 返回 true
 * iterator.next(); // 返回 4
 * iterator.hasNext(); // 返回 false
 *  
 * <p>
 * 注意：
 * <p>
 * 请记得 重置 在 Vector2D 中声明的类变量（静态变量），因为类变量会 在多个测试用例中保持不变，影响判题准确。请 查阅 这里。
 * 你可以假定 next() 的调用总是合法的，即当 next() 被调用时，二维向量总是存在至少一个后续元素。
 *  
 * <p>
 * 进阶：尝试在代码中仅使用 C++ 提供的迭代器 或 Java 提供的迭代器。
 * <p>
 * 通过次数3,121提交次数5,808
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/flatten-2d-vector
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * <p>
 * Copyright (c) @2021 deerhunter0837@gmail.com All Rights Reserved.
 *
 * @author xuejunc
 * @createTime 7/23/21 3:04 PM
 */
public class Topic251 {
    public static class Vector2D {
        private final int[][] vec;
        private int row;
        private int col;

        public Vector2D(int[][] vec) {
            this.vec = vec;
        }

        public int next() {
            if (!hasNext()) {
                throw new NoSuchElementException();
            }
            return vec[row][col++];
        }

        public boolean hasNext() {
            advanceToNext();
            return row < vec.length;
        }

        private void advanceToNext() {
            while (row < vec.length && col == vec[row].length) {
                row++;
                col = 0;
            }
        }
    }
}
