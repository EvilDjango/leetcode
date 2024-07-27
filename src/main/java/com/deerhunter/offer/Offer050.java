package com.deerhunter.offer;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;

/**
 * 剑指 Offer 50. 第一个只出现一次的字符
 * 在字符串 s 中找出第一个只出现一次的字符。如果没有，返回一个单空格。 s 只包含小写字母。
 * <p>
 * 示例 1:
 * <p>
 * 输入：s = "abaccdeff"
 * 输出：'b'
 * 示例 2:
 * <p>
 * 输入：s = ""
 * 输出：' '
 * <p>
 * <p>
 * 限制：
 * <p>
 * 0 <= s 的长度 <= 50000
 * <p>
 * 通过次数228,603提交次数367,916
 * <p>
 * Copyright (c) @2022 deerhunter0837@gmail.com All Rights Reserved.
 *
 * @author xuejunc
 * @createTime 2022/4/25 下午6:39
 */
public class Offer050 {
    /**
     * 朴素解法
     *
     * @param s
     * @return
     */
    public char firstUniqChar(String s) {
        Map<Character, Integer> count = new HashMap<>();
        char[] chars = s.toCharArray();
        for (char c : chars) {
            count.merge(c, 1, Integer::sum);
        }
        for (char c : chars) {
            if (count.get(c) == 1) {
                return c;
            }
        }
        return ' ';
    }

    /**
     * 哈希表+队列，利用队列来保存未重复出现的数字。参考了官方题解
     *
     * @param s
     * @return
     */
    public char firstUniqChar2(String s) {
        Map<Character, Integer> position = new HashMap<>();
        Queue<Character> queue = new LinkedList<>();
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (position.containsKey(c)) {
                position.put(c, -1);
            } else {
                position.put(c, i);
                queue.add(c);
            }
        }
        while (!queue.isEmpty() && position.get(queue.peek()) == -1) {
            queue.remove();
        }
        return queue.isEmpty() ? ' ' : queue.remove();
    }
}
