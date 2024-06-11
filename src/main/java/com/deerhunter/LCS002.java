package com.deerhunter;

import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;

/**
 * LCS 02. 完成一半题目
 * 有 N 位扣友参加了微软与力扣举办了「以扣会友」线下活动。主办方提供了 2*N 道题目，整型数组 questions 中每个数字对应了每道题目所涉及的知识点类型。
 * 若每位扣友选择不同的一题，请返回被选的 N 道题目至少包含多少种知识点类型。
 * <p>
 * 示例 1：
 * <p>
 * 输入：questions = [2,1,6,2]
 * <p>
 * 输出：1
 * <p>
 * 解释：有 2 位扣友在 4 道题目中选择 2 题。
 * 可选择完成知识点类型为 2 的题目时，此时仅一种知识点类型
 * 因此至少包含 1 种知识点类型。
 * <p>
 * 示例 2：
 * <p>
 * 输入：questions = [1,5,1,3,4,5,2,5,3,3,8,6]
 * <p>
 * 输出：2
 * <p>
 * 解释：有 6 位扣友在 12 道题目中选择题目，需要选择 6 题。
 * 选择完成知识点类型为 3、5 的题目，因此至少包含 2 种知识点类型。
 * <p>
 * 提示：
 * <p>
 * questions.length == 2*n
 * 2 <= questions.length <= 10^5
 * 1 <= questions[i] <= 1000
 * 通过次数6,071提交次数9,244
 * <p>
 * Copyright (c) @2022 deerhunter0837@gmail.com All Rights Reserved.
 *
 * @author xuejunc
 * @createTime 2022/6/4 上午11:47
 */
public class LCS002 {
    /**
     * 按照出现次数降序排序（注意要把相同数字排在一起），然后统计前半部分的不同数字数量即得到答案。可惜的是会超时。
     *
     * @param questions
     * @return
     */
    public int halfQuestions(int[] questions) {
        Map<Integer, Integer> count = new HashMap<>();
        for (int q : questions) {
            count.merge(q, 1, Integer::sum);
        }
        Arrays.sort(questions);
        sortInts(questions, (i, j) -> {
            int diff = count.get(j) - count.get(i);
            return diff == 0 ? j - i : diff;
        }, 0, questions.length - 1);
        int size = 1;
        for (int i = 1; i < questions.length >> 1; i++) {
            if (questions[i] != questions[i - 1]) {
                size++;
            }
        }
        return size;
    }

    /**
     * 快速排序整型数组
     *
     * @param nums
     * @param comparator
     */
    private void sortInts(int[] nums, Comparator<Integer> comparator, int start, int end) {
        if (start >= end) {
            return;
        }
        int base = nums[start];
        int l = start, r = end;
        while (l < r) {
            while (l < r && comparator.compare(nums[r], base) >= 0) {
                r--;
            }
            nums[l] = nums[r];
            while (l < r && comparator.compare(nums[l], base) <= 0) {
                l++;
            }
            nums[r] = nums[l];
        }
        nums[l] = base;
        sortInts(nums, comparator, start, l - 1);
        sortInts(nums, comparator, l + 1, end);
    }

    /**
     * 基本思路与解法1一致，但是利用了元素范围属于[1,1000]从而使用一个数组来统计数字，由于我们不关心具体选取哪些元素，所以直接对统计数组排序即可。
     *
     * @param questions
     * @return
     */
    public int halfQuestions2(int[] questions) {
        int[] counts = new int[1001];
        for (int q : questions) {
            counts[q]++;
        }
        Arrays.sort(counts);
        int ans = 0;
        for (int i = 1000, size = 0; i >=0 && size < questions.length >> 1; i--) {
            ans++;
            size += counts[i];
        }
        return ans;
    }
}
