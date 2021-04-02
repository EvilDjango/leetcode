package com.deerhunter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.TreeMap;

/**
 * 218. 天际线问题
 * 城市的天际线是从远处观看该城市中所有建筑物形成的轮廓的外部轮廓。给你所有建筑物的位置和高度，请返回由这些建筑物形成的 天际线 。
 * <p>
 * 每个建筑物的几何信息由数组 buildings 表示，其中三元组 buildings[i] = [lefti, righti, heighti] 表示：
 * <p>
 * lefti 是第 i 座建筑物左边缘的 x 坐标。
 * righti 是第 i 座建筑物右边缘的 x 坐标。
 * heighti 是第 i 座建筑物的高度。
 * 天际线 应该表示为由 “关键点” 组成的列表，格式 [[x1,y1],[x2,y2],...] ，并按 x 坐标 进行 排序 。关键点是水平线段的左端点。列表中最后一个点是最右侧建筑物的终点，y 坐标始终为 0 ，仅用于标记天际线的终点。此外，任何两个相邻建筑物之间的地面都应被视为天际线轮廓的一部分。
 * <p>
 * 注意：输出天际线中不得有连续的相同高度的水平线。例如 [...[2 3], [4 5], [7 5], [11 5], [12 7]...] 是不正确的答案；三条高度为 5 的线应该在最终输出中合并为一个：[...[2 3], [4 5], [12 7], ...]
 * <p>
 * <p>
 * <p>
 * 示例 1：
 * <p>
 * <p>
 * 输入：buildings = [[2,9,10],[3,7,15],[5,12,12],[15,20,10],[19,24,8]]
 * 输出：[[2,10],[3,15],[7,12],[12,0],[15,10],[20,8],[24,0]]
 * 解释：
 * 图 A 显示输入的所有建筑物的位置和高度，
 * 图 B 显示由这些建筑物形成的天际线。图 B 中的红点表示输出列表中的关键点。
 * 示例 2：
 * <p>
 * 输入：buildings = [[0,2,3],[2,5,3]]
 * 输出：[[0,3],[5,0]]
 * <p>
 * <p>
 * 提示：
 * <p>
 * 1 <= buildings.length <= 104
 * 0 <= lefti < righti <= 231 - 1
 * 1 <= heighti <= 231 - 1
 * buildings 按 lefti 非递减排序
 * <p>
 * Copyright (c) 20120 deerhunter0837@gmail.com All Rights Reserved.
 *
 * @author xuejunc
 * @createTime 2021/3/30 18:12
 */
public class Topic218 {

    /**
     * 分治,复制的官方题解
     */
    public static class Solution1 {
        /**
         * Divide-and-conquer algorithm to solve skyline problem,
         * which is similar with the merge sort algorithm.
         */
        public List<List<Integer>> getSkyline(int[][] buildings) {
            int n = buildings.length;
            List<List<Integer>> output = new ArrayList<List<Integer>>();

            // The base cases
            if (n == 0) return output;
            if (n == 1) {
                int xStart = buildings[0][0];
                int xEnd = buildings[0][1];
                int y = buildings[0][2];

                output.add(new ArrayList<Integer>() {{
                    add(xStart);
                    add(y);
                }});
                output.add(new ArrayList<Integer>() {{
                    add(xEnd);
                    add(0);
                }});
                return output;
            }

            // If there is more than one building,
            // recursively divide the input into two subproblems.
            List<List<Integer>> leftSkyline, rightSkyline;
            leftSkyline = getSkyline(Arrays.copyOfRange(buildings, 0, n / 2));
            rightSkyline = getSkyline(Arrays.copyOfRange(buildings, n / 2, n));

            // Merge the results of subproblem together.
            return mergeSkylines(leftSkyline, rightSkyline);
        }

