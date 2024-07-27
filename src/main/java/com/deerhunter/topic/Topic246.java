package com.deerhunter.topic;

import java.util.HashMap;
import java.util.Map;

/**
 * 246. 中心对称数
 * 中心对称数是指一个数字在旋转了 180 度之后看起来依旧相同的数字（或者上下颠倒地看）。
 * <p>
 * 请写一个函数来判断该数字是否是中心对称数，其输入将会以一个字符串的形式来表达数字。
 * <p>
 * <p>
 * <p>
 * 示例 1:
 * <p>
 * 输入: num = "69"
 * 输出: true
 * 示例 2:
 * <p>
 * 输入: num = "88"
 * 输出: true
 * 示例 3:
 * <p>
 * 输入: num = "962"
 * 输出: false
 * 示例 4：
 * <p>
 * 输入：num = "1"
 * 输出：true
 * 通过次数5,043提交次数10,646
 * <p>
 * Copyright (c) @2021 deerhunter0837@gmail.com All Rights Reserved.
 *
 * @author xuejunc
 * @createTime 7/20/21 2:42 PM
 */
public class Topic246 {
    public static class Solution1 {
        private Map<Character, Character> map = new HashMap<>();

        {
            map.put('0', '0');
            map.put('1', '1');
            map.put('8', '8');
            map.put('6', '9');
            map.put('9', '6');
        }

        public boolean isStrobogrammatic(String num) {
            for (int i = 0; i <= (num.length() - 1) / 2; i++) {
                char c = num.charAt(i);
                if (!map.containsKey(c) || num.charAt(num.length() - 1 - i) != map.get(c)) {
                    return false;
                }
            }
            return true;
        }
    }
}
