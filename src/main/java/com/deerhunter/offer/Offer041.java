package com.deerhunter.offer;

import java.util.TreeMap;

/**
 * 剑指 Offer 41. 数据流中的中位数
 * 如何得到一个数据流中的中位数？如果从数据流中读出奇数个数值，那么中位数就是所有数值排序之后位于中间的数值。如果从数据流中读出偶数个数值，那么中位数就是所有数值排序之后中间两个数的平均值。
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
 * 示例 1：
 * <p>
 * 输入：
 * ["MedianFinder","addNum","addNum","findMedian","addNum","findMedian"]
 * [[],[1],[2],[],[3],[]]
 * 输出：[null,null,null,1.50000,null,2.00000]
 * 示例 2：
 * <p>
 * 输入：
 * ["MedianFinder","addNum","findMedian","addNum","findMedian"]
 * [[],[2],[],[3],[]]
 * 输出：[null,null,2.00000,null,2.50000]
 * <p>
 * <p>
 * 限制：
 * <p>
 * 最多会对 addNum、findMedian 进行 50000 次调用。
 * 注意：本题与主站 295 题相同：https://leetcode-cn.com/problems/find-median-from-data-stream/
 * <p>
 * 通过次数95,055提交次数162,718
 * <p>
 * Copyright (c) @2022 deerhunter0837@gmail.com All Rights Reserved.
 *
 * @author xuejunc
 * @createTime 2022/5/2 上午11:19
 */
public class Offer041 {
    /**
     * 最大堆加最小堆
     */
    public static class MedianFinder {
        private int size;
        private Heap min = new Heap(true);
        private Heap max = new Heap(false);

        /**
         * initialize your data structure here.
         */
        public MedianFinder() {
        }

        public void addNum(int num) {
            if (min.size() == 0 || num >= min.peek()) {
                min.push(num);
                if (min.size() > max.size() + 1) {
                    max.push(min.pop());
                }
            } else {
                max.push(num);
                if (max.size() > min.size()) {
                    min.push(max.pop());
                }
            }
        }

        public double findMedian() {
            if (min.size() > max.size()) {
                return min.peek();
            }
            return (min.peek() + max.peek()) / 2.0;
        }
    }

    public static class Heap {
        private int[] data;
        private int size;
        private boolean isMinHeap;

        public Heap(int[] data, boolean isMinHeap) {
            this.data = data;
            this.size = data.length;
            this.isMinHeap = isMinHeap;
            initHeap();
        }

        public Heap(boolean isMinHeap) {
            this.data = new int[16];
            this.isMinHeap = isMinHeap;
        }

        public int size() {
            return size;
        }

        private void initHeap() {
            for (int i = size >> 1; i < size; i++) {
                up(i);
            }
        }

        public int peek() {
            return data[0];
        }

        public int pop() {
            swap(0, --size);
            down(0);
            return data[size];
        }

        public void push(int num) {
            expandIfNeeded();
            data[size] = num;
            up(size);
            size++;
        }

        /**
         * 数组已满时进行扩容
         */
        private void expandIfNeeded() {
            if (size < data.length) {
                return;
            }
            int[] newData = new int[data.length << 1];
            System.arraycopy(data, 0, newData, 0, data.length);
            data = newData;
        }

        private void up(int i) {
            if (i == 0) {
                return;
            }
            int p = (i - 1) >> 1;
            if (less(data[i], data[p])) {
                swap(i, p);
                up(p);
            }
        }

        private void down(int i) {
            if (i >= size >> 1) {
                return;
            }
            int l = (i << 1) + 1, r = (i << 1) + 2;
            int min = i;
            if (less(data[l], data[i])) {
                min = l;
            }
            if (r < size && less(data[r], data[min])) {
                min = r;
            }
            if (min == l) {
                swap(i, l);
                down(l);
            } else if (min == r) {
                swap(i, r);
                down(r);
            }
        }

        private void swap(int i, int j) {
            int temp = data[i];
            data[i] = data[j];
            data[j] = temp;
        }

        private boolean less(int i, int j) {
            if (isMinHeap) {
                return i < j;
            }
            return i > j;
        }
    }

    /**
     * 有序集合+双指针
     */
    public static class MedianFinder2 {
        // 最中间的两个数。当总数为奇数时，两个数重合
        // left[0]表示具体的数字，left[1]表示在相同的数字中的排序
        private int[] left = new int[2], right = new int[2];
        private int n;
        private TreeMap<Integer, Integer> treeMap = new TreeMap<>();

        public MedianFinder2() {
        }

        public void addNum(int num) {
            treeMap.merge(num, 1, Integer::sum);
            if (n == 0) {
                left[0] = right[0] = num;
                left[1] = right[1] = 0;
                // 此前数量为偶数，left，right不重合
            } else if ((n & 1) == 0) {
                if (num >= left[0] && num < right[0]) {
                    increase(left);
                    System.arraycopy(left, 0, right, 0, 2);
                } else if (num < left[0]) {
                    System.arraycopy(left, 0, right, 0, 2);
                } else {
                    System.arraycopy(right, 0, left, 0, 2);
                }
                // 此前数量为奇数，两数重合
            } else {
                if (num >= left[0]) {
                    increase(right);
                } else {
                    decrease(left);
                }
            }
            n++;
        }

        public double findMedian() {
            return (left[0] + right[0]) / 2.0;
        }

        private void increase(int[] arr) {
            arr[1]++;
            if (arr[1] == treeMap.get(arr[0])) {
                arr[0] = treeMap.ceilingKey(arr[0] + 1);
                arr[1] = 0;
            }
        }

        private void decrease(int[] arr) {
            arr[1]--;
            if (arr[1] < 0) {
                arr[0] = treeMap.floorKey(arr[0] - 1);
                arr[1] = treeMap.get(arr[0]) - 1;
            }
        }
    }
}
