package com.deerhunter;

import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;

/**
 * 170. 两数之和 III - 数据结构设计
 * 设计一个接收整数流的数据结构，该数据结构支持检查是否存在两数之和等于特定值。
 * <p>
 * 实现 TwoSum 类：
 * <p>
 * TwoSum() 使用空数组初始化 TwoSum 对象
 * void add(int number) 向数据结构添加一个数 number
 * boolean find(int value) 寻找数据结构中是否存在一对整数，使得两数之和与给定的值相等。如果存在，返回 true ；否则，返回 false 。
 * <p>
 * <p>
 * 示例：
 * <p>
 * 输入：
 * ["TwoSum", "add", "add", "add", "find", "find"]
 * [[], [1], [3], [5], [4], [7]]
 * 输出：
 * [null, null, null, null, true, false]
 * <p>
 * 解释：
 * TwoSum twoSum = new TwoSum();
 * twoSum.add(1);   // [] --> [1]
 * twoSum.add(3);   // [1] --> [1,3]
 * twoSum.add(5);   // [1,3] --> [1,3,5]
 * twoSum.find(4);  // 1 + 3 = 4，返回 true
 * twoSum.find(7);  // 没有两个整数加起来等于 7 ，返回 false
 * <p>
 * <p>
 * 提示：
 * <p>
 * -105 <= number <= 105
 * -231 <= value <= 231 - 1
 * 最多调用 5 * 104 次 add 和 find
 * 通过次数7,390提交次数17,754
 * <p>
 * Copyright (c) @2021 deerhunter0837@gmail.com All Rights Reserved.
 *
 * @author xuejunc
 * @createTime 7/19/21 2:24 PM
 */
public class Topic170 {
    public static class TwoSum {
        private List<Integer> list = new ArrayList<>();

        /**
         * Initialize your data structure here.
         */
        public TwoSum() {

        }

        /**
         * Add the number to an internal data structure..
         */
        public void add(int number) {
            list.add(upperBound(number, list), number);
        }

        private int upperBound(int number, List<Integer> list) {
            int l = 0, r = list.size();
            while (l < r) {
                int mid = (r - l) / 2 + l;
                if (list.get(mid) <= number) {
                    l = mid + 1;
                } else {
                    r = mid;
                }
            }
            return l;
        }

        /**
         * Find if there exists any pair of numbers which sum is equal to the value.
         */
        public boolean find(int value) {
            int l = 0, r = list.size() - 1;
            while (l < r) {
                int remainder = value - list.get(l);
                if (remainder == list.get(r)) {
                    return true;
                }
                if (remainder > list.get(r)) {
                    l++;
                } else {
                    r--;
                }
            }
            return false;
        }
    }
}
