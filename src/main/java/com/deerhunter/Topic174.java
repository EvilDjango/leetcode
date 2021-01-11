package com.deerhunter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 174. Dungeon Game
 * Hard
 * <p>
 * 2054
 * <p>
 * 46
 * <p>
 * Add to List
 * <p>
 * Share
 * The demons had captured the princess (P) and imprisoned her in the bottom-right corner of a dungeon. The dungeon consists of M x N rooms laid out in a 2D grid. Our valiant knight (K) was initially positioned in the top-left room and must fight his way through the dungeon to rescue the princess.
 * <p>
 * The knight has an initial health point represented by a positive integer. If at any point his health point drops to 0 or below, he dies immediately.
 * <p>
 * Some of the rooms are guarded by demons, so the knight loses health (negative integers) upon entering these rooms; other rooms are either empty (0's) or contain magic orbs that increase the knight's health (positive integers).
 * <p>
 * In order to reach the princess as quickly as possible, the knight decides to move only rightward or downward in each step.
 * <p>
 * <p>
 * <p>
 * Write a function to determine the knight's minimum initial health so that he is able to rescue the princess.
 * <p>
 * For example, given the dungeon below, the initial health of the knight must be at least 7 if he follows the optimal path RIGHT-> RIGHT -> DOWN -> DOWN.
 * <p>
 * -2 (K)	-3	3
 * -5	-10	1
 * 10	30	-5 (P)
 * <p>
 * <p>
 * Note:
 * <p>
 * The knight's health has no upper bound.
 * Any room can contain threats or power-ups, even the first room the knight enters and the bottom-right room where the princess is imprisoned.
 * <p>
 * Copyright (c) 20120 deerhunter0837@gmail.com All Rights Reserved.
 *
 * @author xuejunc
 * @createTime 2020/12/27 21:05
 */
public class Topic174 {
    /**
     * 动态规划
     */
    public static class Solution1 {
        public int calculateMinimumHP(int[][] dungeon) {
            int m = dungeon.length;
            int n = dungeon[0].length;
            int[][] minHp = new int[m][n];
            for (int i = m - 1; i >= 0; i--) {
                for (int j = n - 1; j >= 0; j--) {

                    int need;
                    if (i == m - 1 && j == n - 1) {
                        need = 1 - dungeon[i][j];
                    } else if (i == m - 1) {
                        need = minHp[i][j + 1] - dungeon[i][j];
                    } else if (j == n - 1) {
                        need = minHp[i + 1][j] - dungeon[i][j];
                    } else {
                        need = Math.min(minHp[i + 1][j], minHp[i][j + 1]) - dungeon[i][j];
                    }
                    minHp[i][j] = need <= 0 ? 1 : need;
                }
            }
            return minHp[0][0];
        }

    }

    /**
     * 暴力算法
     */
    public static class Solution2 {
        public int calculateMinimumHP(int[][] dungeon) {
            List<Integer> minHealths = new ArrayList<>();
            calculateMinimumHP(dungeon, minHealths, 0, 0, 0, Integer.MAX_VALUE);
            int maxMinHealth = minHealths.get(0);
            for (int i = 1; i < minHealths.size(); i++) {
                maxMinHealth = Math.max(maxMinHealth, minHealths.get(i));
            }
            return maxMinHealth > 0 ? 1 : 1 - maxMinHealth;
        }

        private void calculateMinimumHP(int[][] dungeon, List<Integer> minHealths, int row, int col, int cost, int minHealth) {
            cost += dungeon[row][col];
            minHealth = Math.min(minHealth, cost);
            if (row == dungeon.length - 1 && col == dungeon[0].length - 1) {
                minHealths.add(minHealth);
                return;
            }
            if (col != dungeon[0].length - 1) {
                calculateMinimumHP(dungeon, minHealths, row, col + 1, cost, minHealth);
            }
            if (row != dungeon.length - 1) {
                calculateMinimumHP(dungeon, minHealths, row + 1, col, cost, minHealth);
            }
        }
    }

    /**
     * 参考官方题解，本质上痛Solution1一样
     */
    public static class Solution3 {
        public int calculateMinimumHP(int[][] dungeon) {
            int m = dungeon.length;
            int n = dungeon[0].length;
            int[][] health = new int[m + 1][n + 1];
            for (int i = 0; i <= m; i++) {
                Arrays.fill(health[i], Integer.MAX_VALUE);
            }
            health[m][n - 1] = health[m - 1][n] = 1;
            for (int i = m - 1; i >= 0; i--) {
                for (int j = n - 1; j >= 0; j--) {
                    health[i][j] = Math.max(1, Math.min(health[i][j + 1], health[i + 1][j]) - dungeon[i][j]);
                }
            }
            return health[0][0];
        }
    }
}

