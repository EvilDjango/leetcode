package com.deerhunter.topic;

/**
 * 第45题
 * <p>
 * <p>
 * 给定一个非负整数数组，你最初位于数组的第一个位置。
 * <p>
 * 数组中的每个元素代表你在该位置可以跳跃的最大长度。
 * <p>
 * 你的目标是使用最少的跳跃次数到达数组的最后一个位置。
 * <p>
 * 示例:
 * <p>
 * 输入: [2,3,1,1,4]
 * 输出: 2
 * 解释: 跳到最后一个位置的最小跳跃数是 2。
 *      从下标为 0 跳到下标为 1 的位置，跳 1 步，然后跳 3 步到达数组的最后一个位置。
 * 说明:
 * <p>
 * 假设你总是可以到达数组的最后一个位置。
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/jump-game-ii
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 * <p>
 * Copyright (c) 2019, deerhunter0837@gmail.com All Rights Reserved.
 *
 * @author xuejunc
 * @date 2020-03-03
 */
public class Topic045 {
    /**
     * 动态规划解法
     */
    public static class Solution1 {
        public static int jump(int[] nums) {
            if (nums.length == 1) {
                return 0;
            }
            int[] dp = new int[nums.length];
            // dp[i]表示到达nums[i]需要的最短步数
            dp[0] = 0;
            for (int i = 1; i < dp.length; i++) {
                dp[i] = -1;
            }
            for (int i = 0; i < dp.length; i++) {
                int steps = nums[i];
                for (int j = 1; j < steps + 1 && i + j < nums.length; j++) {
                    int dest = i + j;
                    if (dp[dest] == -1) {
                        dp[dest] = dp[i] + 1;
                    } else {
                        dp[dest] = Math.min(dp[dest], dp[i] + 1);
                    }
                }
                if (dp[dp.length - 1] != -1) {
                    return dp[dp.length - 1];
                }
            }
            return -1;
        }
    }

    /**
     * 滑动窗口
     */
    public static class Solution2 {
        public static int jump(int[] nums) {
            int end = 0;
            int maxDistance = 0;
            int steps = 0;
            for (int i = 0; i < nums.length - 1; i++) {
                maxDistance = Math.max(maxDistance, i + nums[i]);
                if (i == end) {
                    end = maxDistance;
                    steps++;
                }
            }
            return steps;
        }
    }

    /**
     * 从终点往回寻路
     */
    public static class Solution3 {
        public static int jump(int[] nums) {
            int steps = 0;
            int position = nums.length - 1;
            while (position != 0) {
                for (int i = 0; i < position; i++) {
                    if (nums[i] + i >= position) {
                        position = i;
                        steps++;
                    }
                }
            }
            return steps;
        }
    }
}
