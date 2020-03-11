package com.deerhunter;

/**
 * 给出一个无重叠的 ，按照区间起始端点排序的区间列表。
 * <p>
 * 在列表中插入一个新的区间，你需要确保列表中的区间仍然有序且不重叠（如果有必要的话，可以合并区间）。
 * <p>
 * 示例 1:
 * <p>
 * 输入: intervals = [[1,3],[6,9]], newInterval = [2,5]
 * 输出: [[1,5],[6,9]]
 * 示例 2:
 * <p>
 * 输入: intervals = [[1,2],[3,5],[6,7],[8,10],[12,16]], newInterval = [4,8]
 * 输出: [[1,2],[3,10],[12,16]]
 * 解释: 这是因为新的区间 [4,8] 与 [3,5],[6,7],[8,10] 重叠。
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/insert-interval
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * <p>
 * Copyright (c) 2019, deerhunter0837@gmail.com All Rights Reserved.
 *
 * @author xuejunc
 * @date 2020-03-11
 */
public class Topic057 {
    public static class Solution1 {
        public int[][] insert(int[][] intervals, int[] newInterval) {
            int len = intervals.length;
            // 左侧最靠近新区间并且无重叠的区间下标
            int left = -1;
            while (left + 1 < len && intervals[left + 1][1] < newInterval[0]) {
                left++;
            }
            // 右侧最靠近新区间并且无重叠的区间下标
            int right = len;
            while (right - 1 >= 0 && intervals[right - 1][0] > newInterval[1]) {
                right--;
            }
            // 新区间下标
            int index = left + 1;
            int[] merged;
            if (left + 1 == right) {
                merged = newInterval;
            } else {
                merged = new int[]{Math.min(intervals[left + 1][0], newInterval[0]), Math.max(intervals[right - 1][1], newInterval[1])};
            }
            int[][] ans = new int[left + 1 + len - right + 1][];
            System.arraycopy(intervals, 0, ans, 0, left + 1);
            ans[index] = merged;
            System.arraycopy(intervals, right, ans, index + 1, len - right);
            return ans;
        }
    }
}
