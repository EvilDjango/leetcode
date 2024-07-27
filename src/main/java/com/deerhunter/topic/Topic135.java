package com.deerhunter.topic;

import java.util.ArrayList;
import java.util.List;

/**
 * 老师想给孩子们分发糖果，有 N 个孩子站成了一条直线，老师会根据每个孩子的表现，预先给他们评分。
 * <p>
 * 你需要按照以下要求，帮助老师给这些孩子分发糖果：
 * <p>
 * 每个孩子至少分配到 1 个糖果。
 * 相邻的孩子中，评分高的孩子必须获得更多的糖果。
 * 那么这样下来，老师至少需要准备多少颗糖果呢？
 * <p>
 * 示例 1:
 * <p>
 * 输入: [1,0,2]
 * 输出: 5
 * 解释: 你可以分别给这三个孩子分发 2、1、2 颗糖果。
 * 示例 2:
 * <p>
 * 输入: [1,2,2]
 * 输出: 4
 * 解释: 你可以分别给这三个孩子分发 1、2、1 颗糖果。
 * 第三个孩子只得到 1 颗糖果，这已满足上述两个条件。
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/candy
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * <p>
 * Copyright (c) 20120 deerhunter0837@gmail.com All Rights Reserved.
 *
 * @author xuejunc
 * @createTime 2020/9/25 17:07
 */
public class Topic135 {
    public static class Solution {
        public int candy(int[] ratings) {
            int n = ratings.length;
            if (n == 0) {
                return 0;
            }

            int sum = 1;
            int candy = 1;
            int lastPeak = 0;
            boolean down = false;
            for (int i = 1; i < n; i++) {
                if (ratings[i] < ratings[i - 1]) {
                    candy--;
                    if (!down) {
                        lastPeak = i - 1;
                    }
                    down = true;
                } else {
                    // 结算下降段
                    if (down) {
                        if (candy < 1) {
                            sum += (i - lastPeak) * (1 - candy);
                        } else {
                            sum -= (i - lastPeak - 1) * (candy - 1);
                        }
                        candy = 1;
                    }
                    down = false;
                }

                if (ratings[i] > ratings[i - 1]) {
                    candy++;
                }
                if (ratings[i] == ratings[i - 1]) {
                    candy = 1;
                }
                sum += candy;
            }

            // 结算下降段
            if (down) {
                if (candy < 1) {
                    sum += (n - lastPeak) * (1 - candy);
                } else {
                    sum -= (n - lastPeak - 1) * (candy - 1);
                }
            }
            return sum;
        }
    }

}
