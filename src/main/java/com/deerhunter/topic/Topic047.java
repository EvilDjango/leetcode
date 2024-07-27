package com.deerhunter.topic;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 给定一个可包含重复数字的序列，返回所有不重复的全排列。
 * <p>
 * 示例:
 * <p>
 * 输入: [1,1,2]
 * 输出:
 * [
 * [1,1,2],
 * [1,2,1],
 * [2,1,1]
 * ]
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/permutations-ii
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * <p>
 * Copyright (c) 2019, deerhunter0837@gmail.com All Rights Reserved.
 *
 * @author xuejunc
 * @date 2020-03-05
 */
public class Topic047 {
    public static class Solution1 {
        public List<List<Integer>> permuteUnique(int[] nums) {
            Arrays.sort(nums);
            List<List<Integer>> result = new ArrayList<>();
            dfs(nums, new boolean[nums.length], new ArrayList<>(), result);
            return result;
        }

        private void dfs(int[] nums, boolean[] book, ArrayList<Integer> temp, List<List<Integer>> result) {
            if (temp.size() == nums.length) {
                result.add(new ArrayList<>(temp));
                return;
            }
            for (int i = 0; i < book.length; i++) {
                if (book[i]) {
                    continue;
                }
                if (i > 0 && nums[i] == nums[i - 1] && !book[i - 1]) {
                    continue;
                }
                temp.add(nums[i]);
                book[i] = true;
                dfs(nums, book, temp, result);
                temp.remove(temp.size() - 1);
                book[i] = false;
            }
        }
    }
}
