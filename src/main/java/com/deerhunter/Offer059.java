package com.deerhunter;

import java.util.ArrayDeque;
import java.util.Comparator;
import java.util.Deque;
import java.util.PriorityQueue;

/**
 * Oops, forgot to write comments. Good luck, bro.
 * <p>
 * Copyright (c) @2022 deerhunter0837@gmail.com All Rights Reserved.
 *
 * @author xuejunc
 * @createTime 2022/4/22 上午11:55
 */
public class Offer059 {
    /**
     * 单调队列
     *
     * @param nums
     * @param k
     * @return
     */
    public int[] maxSlidingWindow(int[] nums, int k) {
        int n = nums.length;
        if (n == 0) {
            return new int[0];
        }
        Deque<Integer> maxValues = new ArrayDeque<>();
        int[] ans = new int[n - k + 1];
        for (int i = 0; i < nums.length; i++) {
            while (!maxValues.isEmpty() && maxValues.peekLast() < nums[i]) {
                maxValues.removeLast();
            }
            maxValues.add(nums[i]);
            if (i >= k && maxValues.peekFirst() == nums[i - k]) {
                maxValues.removeFirst();
            }
            if (i >= k - 1) {
                ans[i - k + 1] = maxValues.peekFirst();
            }
        }
        return ans;
    }

    /**
     * 优先队列
     *
     * @param nums
     * @param k
     * @return
     */
    public int[] maxSlidingWindow2(int[] nums, int k) {
        int n = nums.length;
        if (n == 0) {
            return new int[0];
        }
        PriorityQueue<int[]> queue = new PriorityQueue<>((o1, o2) -> o2[0] - o1[0]);
        int[] ans = new int[n - k + 1];
        for (int i = 0; i < n; i++) {
            queue.add(new int[]{nums[i], i});
            while (queue.peek()[1] < i - k) {
                queue.remove();
            }
            if (i - k + 1 >= 0) {
                ans[i - k + 1] = queue.peek()[0];
            }
        }
        return ans;
    }

    /**
     * 分块预处理，将数组分成若干个长度为k的连续块，分别计算每个块的前缀最大值、后缀最大值。
     * 区间 [i,i+k-1]的最大值等于 i所在块的后缀最大值和i+k-1所在块的前缀最大值这两者取最大值。
     *
     * @param nums
     * @param k
     * @return
     */
    public int[] maxSlidingWindow3(int[] nums, int k) {
        int n = nums.length;
        if (n == 0) {
            return new int[0];
        }
        int[] prefix = new int[n];
        int[] suffix = new int[n];
        for (int i = 0; i < n; i++) {
            if (i % k == 0) {
                prefix[i] = nums[i];
            } else {
                prefix[i] = Math.max(prefix[i - 1], nums[i]);
            }
        }
        suffix[n - 1] = nums[n - 1];
        for (int i = n - 2; i >= 0; i--) {
            if (i % k == 0) {
                suffix[i] = nums[i];
            } else {
                suffix[i] = Math.max(suffix[i + 1], nums[i]);
            }
        }
        int[] ans = new int[n - k + 1];
        for (int i = k - 1; i < n; i++) {
            ans[i - k + 1] = Math.max(suffix[i - k + 1], prefix[i]);
        }
        return ans;
    }
}
