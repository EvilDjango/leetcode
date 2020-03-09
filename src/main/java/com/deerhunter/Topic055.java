package com.deerhunter;

/**
 * 给定一个非负整数数组，你最初位于数组的第一个位置。
 * <p>
 * 数组中的每个元素代表你在该位置可以跳跃的最大长度。
 * <p>
 * 判断你是否能够到达最后一个位置。
 * <p>
 * 示例 1:
 * <p>
 * 输入: [2,3,1,1,4]
 * 输出: true
 * 解释: 我们可以先跳 1 步，从位置 0 到达 位置 1, 然后再从位置 1 跳 3 步到达最后一个位置。
 * 示例 2:
 * <p>
 * 输入: [3,2,1,0,4]
 * 输出: false
 * 解释: 无论怎样，你总会到达索引为 3 的位置。但该位置的最大跳跃长度是 0 ， 所以你永远不可能到达最后一个位置。
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/jump-game
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * <p>
 * Copyright (c) 2019, deerhunter0837@gmail.com All Rights Reserved.
 *
 * @author xuejunc
 * @date 2020-03-09
 */
public class Topic055 {
    /**
     * 贪心解法
     */
    public static class Solution1 {

        public boolean canJump(int[] nums) {
            int maxIndex = 0;
            for (int i = 0; i < nums.length; i++) {
                maxIndex = Math.max(i + nums[i], maxIndex);
                if (maxIndex >= nums.length - 1) {
                    return true;
                }
                if (maxIndex == i) {
                    return false;
                }
            }
            return false;

        }
    }

    /**
     * 动态规划,就地使用原数组作为dp数组
     */
    public static class Solution2 {

        public boolean canJump(int[] nums) {
            for (int i = 0; i < nums.length; i++) {
                nums[i] = Math.max(i + nums[i], i > 0 ? nums[i - 1] : 0);
                if (nums[i] >= nums.length - 1) {
                    return true;
                }
                if (nums[i] == i) {
                    return false;
                }
            }
            return false;
        }
    }

    /**
     * 自底向上的动态规划
     */
    public static class Solution3 {

        public boolean canJump(int[] nums) {
            // 记忆数组，mem[i]=1表示从nums下标i处能到达nums末尾，为-1表示不能，为0表示尚未查明
            boolean[] canJumpToEnd = new boolean[nums.length];
            canJumpToEnd[nums.length - 1] = true;
            for (int i = nums.length - 2; i >= 0; i--) {
                canJumpToEnd[i] = false;
                for (int j = i + 1; j <= i + nums[i]; j++) {
                    if (canJumpToEnd[j]) {
                        canJumpToEnd[i] = true;
                        break;
                    }
                }
            }
            return canJumpToEnd[0];
        }
    }

    /**
     * 自底向上的贪心解法，是Solution3的改进版
     */
    public static class Solution4 {

        public boolean canJump(int[] nums) {
            // 当前能达到终点的最靠左的下标
            int left = nums.length - 1;
            for (int i = nums.length - 1; i >= 0; i--) {
                if (nums[i] + i >= left) {
                    left = i;
                }
            }
            return left == 0;
        }
    }
}
