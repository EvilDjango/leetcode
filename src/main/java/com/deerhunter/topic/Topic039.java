package com.deerhunter.topic;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Stack;

/**
 * 给定一个无重复元素的数组 candidates 和一个目标数 target ，找出 candidates 中所有可以使数字和为 target 的组合。
 * <p>
 * candidates 中的数字可以无限制重复被选取。
 * <p>
 * 说明：
 * <p>
 * 所有数字（包括 target）都是正整数。
 * 解集不能包含重复的组合。 
 * 示例 1:
 * <p>
 * 输入: candidates = [2,3,6,7], target = 7,
 * 所求解集为:
 * [
 * [7],
 * [2,2,3]
 * ]
 * 示例 2:
 * <p>
 * 输入: candidates = [2,3,5], target = 8,
 * 所求解集为:
 * [
 *   [2,2,2,2],
 *   [2,3,3],
 *   [3,5]
 * ]
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/combination-sum
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * <p>
 * Copyright (c) 2019, deerhunter0837@gmail.com All Rights Reserved.
 *
 * @author xuejunc
 * @date 2019-11-18
 */
public class Topic039 {
    private List<List<Integer>> combinations;
    private int[] candidates;

    public List<List<Integer>> combinationSum(int[] candidates, int target) {
        combinations = new ArrayList<>();
        Arrays.sort(candidates);
        this.candidates = candidates;
        dfs(new Stack<>(), target);
        return combinations;
    }

    private void dfs(Stack<Integer> temp, int target) {
        if (target == 0) {
            combinations.add(new ArrayList<>(temp));
            return;
        }
        if (target < candidates[0]) {
            return;
        }

        int curMax = Integer.MIN_VALUE;
        if (temp.size() > 0) {
            curMax = temp.peek();
        }
        for (int i : candidates) {
            if (i >= curMax && i <= target) {
                temp.push(i);
                dfs(temp, target - i);
                temp.pop();
            }
        }
    }
}
