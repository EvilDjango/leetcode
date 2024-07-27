package com.deerhunter.topic;

import java.util.Comparator;
import java.util.TreeSet;

/**
 * 295. 数据流的中位数
 * 中位数是有序列表中间的数。如果列表长度是偶数，中位数则是中间两个数的平均值。
 * <p>
 * 例如，
 * <p>
 * [2,3,4] 的中位数是 3
 * <p>
 * [2,3] 的中位数是 (2 + 3) / 2 = 2.5
 * <p>
 * 设计一个支持以下两种操作的数据结构：
 * <p>
 * void addNum(int num) - 从数据流中添加一个整数到数据结构中。
 * double findMedian() - 返回目前所有元素的中位数。
 * 示例：
 * <p>
 * addNum(1)
 * addNum(2)
 * findMedian() -> 1.5
 * addNum(3)
 * findMedian() -> 2
 * 进阶:
 * <p>
 * 如果数据流中所有整数都在 0 到 100 范围内，你将如何优化你的算法？
 * 如果数据流中 99% 的整数都在 0 到 100 范围内，你将如何优化你的算法？
 * 通过次数43,735提交次数83,878
 * <p>
 * Copyright (c) @2021 deerhunter0837@gmail.com All Rights Reserved.
 *
 * @author xuejunc
 * @createTime 8/19/21 9:44 PM
 */
public class Topic295 {
    class MedianFinder {
        private TreeSet<int[]> tree;
        private Comparator<int[]> comparator;
        private int[] low, high;
        private int index;

        /**
         * initialize your data structure here.
         */
        public MedianFinder() {
            comparator = (o1, o2) -> {
                if (o1[0] != o2[0]) {
                    return o1[0] - o2[0];
                }
                return o1[1] - o2[1];
            };
            tree = new TreeSet<>(comparator);
        }

        public void addNum(int num) {
            int n = tree.size();
            int[] curr = {num, index++};
            tree.add(curr);
            if (n == 0) {
                low = high = curr;
            } else if ((n & 1) == 1) {
                if (num >= low[0]) {
                    high = tree.higher(low);
                } else {
                    low = tree.lower(low);
                }
            } else {
                if (num >= low[0] && num < high[0]) {
                    low = high = curr;
                } else if (num < low[0]) {
                    high = low;
                } else {
                    low = high;
                }
            }
        }

        public double findMedian() {
            return (low[0] + high[0]) / 2.0;
        }
    }

/**
 * Your MedianFinder object will be instantiated and called as such:
 * MedianFinder obj = new MedianFinder();
 * obj.addNum(num);
 * double param_2 = obj.findMedian();
 */
}