        /**
         * Merge two skylines together.
         */
        public List<List<Integer>> mergeSkylines(List<List<Integer>> left, List<List<Integer>> right) {
            int nL = left.size(), nR = right.size();
            int pL = 0, pR = 0;
            int currY = 0, leftY = 0, rightY = 0;
            int x, maxY;
            List<List<Integer>> output = new ArrayList<List<Integer>>();

            // while we're in the region where both skylines are present
            while ((pL < nL) && (pR < nR)) {
                List<Integer> pointL = left.get(pL);
                List<Integer> pointR = right.get(pR);
                // pick up the smallest x
                if (pointL.get(0) < pointR.get(0)) {
                    x = pointL.get(0);
                    leftY = pointL.get(1);
                    pL++;
                } else {
                    x = pointR.get(0);
                    rightY = pointR.get(1);
                    pR++;
                }
                // max height (i.e. y) between both skylines
                maxY = Math.max(leftY, rightY);
                // update output if there is a skyline change
                if (currY != maxY) {
                    updateOutput(output, x, maxY);
                    currY = maxY;
                }
            }

            // there is only left skyline
            appendSkyline(output, left, pL, nL, currY);

            // there is only right skyline
            appendSkyline(output, right, pR, nR, currY);

            return output;
        }

        /**
         * Update the final output with the new element.
         */
        public void updateOutput(List<List<Integer>> output, int x, int y) {
            // if skyline change is not vertical -
            // add the new point
            if (output.isEmpty() || output.get(output.size() - 1).get(0) != x)
                output.add(new ArrayList<Integer>() {{
                    add(x);
                    add(y);
                }});
                // if skyline change is vertical -
                // update the last point
            else {
                output.get(output.size() - 1).set(1, y);
            }
        }

        /**
         * Append the rest of the skyline elements with indice (p, n)
         * to the final output.
         */
        public void appendSkyline(List<List<Integer>> output, List<List<Integer>> skyline,
                                  int p, int n, int currY) {
            while (p < n) {
                List<Integer> point = skyline.get(p);
                int x = point.get(0);
                int y = point.get(1);
                p++;

                // update output
                // if there is a skyline change
                if (currY != y) {
                    updateOutput(output, x, y);
                    currY = y;
                }
            }
        }
    }

    /**
     * 自己写的分治算法
     */
    public static class Solution2 {
        /**
         * Divide-and-conquer algorithm to solve skyline problem,
         * which is similar with the merge sort algorithm.
         */
        public List<List<Integer>> getSkyline(int[][] buildings) {
            return getSkyline(buildings, 0, buildings.length);
        }

        private List<List<Integer>> getSkyline(int[][] buildings, int left, int right) {
            if (left == right) {
                return new ArrayList<>();
            } else if (left + 1 == right) {
                List<List<Integer>> ans = new ArrayList<>();
                ans.add(Arrays.asList(buildings[left][0], buildings[left][2]));
                ans.add(Arrays.asList(buildings[left][1], 0));
                return ans;
            }
            int middle = left + (right - left) / 2;
            List<List<Integer>> leftSkyline = getSkyline(buildings, left, middle);
            List<List<Integer>> rightSkyline = getSkyline(buildings, middle, right);
            return mergeSkyline(leftSkyline, rightSkyline);
        }

        private List<List<Integer>> mergeSkyline(List<List<Integer>> leftSkyline, List<List<Integer>> rightSkyline) {
            List<List<Integer>> output = new ArrayList<>();
            int nL = leftSkyline.size(), nR = rightSkyline.size();
            int pL = 0, pR = 0;
            int leftY = 0, rightY = 0, currY = 0;
            while (pL < nL && pR < nR) {
                List<Integer> pointL = leftSkyline.get(pL);
                List<Integer> pointR = rightSkyline.get(pR);
                int x;
                if (pointL.get(0) < pointR.get(0)) {
                    x = pointL.get(0);
                    leftY = pointL.get(1);
                    pL++;
                } else {
                    x = pointR.get(0);
                    rightY = pointR.get(1);
                    pR++;
                }
                int maxY = Math.max(leftY, rightY);
                if (maxY != currY) {
                    updateOutput(output, Arrays.asList(x, maxY));
                }
                currY = maxY;
            }

            append(leftSkyline, output, pL, currY);
            append(rightSkyline, output, pR, currY);
            return output;
        }

