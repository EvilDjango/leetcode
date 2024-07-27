package com.deerhunter.topic;

import java.util.ArrayList;
import java.util.List;

/**
 * n 皇后问题研究的是如何将 n 个皇后放置在 n×n 的棋盘上，并且使皇后彼此之间不能相互攻击。
 * <p>
 * <p>
 * <p>
 * 上图为 8 皇后问题的一种解法。
 * <p>
 * 给定一个整数 n，返回所有不同的 n 皇后问题的解决方案。
 * <p>
 * 每一种解法包含一个明确的 n 皇后问题的棋子放置方案，该方案中 'Q' 和 '.' 分别代表了皇后和空位。
 * <p>
 * 示例:
 * <p>
 * 输入: 4
 * 输出: [
 * [".Q..",  // 解法 1
 * "...Q",
 * "Q...",
 * "..Q."],
 * <p>
 * ["..Q.",  // 解法 2
 * "Q...",
 * "...Q",
 * ".Q.."]
 * ]
 * 解释: 4 皇后问题存在两个不同的解法。
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/n-queens
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * <p>
 * Copyright (c) 2019, deerhunter0837@gmail.com All Rights Reserved.
 *
 * @author xuejunc
 * @date 2020-03-07
 */
public class Topic051 {
    public static class Solution1 {
        public List<List<String>> solveNQueens(int n) {
            List<List<String>> ans = new ArrayList<>();
            backTrack(n, 0, new boolean[n], new boolean[2 * n - 1], new boolean[2 * n - 1], new boolean[n][n], ans);
            return ans;
        }

        private void backTrack(int n, int i, boolean[] col, boolean[] leftDiagonal, boolean[] rightDiagonal, boolean[][] temp, List<List<String>> result) {
            if (i == n) {
                List<String> solution = new ArrayList<>();
                for (boolean[] booleans : temp) {
                    StringBuilder sb = new StringBuilder();
                    for (boolean b : booleans) {
                        sb.append(b ? "Q" : ".");
                    }
                    solution.add(sb.toString());
                }
                result.add(solution);
                return;
            }
            // 在第i行查找一个可以符合规则摆放一个皇后的位置
            for (int j = 0; j < n; j++) {
                int leftDiagonalIndex = i - j + n - 1;
                int rightDiagonalIndex = i + j;
                if (col[j] || leftDiagonal[leftDiagonalIndex] || rightDiagonal[rightDiagonalIndex]) {
                    continue;
                }
                col[j] = true;
                leftDiagonal[leftDiagonalIndex] = true;
                rightDiagonal[rightDiagonalIndex] = true;
                temp[i][j] = true;
                backTrack(n, i + 1, col, leftDiagonal, rightDiagonal, temp, result);
                col[j] = false;
                leftDiagonal[leftDiagonalIndex] = false;
                rightDiagonal[rightDiagonalIndex] = false;
                temp[i][j] = false;
            }
        }
    }
}
