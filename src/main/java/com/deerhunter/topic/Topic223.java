package com.deerhunter.topic;

/**
 * 223. 矩形面积
 * 给你 二维 平面上两个 由直线构成的 矩形，请你计算并返回两个矩形覆盖的总面积。
 * <p>
 * 每个矩形由其 左下 顶点和 右上 顶点坐标表示：
 * <p>
 * 第一个矩形由其左下顶点 (ax1, ay1) 和右上顶点 (ax2, ay2) 定义。
 * 第二个矩形由其左下顶点 (bx1, by1) 和右上顶点 (bx2, by2) 定义。
 * <p>
 * <p>
 * 示例 1：
 * <p>
 * Rectangle Area
 * 输入：ax1 = -3, ay1 = 0, ax2 = 3, ay2 = 4, bx1 = 0, by1 = -1, bx2 = 9, by2 = 2
 * 输出：45
 * 示例 2：
 * <p>
 * 输入：ax1 = -2, ay1 = -2, ax2 = 2, ay2 = 2, bx1 = -2, by1 = -2, bx2 = 2, by2 = 2
 * 输出：16
 * <p>
 * <p>
 * 提示：
 * <p>
 * -104 <= ax1, ay1, ax2, ay2, bx1, by1, bx2, by2 <= 104
 * <p>
 * Copyright (c) @2021 deerhunter0837@gmail.com All Rights Reserved.
 *
 * @author xuejunc
 * @createTime 6/24/21 3:03 PM
 */
public class Topic223 {
    public static class Solution1 {
        public int computeArea(int ax1, int ay1, int ax2, int ay2, int bx1, int by1, int bx2, int by2) {
            int area = (ax2 - ax1) * (ay2 - ay1) + (bx2 - bx1) * (by2 - by1);
            // 无重叠
            if (ax1 > bx2 || ax2 < bx1 || ay1 > by2 || ay2 < by1) {
                return area;
            }
            // o前缀的变量表示重叠矩形的参数
            int ox1 = Math.max(ax1, bx1);
            int ox2 = Math.min(ax2, bx2);
            int oy1 = Math.max(ay1, by1);
            int oy2 = Math.min(ay2, by2);
            return area - (ox2 - ox1) * (oy2 - oy1);
        }
    }
}