        private void updateOutput(List<List<Integer>> output, List<Integer> point) {
            if (output.size() == 0) {
                output.add(point);
                return;
            }
            List<Integer> last = output.get(output.size() - 1);
            if (last.get(0).equals(point.get(0))) {
                last.set(1, point.get(1));
            } else {
                output.add(point);
            }

        }

        private void append(List<List<Integer>> skyline, List<List<Integer>> output, int index, int currY) {
            while (index < skyline.size()) {
                List<Integer> point = skyline.get(index);
                if (point.get(1) != currY) {
                    updateOutput(output, point);
                    currY = point.get(1);
                }
                index++;
            }
        }
    }

    /**
     * 线扫描法
     */
    public static class Solution3 {
        public static class Endpoint implements Comparable<Endpoint> {
            int x;
            int h;

            public Endpoint(int x, int h) {
                this.x = x;
                this.h = h;
            }

            @Override
            public int compareTo(Endpoint o) {
                return this.x - o.x;
            }
        }

        public List<List<Integer>> getSkyline(int[][] buildings) {
            List<List<Integer>> ans = new ArrayList<>();
            Queue<Endpoint> leftPoints = new PriorityQueue<>();
            Queue<Endpoint> rightPoints = new PriorityQueue<>();
            for (int[] building : buildings) {
                leftPoints.add(new Endpoint(building[0], building[2]));
                rightPoints.add(new Endpoint(building[1], building[2]));
            }
            Queue<Integer> heights = new PriorityQueue<>(Comparator.reverseOrder());
            int prevHeight = 0;
            heights.add(0);
            while (!rightPoints.isEmpty()) {
                int x;
                if (!leftPoints.isEmpty() && leftPoints.peek().x <= rightPoints.peek().x) {
                    x = leftPoints.peek().x;
                    heights.add(leftPoints.remove().h);
                } else {
                    x = rightPoints.peek().x;
                    heights.remove(rightPoints.remove().h);
                }
                Integer height = heights.peek();
                if (height != prevHeight) {
                    update(ans, x, height);
                    prevHeight = height;
                }
            }
            return ans;

        }

        private void update(List<List<Integer>> ans, int x, Integer height) {
            if (!ans.isEmpty() && ans.get(ans.size() - 1).get(0) == x) {
                ans.get(ans.size() - 1).set(1, height);
            } else {
                ans.add(Arrays.asList(x, height));
            }
        }
    }

    /**
     * 线扫描法，参考优秀题解
     */
    public static class Solution4 {
        public List<List<Integer>> getSkyline(int[][] buildings) {
            List<List<Integer>> ans = new ArrayList<>();
            Queue<int[]> points = new PriorityQueue<>((a, b) -> a[0] == b[0] ? a[1] - b[1] : a[0] - b[0]);
            for (int[] building : buildings) {
                points.add(new int[]{building[0], -building[2]});
                points.add(new int[]{building[1], building[2]});
            }
            TreeMap<Integer, Integer> heights = new TreeMap<>((a, b) -> b - a);
            heights.put(0, 1);
            int height = 0;
            while (!points.isEmpty()) {
                int[] point = points.remove();
                if (point[1] < 0) {
                    heights.merge(-point[1], 1, Integer::sum);
                } else {
                    heights.merge(point[1], -1, Integer::sum);
                    if (heights.get(point[1]) == 0) {
                        heights.remove(point[1]);
                    }
                }
                int maxHeight = heights.keySet().iterator().next();
                if (maxHeight != height) {
                    ans.add(Arrays.asList(point[0], maxHeight));
                    height = maxHeight;
                }
            }
            return ans;
        }
    }
}
