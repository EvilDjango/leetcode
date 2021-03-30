package com.deerhunter;

import java.util.ArrayList;
import java.util.List;

/**
 * 216. Combination Sum III
 * Medium
 * <p>
 * 1712
 * <p>
 * 64
 * <p>
 * Add to List
 * <p>
 * Share
 * Find all valid combinations of k numbers that sum up to n such that the following conditions are true:
 * <p>
 * Only numbers 1 through 9 are used.
 * Each number is used at most once.
 * Return a list of all possible valid combinations. The list must not contain the same combination twice, and the combinations may be returned in any order.
 * <p>
 * <p>
 * <p>
 * Example 1:
 * <p>
 * Input: k = 3, n = 7
 * Output: [[1,2,4]]
 * Explanation:
 * 1 + 2 + 4 = 7
 * There are no other valid combinations.
 * Example 2:
 * <p>
 * Input: k = 3, n = 9
 * Output: [[1,2,6],[1,3,5],[2,3,4]]
 * Explanation:
 * 1 + 2 + 6 = 9
 * 1 + 3 + 5 = 9
 * 2 + 3 + 4 = 9
 * There are no other valid combinations.
 * Example 3:
 * <p>
 * Input: k = 4, n = 1
 * Output: []
 * Explanation: There are no valid combinations. [1,2,1] is not valid because 1 is used twice.
 * Example 4:
 * <p>
 * Input: k = 3, n = 2
 * Output: []
 * Explanation: There are no valid combinations.
 * Example 5:
 * <p>
 * Input: k = 9, n = 45
 * Output: [[1,2,3,4,5,6,7,8,9]]
 * Explanation:
 * 1 + 2 + 3 + 4 + 5 + 6 + 7 + 8 + 9 = 45
 * ​​​​​​​There are no other valid combinations.
 * <p>
 * <p>
 * Constraints:
 * <p>
 * 2 <= k <= 9
 * 1 <= n <= 60
 * <p>
 * Copyright (c) 20120 deerhunter0837@gmail.com All Rights Reserved.
 *
 * @author xuejunc
 * @createTime 2021/3/26 14:34
 */
public class Topic216 {
    /**
     * 回溯
     */
    public static class Solution1 {
        public List<List<Integer>> combinationSum3(int k, int n) {
            List<List<Integer>> ans = new ArrayList<>();
            dfs(ans, new boolean[9], 0, n, k);
            return ans;
        }

        private void dfs(List<List<Integer>> ans, boolean[] used, int last, int target, int quota) {
            if (target == 0) {
                if (quota != 0) {
                    return;
                }
                List<Integer> combination = new ArrayList<>();
                for (int i = 0; i < 9; i++) {
                    if (used[i]) {
                        combination.add(i + 1);
                    }
                }
                ans.add(combination);
                return;
            }
            if (last == 9 || quota == 0) {
                return;
            }
            for (int i = last; i < 9; i++) {
                if (!used[i]) {
                    if (i < target) {
                        used[i] = true;
                        dfs(ans, used, i + 1, target - i - 1, quota - 1);
                        used[i] = false;
                    }
                }
            }
        }
    }

    /**
     * 二进制子集枚举
     */
    public static class Solution2 {
        public List<List<Integer>> combinationSum3(int k, int n) {
            List<List<Integer>> ans = new ArrayList<>();
            for (int i = 3; i < 1 << 9; i++) {
                checkCombination(ans, i, k, n);
            }
            return ans;
        }

        private void checkCombination(List<List<Integer>> ans, int combination, int quota, int target) {
            List<Integer> nums = new ArrayList<>();
            int num = 0;
            for (int i = 0; i < 9; i++) {
                num++;
                if ((combination & 1) == 1) {
                    nums.add(num);
                    target -= num;
                }
                combination >>= 1;
                if (combination == 0) {
                    break;
                }
            }
            if (nums.size() == quota && target == 0) {
                ans.add(nums);
            }
        }
    }
}
