package com.deerhunter;

import java.util.HashMap;
import java.util.Map;

/**
 * 149. 直线上最多的点数
 * 给定一个二维平面，平面上有 n 个点，求最多有多少个点在同一条直线上。
 * <p>
 * 示例 1:
 * <p>
 * 输入: [[1,1],[2,2],[3,3]]
 * 输出: 3
 * 解释:
 * ^
 * |
 * |        o
 * |     o
 * |  o
 * +------------->
 * 0  1  2  3  4
 * 示例 2:
 * <p>
 * 输入: [[1,1],[3,2],[5,3],[4,1],[2,3],[1,4]]
 * 输出: 4
 * 解释:
 * ^
 * |
 * |  o
 * |     o        o
 * |        o
 * |  o        o
 * +------------------->
 * 0  1  2  3  4  5  6
 * 通过次数17,231提交次数74,222
 * <p>
 * Copyright (c) 20120 deerhunter0837@gmail.com All Rights Reserved.
 *
 * @author xuejunc
 * @createTime 2020/11/6 15:10
 */
public class Topic149 {
    /**
     * 暴力算法，由于计算机无法精确保存小数，所以此算法无法ac
     */
    public static class Solution1 {
        public int maxPoints(int[][] points) {
            int maxPoints = 0;
            int m = points.length;
            if (m < 3) {
                return m;
            }
            for (int i = 0; i < m; i++) {
                for (int j = i + 1; j < m; j++) {
                    double[] line = getLine(points[i], points[j]);
                    int count = 2;
                    for (int k = 0; k < m; k++) {
                        if (k == i || k == j) {
                            continue;
                        }
                        if (line == null && points[k][0] == points[i][0]) {
                            count++;
                        }
                        if (line != null && isInLine(line[0], line[1], points[k])) {
                            count++;
                        }
                    }
                    maxPoints = Math.max(maxPoints, count);
                }
            }
            return maxPoints;
        }

        /**
         * 检查点point是否在直线y=kx+b上
         *
         * @param k
         * @param b
         * @param point
         * @return
         */
        private boolean isInLine(double k, double b, int[] point) {
            return point[1] == k * point[0] + b;
        }

        /**
         * 计算过两点的直线
         *
         * @param p1
         * @param p2
         * @return int[], 第一位和第二位分别对应直线函数y=kx+b 中的k和b,若直线与x轴平行，即x=b时，返回null
         */
        private double[] getLine(int[] p1, int[] p2) {
            if (p1[0] == p2[0]) {
                return null;
            }
            double k = (p1[1] - p2[1]) / (p1[0] - p2[0] + 0D);
            double b = p1[1] - k * p1[0];
            return new double[]{k, b};
        }
    }

    /**
     * 参考优秀题解
     * 依次计算经过点i的直线最多包含的已知点数
     * 计算点i+1时，由于直线（i，i+1）已经计算过，所以只需从i+2开始考察
     * 这里使用点斜式y-y1=k(x-x1) 即k=(y-y1)/(x-x1),由于计算机无法保存小数的精确值，所以我们对把分子和分母约分到最简，然后生成一个字符串
     * "分母@分子"来精确表示k。约分时需要先求分子和分母的最大公约数
     */
    public static class Solution2 {
        public int maxPoints(int[][] points) {
            int n = points.length;
            if (n < 3) {
                return n;
            }

            int result = 2;
            for (int i = 0; i < n; i++) {
                int[] p1 = points[i];
                // key为斜率，value表示经过p1且斜率为key的直线上的点数
                Map<String, Integer> count = new HashMap<>();
                // 与p1相同的点数
                int duplicates = 0;
                // 位于经过p1，垂直于x轴的直线上的点数
                int verticals = 0;
                int maxPoints = 0;
                for (int j = i + 1; j < n; j++) {
                    int[] p2 = points[j];
                    if (p1[0] == p2[0] && p1[1] == p2[1]) {
                        duplicates++;
                    } else if (p2[1] == p1[1]) {
                        verticals++;
                    } else {
                        int deltaY = p2[1] - p1[1];
                        int deltaX = p2[0] - p1[0];
                        int gcd = gcd(deltaY, deltaX);
                        String k = deltaY / gcd + "@" + deltaX / gcd;
                        count.merge(k, 1, Integer::sum);
                        maxPoints = Math.max(maxPoints, count.get(k));
                    }
                }
                maxPoints = Math.max(maxPoints, verticals) + 1 + duplicates;
                result = Math.max(maxPoints, result);
            }
            return result;
        }

        /**
         * 求最大公约数
         *
         * @param a
         * @param b
         * @return
         */
        private int gcd(int a, int b) {
            while (b != 0) {
                int temp = a % b;
                a = b;
                b = temp;
            }
            return a;
        }
    }
}
