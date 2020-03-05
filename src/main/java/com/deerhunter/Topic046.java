package com.deerhunter;

import java.util.ArrayList;
import java.util.List;

/**
 * 给定一个没有重复数字的序列，返回其所有可能的全排列。
 * <p>
 * 示例:
 * <p>
 * 输入: [1,2,3]
 * 输出:
 * [
 * [1,2,3],
 * [1,3,2],
 * [2,1,3],
 * [2,3,1],
 * [3,1,2],
 * [3,2,1]
 * ]
 * 通过次数83,712提交次数112,770
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/permutations
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * <p>
 * Copyright (c) 2019, deerhunter0837@gmail.com All Rights Reserved.
 *
 * @author xuejunc
 * @date 2020-03-04
 */
public class Topic046 {
    public static class Solution1 {
        public static List<List<Integer>> permute(int[] nums) {
            List<List<Integer>> result = new ArrayList<>();
            permute(nums, new boolean[nums.length], new ArrayList<>(), result);
            return result;
        }

        private static  void permute(int[] nums, boolean[] book, List<Integer> temp, List<List<Integer>> result) {
            if (temp.size() == nums.length) {
                result.add(new ArrayList<>(temp));
                return;
            }
            for (int i = 0; i < book.length; i++) {
                if (book[i]) {
                    continue;
                }
                book[i] = true;
                temp.add(nums[i]);
                permute(nums, book, temp, result);
                book[i] = false;
                temp.remove(temp.size() - 1);
            }
        }
    }
}
