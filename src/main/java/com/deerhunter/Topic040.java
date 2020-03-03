package com.deerhunter;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.Stack;

/**
 * 给定一个数组 candidates 和一个目标数 target ，找出 candidates 中所有可以使数字和为 target 的组合。
 * <p>
 * candidates 中的每个数字在每个组合中只能使用一次。
 * <p>
 * 说明：
 * <p>
 * 所有数字（包括目标数）都是正整数。
 * 解集不能包含重复的组合。 
 * 示例 1:
 * <p>
 * 输入: candidates = [10,1,2,7,6,1,5], target = 8,
 * 所求解集为:
 * [
 * [1, 7],
 * [1, 2, 5],
 * [2, 6],
 * [1, 1, 6]
 * ]
 * 示例 2:
 * <p>
 * 输入: candidates = [2,5,2,1,2], target = 5,
 * 所求解集为:
 * [
 *   [1,2,2],
 *   [5]
 * ]
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/combination-sum-ii
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * <p>
 * Copyright (c) 2019, deerhunter0837@gmail.com All Rights Reserved.
 *
 * @author xuejunc
 * @date 2019-11-18
 */
public class Topic040 {
    private List<List<Integer>> combinations;
    private int[] candidates;

    public List<List<Integer>> combinationSum(int[] candidates, int target) {
        combinations = new ArrayList<>();
        Arrays.sort(candidates);
        this.candidates = candidates;
        dfs(new Stack<>(), 0, target);
        return combinations;
    }

    private void dfs(Stack<Integer> temp, int index, int target) {
        if (target == 0) {
            combinations.add(new ArrayList<>(temp));
            return;
        }
        if (index == candidates.length) {
            return;
        }

        if (target < candidates[index]) {
            return;
        }


        for (int i = index; i < candidates.length; i++) {
            int num = candidates[i];
            if (i - 1 >= 0 && candidates[i - 1] == num && i > index) {
                continue;
            }
            if (num <= target) {
                temp.push(num);
                dfs(temp, i + 1, target - num);
                temp.pop();
            }
        }
    }
}
