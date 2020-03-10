package com.deerhunter;

import sun.jvm.hotspot.utilities.Interval;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;
import java.util.function.IntFunction;
import java.util.stream.Collectors;

/**
 * 给出一个区间的集合，请合并所有重叠的区间。
 * <p>
 * 示例 1:
 * <p>
 * 输入: [[1,3],[2,6],[8,10],[15,18]]
 * 输出: [[1,6],[8,10],[15,18]]
 * 解释: 区间 [1,3] 和 [2,6] 重叠, 将它们合并为 [1,6].
 * 示例 2:
 * <p>
 * 输入: [[1,4],[4,5]]
 * 输出: [[1,5]]
 * 解释: 区间 [1,4] 和 [4,5] 可被视为重叠区间。
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/merge-intervals
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * <p>
 * Copyright (c) 2019, deerhunter0837@gmail.com All Rights Reserved.
 *
 * @author xuejunc
 * @date 2020-03-10
 */
public class Topic056 {
    /**
     * 先按照左边界升序排序，然后从左到右依次聚合
     */
    public static class Solution1 {
        public int[][] merge(int[][] intervals) {
            List<int[]> ans = new ArrayList<>(Arrays.asList(intervals));
            ans.sort(Comparator.comparingInt(o -> o[0]));
            for (int i = 1; i < ans.size(); i++) {
                int[] last = ans.get(i - 1);
                int[] interval = ans.get(i);
                if (last[1] >= interval[0]) {
                    ans.set(i - 1, null);
                    ans.set(i, new int[]{last[0], Math.max(interval[1], last[1])});
                }
            }

            return ans.stream().filter(Objects::nonNull).collect(Collectors.toList()).toArray(new int[0][0]);
        }

    }

    /**
     * 时间复杂度为O(n2)的笨办法
     */
    public static class Solution2 {
        public int[][] merge(int[][] intervals) {
            List<int[]> ans = new ArrayList<>(Arrays.asList(intervals));
            // 最多聚合n-1次
            for (int i = 0; i < ans.size() - 1; i++) {
                int[] cur = intervals[i];
                for (int j = i+1; j < ans.size(); j++) {
                    int[] after = intervals[j];
                    if ((cur[0] >= after[0] && cur[0] <= after[1]) || (after[0] >= cur[0] && after[0] <= cur[1])) {
                        after[0] = Math.min(cur[0], after[0]);
                        after[1] = Math.max(cur[1], after[1]);
                        ans.set(i, null);
                        break;
                    }
                }
            }

            return ans.stream().filter(Objects::nonNull).collect(Collectors.toList()).toArray(new int[0][0]);
        }

    }

}
