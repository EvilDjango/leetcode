package com.deerhunter;

import java.util.ArrayList;
import java.util.List;

/**
 * 254. 因子的组合
 * 整数可以被看作是其因子的乘积。
 * <p>
 * 例如：
 * <p>
 * 8 = 2 x 2 x 2;
 * = 2 x 4.
 * 请实现一个函数，该函数接收一个整数 n 并返回该整数所有的因子组合。
 * <p>
 * 注意：
 * <p>
 * 你可以假定 n 为永远为正数。
 * 因子必须大于 1 并且小于 n。
 * 示例 1：
 * <p>
 * 输入: 1
 * 输出: []
 * 示例 2：
 * <p>
 * 输入: 37
 * 输出: []
 * 示例 3：
 * <p>
 * 输入: 12
 * 输出:
 * [
 * [2, 6],
 * [2, 2, 3],
 * [3, 4]
 * ]
 * 示例 4:
 * <p>
 * 输入: 32
 * 输出:
 * [
 * [2, 16],
 * [2, 2, 8],
 * [2, 2, 2, 4],
 * [2, 2, 2, 2, 2],
 * [2, 4, 4],
 * [4, 8]
 * ]
 * 通过次数4,470提交次数7,851
 * <p>
 * Copyright (c) @2021 deerhunter0837@gmail.com All Rights Reserved.
 *
 * @author xuejunc
 * @createTime 7/27/21 1:20 PM
 */
public class Topic254 {
    class Solution {
        public List<List<Integer>> getFactors(int n) {
            return getFactors(n, 2);
        }

        private List<List<Integer>> getFactors(int n, int start) {
            List<List<Integer>> ans = new ArrayList<>();
            for (int i = start; i * i <= n; i++) {
                if (n % i == 0) {
                    List<Integer> list = new ArrayList<>();
                    list.add(n / i);
                    list.add(i);
                    ans.add(list);
                    for (List<Integer> sub : getFactors(n/i, i)) {
                        sub.add(i);
                        ans.add(sub);
                    }
                }
            }
            return ans;
        }
    }

}
