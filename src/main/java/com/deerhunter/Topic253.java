package com.deerhunter;

import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * 253. 会议室 II
 * 给你一个会议时间安排的数组 intervals ，每个会议时间都会包括开始和结束的时间 intervals[i] = [starti, endi] ，为避免会议冲突，同时要考虑充分利用会议室资源，请你计算至少需要多少间会议室，才能满足这些会议安排。
 * <p>
 * <p>
 * <p>
 * 示例 1：
 * <p>
 * 输入：intervals = [[0,30],[5,10],[15,20]]
 * 输出：2
 * 示例 2：
 * <p>
 * 输入：intervals = [[7,10],[2,4]]
 * 输出：1
 * <p>
 * <p>
 * 提示：
 * <p>
 * 1 <= intervals.length <= 104
 * 0 <= starti < endi <= 106
 * 通过次数27,706提交次数56,187
 * <p>
 * Copyright (c) @2021 deerhunter0837@gmail.com All Rights Reserved.
 *
 * @author xuejunc
 * @createTime 7/27/21 1:02 PM
 */
public class Topic253 {
    /**
     * 使用优先队列
     */
    public static class Solution1 {
        public int minMeetingRooms(int[][] intervals) {
            Arrays.sort(intervals, Comparator.comparingInt(o -> o[0]));
            PriorityQueue<Integer> priorityQueue = new PriorityQueue<>();
            for (int[] interval : intervals) {
                if (!priorityQueue.isEmpty() && priorityQueue.peek() <= interval[0]) {
                    priorityQueue.remove();
                }
                priorityQueue.add(interval[1]);
            }
            return priorityQueue.size();
        }
    }

    public static class Solution2 {
        public int minMeetingRooms(int[][] intervals) {
            int n = intervals.length;
            int[] starts = new int[n];
            int[] ends = new int[n];
            for (int i = 0; i < n; i++) {
                starts[i] = intervals[i][0];
                ends[i] = intervals[i][1];
            }
            Arrays.sort(starts);
            Arrays.sort(ends);
            int rooms = 0;
            for (int i = 0, j = 0; i < n; i++) {
                if (starts[i] < ends[j]) {
                    rooms++;
                } else {
                    j++;
                }
            }
            return rooms;
        }
    }
}
