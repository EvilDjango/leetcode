package com.deerhunter.topic;

import java.util.Arrays;

/**
 * 200. 岛屿数量
 * 给你一个由 '1'（陆地）和 '0'（水）组成的的二维网格，请你计算网格中岛屿的数量。
 * <p>
 * 岛屿总是被水包围，并且每座岛屿只能由水平方向和/或竖直方向上相邻的陆地连接形成。
 * <p>
 * 此外，你可以假设该网格的四条边均被水包围。
 * <p>
 * <p>
 * <p>
 * 示例 1：
 * <p>
 * 输入：grid = [
 * ["1","1","1","1","0"],
 * ["1","1","0","1","0"],
 * ["1","1","0","0","0"],
 * ["0","0","0","0","0"]
 * ]
 * 输出：1
 * 示例 2：
 * <p>
 * 输入：grid = [
 * ["1","1","0","0","0"],
 * ["1","1","0","0","0"],
 * ["0","0","1","0","0"],
 * ["0","0","0","1","1"]
 * ]
 * 输出：3
 * <p>
 * <p>
 * 提示：
 * <p>
 * m == grid.length
 * n == grid[i].length
 * 1 <= m, n <= 300
 * grid[i][j] 的值为 '0' 或 '1'
 * <p>
 * Copyright (c) 20120 deerhunter0837@gmail.com All Rights Reserved.
 *
 * @author xuejunc
 * @createTime 2021/1/20 17:03
 */
public class Topic200 {
    public static class Solution1 {
        public int numIslands(char[][] grid) {
            int m = grid.length;
            if (m == 0) {
                return 0;
            }
            int n = grid[0].length;
            boolean[][] visited = new boolean[m][n];
            int count = 0;
            for (int i = 0; i < m; i++) {
                for (int j = 0; j < n; j++) {
                    if (grid[i][j] == '1' && !visited[i][j]) {
                        count++;
                        dfs(grid, visited, i, j);
                    }
                }
            }
            return count;
        }

        private void dfs(char[][] grid, boolean[][] visited, int i, int j) {
            if (i < 0 || i >= grid.length || j < 0 || j >= grid[0].length || grid[i][j] != '1' || visited[i][j]) {
                return;
            }
            visited[i][j] = true;
            dfs(grid, visited, i - 1, j);
            dfs(grid, visited, i + 1, j);
            dfs(grid, visited, i, j - 1);
            dfs(grid, visited, i, j + 1);
        }
    }

    /**
     * 并查集
     */
    public static class Solution2 {
        private static class UnionFind {
            private final int[] parent;
            private int count;

            public UnionFind(char[][] grid) {
                int rows = grid.length;
                int cols = grid[0].length;
                parent = new int[rows * cols];
                for (int i = 0; i < rows; i++) {
                    for (int j = 0; j < cols; j++) {
                        if (grid[i][j] == '1') {
                            parent[i * cols + j] = i * cols + j;
                            count++;
                        }
                    }
                }
            }

            public int find(int i) {
                if (parent[i] != i) {
                    parent[i] = find(parent[i]);
                }
                return parent[i];
            }

            public void union(int x, int y) {

                int rootX = find(x);
                int rootY = find(y);
                if (rootX == rootY) {
                    return;
                }
                count--;
                if (rootX > rootY) {
                    parent[rootX] = rootY;
                } else {
                    parent[rootY] = rootX;
                }
            }
        }

        public int numIslands(char[][] grid) {
            int m = grid.length;
            if (m == 0) {
                return 0;
            }
            int n = grid[0].length;
            UnionFind unionFind = new UnionFind(grid);
            for (int i = 0; i < m; i++) {
                for (int j = 0; j < n; j++) {
                    if (grid[i][j] == '1') {
                        if (i + 1 < m && grid[i + 1][j] == '1') {
                            unionFind.union(getFlatIndex(n, i, j), getFlatIndex(n, i + 1, j));
                        }
                        if (j + 1 < n && grid[i][j + 1] == '1') {
                            unionFind.union(getFlatIndex(n, i, j), getFlatIndex(n, i, j + 1));
                        }
                    }
                }
            }
            return unionFind.count;
        }

        /**
         * 获取二维数组中某个元素对应于展开后的一维数组的索引
         *
         * @param cols
         * @param row
         * @param col
         * @return
         */
        private int getFlatIndex(int cols, int row, int col) {
            return row * cols + col;
        }
    }
}
