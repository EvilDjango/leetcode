package com.deerhunter;

/**
 * LCS 03. 主题空间
 * 「以扣会友」线下活动所在场地由若干主题空间与走廊组成，场地的地图记作由一维字符串型数组 grid，字符串中仅包含 "0"～"5" 这 6 个字符。地图上每一个字符代表面积为 1 的区域，其中 "0" 表示走廊，其他字符表示主题空间。相同且连续（连续指上、下、左、右四个方向连接）的字符组成同一个主题空间。
 * <p>
 * 假如整个 grid 区域的外侧均为走廊。请问，不与走廊直接相邻的主题空间的最大面积是多少？如果不存在这样的空间请返回 0。
 * <p>
 * 示例 1:
 * <p>
 * 输入：grid = ["110","231","221"]
 * <p>
 * 输出：1
 * <p>
 * 解释：4 个主题空间中，只有 1 个不与走廊相邻，面积为 1。
 * image.png
 * <p>
 * 示例 2:
 * <p>
 * 输入：grid = ["11111100000","21243101111","21224101221","11111101111"]
 * <p>
 * 输出：3
 * <p>
 * 解释：8 个主题空间中，有 5 个不与走廊相邻，面积分别为 3、1、1、1、2，最大面积为 3。
 * image.png
 * <p>
 * 提示：
 * <p>
 * 1 <= grid.length <= 500
 * 1 <= grid[i].length <= 500
 * grid[i][j] 仅可能是 "0"～"5"
 * 通过次数2,635提交次数6,371
 * <p>
 * Copyright (c) @2022 deerhunter0837@gmail.com All Rights Reserved.
 *
 * @author xuejunc
 * @createTime 2022/6/4 上午10:31
 */
public class LCS003 {
    public int largestArea(String[] grid) {
        int m = grid.length;
        int n = grid[0].length();
        int max = 0;
        boolean[][] visited = new boolean[m][n];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                max = Math.max(max, bfs(grid[i].charAt(j), grid, i, j, visited));
            }
        }
        return max;
    }

    /**
     * 广度优先遍历。从某个位置出发，查找主题空间面积。若与边界相邻或者与0相邻，则返回-1。
     * 需要注意的是，这里遇到边界或者0后不能直接返回，还是要继续将主题空间遍历完成而不能提前返回。否则会误判后面剩余的主题空间不语走廊相邻。
     *
     * @param theme
     * @param grid
     * @param i
     * @param j
     * @param visited
     * @return
     */
    private int bfs(char theme, String[] grid, int i, int j, boolean[][] visited) {
        // 越界直接返回
        if (i < 0 || i == visited.length || j < 0 || j == visited[0].length) {
            return 0;
        }
        // 与走廊相邻。注意这个位置即使被访问过了也不影响我们判断当前主题相邻，所以要放在已访问判断之前。
        if (grid[i].charAt(j) == '0') {
            return -1;
        }
        if (visited[i][j] || grid[i].charAt(j) != theme) {
            return 0;
        }
        // 边界位置认为与走廊相邻。
        boolean adjacentPassage = i == 0 || i == visited.length - 1 || j == 0 || j == visited[0].length - 1;
        visited[i][j] = true;
        int[][] dirs = new int[][]{{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
        int area = 1;
        for (int[] dir : dirs) {
            int subArea = bfs(grid[i].charAt(j), grid, i + dir[0], j + dir[1], visited);
            if (subArea == -1) {
                adjacentPassage = true;
            }
            area += subArea;
        }
        return adjacentPassage ? -1 : area;
    }
}
